package com.demoblaze.steps;

import com.demoblaze.pom.pages.HomePage;
import com.demoblaze.utils.SharedData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static com.demoblaze.helpers.AssertionWrapper.assertThatPageIsOpened;

public class HomeSteps {

    @Autowired
    private HomePage homePage;

    @Autowired
    private SharedData sharedData;

    @Given("home page is displayed")
    public void homePageIsDisplayed() {
        homePage.openHomePage();
        assertThatPageIsOpened(homePage);
    }

    @When("user selects {string} category")
    public void userClicksOnLaptopsCategory(String category) {
        homePage.clickCategory(category);
    }

    @And("user selects {string}")
    public void userSelects(String model) {
        homePage.clickProduct(model);
        sharedData.getModels().add(model);
    }
}
