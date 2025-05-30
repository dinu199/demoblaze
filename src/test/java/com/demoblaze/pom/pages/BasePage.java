package com.demoblaze.pom.pages;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Slf4j
@Component
public class BasePage {

    @Autowired
    protected WebDriver driver;

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }
}
