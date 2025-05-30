package com.demoblaze.pom.blocks;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
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

    private WebElement getLinkByText(String linkText) {
        String normalized = linkText.trim().toLowerCase();
        return switch (normalized) {
            case "home" -> homeLink;
            case "contact" -> contactLink;
            case "about us", "aboutus" -> aboutusLink;
            case "cart" -> cartLink;
            case "log in", "login" -> loginLink;
            case "sign up", "signup" -> signupLink;
            default -> null;
        };
    }

    public void clickLink(String linkText) {
        WebElement target = getLinkByText(linkText);
        if (target == null || !target.isDisplayed()) {
            throw new NoSuchElementException("No visible header link found with text: " + linkText);
        }
        target.click();
    }
}