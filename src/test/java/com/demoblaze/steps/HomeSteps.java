package com.demoblaze.steps;

import com.demoblaze.pom.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.demoblaze.helpers.AssertionWrapper.assertThatPageIsOpened;

public class HomeSteps {

    @Autowired
    private HomePage homePage;

    @Given("home page is displayed")
    public void testUrl() {
        assertThatPageIsOpened(homePage);
    }

    @When("user selects {string} category")
    public void userClicksOnLaptopsCategory(String laptops) {
        homePage.clickCategory(laptops);
    }

    @And("user selects {string}")
    public void userSelects(String laptopModel) {
        homePage.clickProduct(laptopModel);
    }
}
