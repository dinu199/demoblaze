package com.demoblaze.pom.pages;

import com.demoblaze.utils.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

@Component
@Slf4j
public class HomePage extends BasePage implements Page {

    @Value("#{'${demoblaze-url}'}")
    private URL homePage;

    @FindBy(id = "contcar")
    private WebElement carouselItems;

    @FindBy(css = "a[class='carousel-control-prev']")
    private WebElement carouselPreviousButton;

    @FindBy(css = "a[class='carousel-control-next']")
    private WebElement carouselNextButton;

    @FindBy(css = ".list-group a")
    private List<WebElement> categoryLinks;

    @FindBy(css = ".card.h-100")
    private List<WebElement> productCards;

    @FindBy(id = "prev2")
    private WebElement previousPageButton;

    @FindBy(id = "next2")
    private WebElement nextPageButton;

    public void openHomePage() {
        driver.navigate().to(homePage);
    }

    public void clickCategory(String category) {
        for(WebElement element : categoryLinks) {
            if(element.getText().equalsIgnoreCase(category)) {
                element.click();
                break;
            }
        }
    }

    public void clickProduct(String product) {
        WebDriverWait wait = getWait();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h4[@class='card-title']/a[normalize-space()='" + product + "']")
        ));

        List<WebElement> cards = driver.findElements(By.cssSelector("#tbodyid .card.h-100"));

        for (WebElement card : cards) {
            try {
                if (!card.isDisplayed()) continue;

                WebElement titleLink = card.findElement(By.cssSelector("h4.card-title > a"));
                String titleText = titleLink.getText().trim();

                if (titleText.equalsIgnoreCase(product.trim())) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", titleLink);
                    wait.until(ExpectedConditions.elementToBeClickable(titleLink));
                    titleLink.click();
                    return;
                }
            } catch (StaleElementReferenceException e) {
                log.info("Retry or skip");
            }
        }
        throw new NoSuchElementException("Product '" + product + "' not found.");
    }

    @Override
    public boolean isAt() {
        return WaitUtils.awaitForElements(
                carouselItems,
                carouselPreviousButton,
                carouselNextButton,
                previousPageButton,
                nextPageButton);
    }
}
