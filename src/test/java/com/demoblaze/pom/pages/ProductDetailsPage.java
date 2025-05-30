package com.demoblaze.pom.pages;

import com.demoblaze.pom.blocks.Header;
import com.demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

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

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

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
        List<WebElement> headerLinks = driver.findElements(By.xpath("//a[@class='nav-link']"))
                .stream().filter(element -> {
                    try {
                        return element.getText().trim().equalsIgnoreCase(link.trim());
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }
                }).toList();

        if (headerLinks.isEmpty()) {
            throw new NoSuchElementException("No visible header link found with text: " + link);
        }
        headerLinks.get(0).click();
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