package com.demoblaze.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class Hooks {

    @Autowired
    private WebDriver driver;

    @Before()
    public void setup() {
        driver.manage().deleteAllCookies();
        log.info("Amazon page is loaded");
    }

    @After()
    public void tearDown() {

    }
}