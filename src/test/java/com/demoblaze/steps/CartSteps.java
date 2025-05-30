package com.demoblaze.steps;

import com.demoblaze.pom.pages.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;

@ScenarioScope
public class CartSteps {

    @Autowired
    private CartPage cartPage;

    @And("user places an order")
    public void userPlacesOrder() {
        cartPage.clickPlaceOrder();
    }

    @And("user completes the order form")
    public void userCompletesTheOrderForm() {
        cartPage.completeForm("Jonny", "Mexico", "Monterrey", "4568", "03", "1999");
    }

    @Then("user checks and confirms order")
    public void confirmationMessagePopsUp() {
        cartPage.checkAndConfirmOrder();
    }
}
