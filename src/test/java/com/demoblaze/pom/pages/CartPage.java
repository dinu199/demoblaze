package com.demoblaze.pom.pages;

import com.demoblaze.utils.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
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

    public void removeProductsFromCart(List<String> products) {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//tr[@class='success']")));

        List<WebElement> rows = productTable.findElements(By.xpath(".//tr[@class='success']"));

        for (String targetProduct : products) {
            boolean found = false;

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 4) {
                    String productName = cells.get(1).getText().trim();

                    if (productName.equalsIgnoreCase(targetProduct.trim())) {
                        WebElement deleteLink = cells.get(3).findElement(By.linkText("Delete"));
                        wait.until(ExpectedConditions.elementToBeClickable(deleteLink));
                        deleteLink.click();

                        wait.until(ExpectedConditions.stalenessOf(row));
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                throw new NoSuchElementException("Product '" + targetProduct + "' not found.");
            }
            rows = productTable.findElements(By.xpath(".//tr[@class='success']"));
        }
    }

    public void clearCart() {
        WebDriverWait wait = getWait();

        List<WebElement> deleteLinks = productTable.findElements(By.linkText("Delete"));
        while (!deleteLinks.isEmpty()) {
            WebElement firstDelete = deleteLinks.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(firstDelete));
            firstDelete.click();

            wait.until(ExpectedConditions.stalenessOf(firstDelete));
            deleteLinks = productTable.findElements(By.linkText("Delete"));
        }
    }

    public List<String> getActualCartProductNames() {
        WebDriverWait wait = getWait();
        List<String> productNames = new ArrayList<>();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//tr[@class='success']")));
        List<WebElement> rows = productTable.findElements(By.xpath(".//tr[@class='success']"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 2) {
                productNames.add(0, cells.get(1).getText().trim());
            }
        }
        return productNames;
    }

    public boolean validateTotalPrice() {
        List<WebElement> rows = productTable.findElements(By.xpath(".//tr[@class='success']"));
        int expectedTotal = 0;
        int actualTotal = Integer.parseInt(totalPrice.getText());
        boolean isCorrectlyCalculated = false;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3) {
                expectedTotal += Integer.parseInt(cells.get(2).getText().trim());
            }
        }

        if (expectedTotal == actualTotal) {
            isCorrectlyCalculated = true;
        }

        return isCorrectlyCalculated;
    }

    @Override
    public boolean isAt() {
        return WaitUtils.awaitForElements(
                productsLabel,
                productTable,
                placeOrderButton
        );
    }
}
