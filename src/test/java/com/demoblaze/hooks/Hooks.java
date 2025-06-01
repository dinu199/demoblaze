package com.demoblaze.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import com.demoblaze.config.DriverManager;

import java.time.Duration;
import java.util.List;

@Slf4j
public class Hooks {

    @Autowired
    private WebDriver driver;

    @Before()
    public void setup() {
        driver.manage().deleteAllCookies();
        log.info("Demoblaze page is loaded");
    }

    @Before
    public void clearCart() {
        driver.navigate().to("https://demoblaze.com/cart.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tbodyid")));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Delete')]")));
        } catch (TimeoutException e) {
            return;
        }

        List<WebElement> deleteLinks = driver.findElements(By.xpath("//a[contains(text(),'Delete')]"));

        while (!deleteLinks.isEmpty()) {
            WebElement firstDelete = deleteLinks.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(firstDelete));
            firstDelete.click();

            wait.until(ExpectedConditions.stalenessOf(firstDelete));
            deleteLinks = driver.findElements(By.xpath("//a[contains(text(),'Delete')]"));
        }
    }

    @After()
    public void tearDown() {
        DriverManager.closeDriver();
    }
}