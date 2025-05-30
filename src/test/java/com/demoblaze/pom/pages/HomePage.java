package com.demoblaze.pom.pages;

import com.demoblaze.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class HomePage extends BasePage implements Page {

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

    public void clickCategory(String category) {
        for(WebElement element : categoryLinks) {
            if(element.getText().equalsIgnoreCase(category)) {
                element.click();
                break;
            }
        }
    }

    public void clickProduct(String product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> visibleCards = (List<WebElement>) js.executeScript(
                "return Array.from(document.querySelectorAll('#tbodyid .card.h-100'))" +
                        ".filter(e => e.offsetParent !== null);");

        for (WebElement card : visibleCards) {
            WebElement titleLink = card.findElement(By.cssSelector("h4.card-title > a"));
            String titleText = titleLink.getText().trim();

            if (titleText.equalsIgnoreCase(product.trim())) {
                wait.until(ExpectedConditions.visibilityOf(titleLink));
                titleLink.click();
                break;
            }
        }
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
