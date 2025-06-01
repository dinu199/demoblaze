package com.demoblaze.steps;

import com.demoblaze.pom.pages.CartPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@ScenarioScope
public class CartSteps {

    @Autowired
    private CartPage cartPage;

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
    public void userRemovesProductFromCart(DataTable dataTable) {
        List<String> data = dataTable.asList();

        cartPage.removeProductsFromCart(data);
    }

    @Then("cart should be empty")
    public void cartShouldBeEmpty() {
        cartPage.checkCartIsEmpty();
    }
}
