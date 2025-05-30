package com.demoblaze.steps;

import com.demoblaze.pom.pages.ProductDetailsPage;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import static com.demoblaze.helpers.AssertionWrapper.assertThatPageIsOpened;

public class ProductDetailsSteps {

    @Autowired
    private ProductDetailsPage productDetailsPage;

    @And("user is redirected to product detail page")
    public void userIsRedirectedToProductDetailPage() {
        assertThatPageIsOpened(productDetailsPage);
    }

    @And("user clicks add to cart button")
    public void userClicksAddToCartButton() {
        assertThatPageIsOpened(productDetailsPage);
        productDetailsPage.clickAddToCartButton();
    }

    @And("successful alert pops up")
    public void successfulAlertPopsUp() {
        productDetailsPage.checkAlertIsDisplayed();
        productDetailsPage.dismissAlert();
    }

    @And("user clicks on {string} link")
    public void userClicksOnLink(String link) {
        productDetailsPage.clickHeaderLink(link);
    }
}