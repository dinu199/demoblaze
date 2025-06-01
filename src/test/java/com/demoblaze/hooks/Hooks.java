package com.demoblaze.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.demoblaze.config.DriverManager;
import com.demoblaze.pom.pages.CartPage;

import java.net.URL;

@Slf4j
public class Hooks {

    @Autowired
    private WebDriver driver;

    @Autowired
    private CartPage cartPage;

    @Value("#{'${demoblaze-url}'}")
    private URL baseUrl;

    @Before()
    public void setup() {
        driver.manage().deleteAllCookies();
        log.info("Demoblaze page is loaded");
    }

    @Before
    public void clearCart() {
        driver.navigate().to(baseUrl + "/cart.html");
        cartPage.clearCart();
    }

    @After()
    public void tearDown() {
        DriverManager.closeDriver();
    }
}