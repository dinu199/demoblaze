package com.demoblaze.steps;

import com.demoblaze.pom.pages.CartPage;
import com.demoblaze.utils.SharedData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@ScenarioScope
public class CartSteps {

    @Autowired
    private CartPage cartPage;

    @Autowired
    private SharedData sharedData;

    @And("user places an order")
    public void userPlacesOrder() {
        cartPage.clickPlaceOrder();
    }

    @And("user completes the order form")
    public void userCompletesTheOrderForm(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> form = data.get(0);

        cartPage.completeForm(
                form.get("name"),
                form.get("country"),
                form.get("city"),
                form.get("creditCard"),
                form.get("month"),
                form.get("year")
        );
    }

    @Then("user checks and confirms order")
    public void confirmationMessagePopsUp() {
        cartPage.checkAndConfirmOrder();
    }

    @And("user removes product from cart")
    public void userRemovesProductFromCart() {
        List<String> products = sharedData.getModels();

        cartPage.removeProductsFromCart(products);
    }

    @Then("cart should be empty")
    public void cartShouldBeEmpty() {
        assertTrue(cartPage.getActualCartProductNames().isEmpty());
    }

    @Then("cart contains the added products")
    public void cartContainsTheAddedProducts() {
        List<String> expectedProducts = sharedData.getModels();
        Collections.sort(expectedProducts);
        var actualProducts  = cartPage.getActualCartProductNames();
        Collections.sort(actualProducts);

        for(String expected : expectedProducts) {
            assertThat(actualProducts).as("Cart should contain product: " + expected)
                    .anyMatch(actual -> actual.equalsIgnoreCase(expected));
        }
    }

    @And("cart total should match sum of product prices")
    public void cartTotalShouldMatchSumOfProductPrices() {
        cartPage.validateTotalPrice();
    }
}
