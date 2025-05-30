package com.demoblaze.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CartPage extends BasePage implements Page {

    @FindBy(xpath = "//h2[(text()='Products')]")
    private WebElement productsLabel;

    @FindBy(xpath = "//div[@class='table-responsive']")
    private WebElement productTable;

    @FindBy(xpath = "//h2[(text()='Total')]")
    private WebElement totalLabel;

    @FindBy(id = "totalp")
    private WebElement totalPrice;

    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//div[@id='orderModal']//div[@class='modal-content']")
    private WebElement placeOrderForm;

    @FindBy(xpath = "//div[@class='sweet-alert  showSweetAlert visible']")
    private WebElement confirmationForm;

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void clickPlaceOrder() {
        placeOrderButton.click();
    }

    public void completeForm(String name, String country, String city, String creditCard, String month, String year) {

        getWait().until(ExpectedConditions.visibilityOf(placeOrderForm));

        placeOrderForm.findElement(By.id("name")).sendKeys(name);
        placeOrderForm.findElement(By.id("country")).sendKeys(country);
        placeOrderForm.findElement(By.id("city")).sendKeys(city);
        placeOrderForm.findElement(By.id("card")).sendKeys(creditCard);
        placeOrderForm.findElement(By.id("month")).sendKeys(month);
        placeOrderForm.findElement(By.id("year")).sendKeys(year);

        placeOrderForm.findElement(By.xpath("//button[@onClick='purchaseOrder()']")).click();
    }

    public void checkAndConfirmOrder() {
        getWait().until(ExpectedConditions.visibilityOf(confirmationForm));

        confirmationForm.findElement(By.xpath("//div[@class='sa-placeholder']")).isDisplayed();
        confirmationForm.findElement(By.xpath("//h2[text()='Thank you for your purchase!']")).isDisplayed();
        confirmationForm.findElement(By.xpath("//p[@class='lead text-muted ']")).isDisplayed();
        confirmationForm.findElement(By.xpath("//button[@class='confirm btn btn-lg btn-primary']")).isDisplayed();

        confirmationForm.findElement(By.xpath("//button[@class='confirm btn btn-lg btn-primary']")).click();
    }

    @Override
    public boolean isAt() {
        return false;
    }
}
