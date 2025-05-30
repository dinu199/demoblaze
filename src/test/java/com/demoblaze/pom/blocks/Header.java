package com.demoblaze.pom.blocks;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Header {

    private final WebDriver driver;

    @FindBy(id = "nava")
    private WebElement navigationLogo;

    @FindBy(linkText = "Home")
    private WebElement homeLink;

    @FindBy(linkText = "Contact")
    private WebElement contactLink;

    @FindBy(linkText = "About us")
    private WebElement aboutusLink;

    @FindBy(linkText = "Cart")
    private WebElement cartLink;

    @FindBy(linkText = "Log in")
    private WebElement loginLink;

    @FindBy(linkText = "Sign up")
    private WebElement signupLink;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}