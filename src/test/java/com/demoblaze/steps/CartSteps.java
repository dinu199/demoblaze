package com.demoblaze.steps;

import com.demoblaze.pom.pages.CartPage;
import io.cucumber.datatable.DataTable;
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
    public void userCompletesTheOrderForm(DataTable dataTable) {
        // Using a single row data table with headers to pass form values
        var data = dataTable.asMaps().get(0);
        cartPage.completeForm(
                data.get("name"),
                data.get("country"),
                data.get("city"),
                data.get("creditCard"),
                data.get("month"),
                data.get("year")
        );
    }

    @Then("user checks and confirms order")
    public void confirmationMessagePopsUp() {
        cartPage.checkAndConfirmOrder();
    }
}
