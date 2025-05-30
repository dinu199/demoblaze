package com.demoblaze.pom.pages;

import com.demoblaze.pom.blocks.Header;
import com.demoblaze.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailsPage extends BasePage implements Page {

    @Autowired
    private Header header;

    @FindBy(xpath = "//div[@class='product-image']")
    private WebElement productImage;

    @FindBy(xpath = "//h2[@class='name']")
    private WebElement productTitle;

    @FindBy(xpath = "//h3[@class='price-container']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='description description-tabs']")
    private WebElement productDescription;

    @FindBy(xpath = "//a[@class='btn btn-success btn-lg']")
    private WebElement addToCartButton;

    public void clickAddToCartButton() {
        getWait().until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
    }

    public void checkAlertIsDisplayed() {
        getWait().until(ExpectedConditions.alertIsPresent());
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void clickHeaderLink(String link) {
        header.clickLink(link);
    }

    @Override
    public boolean isAt() {
        return WaitUtils.awaitForElements(productImage,
                productTitle,
                productPrice,
                productDescription,
                addToCartButton);
    }
}