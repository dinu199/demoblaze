package com.demoblaze.pom.pages;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Slf4j
@Component
public class BasePage {

    private static final long DEFAULT_WAIT_SECONDS = 5L;

    @Autowired
    protected WebDriver driver;

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }

    protected WebDriverWait getWait() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized");
        }
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_SECONDS));
    }
}
