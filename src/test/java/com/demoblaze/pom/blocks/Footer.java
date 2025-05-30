package com.demoblaze.pom.blocks;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Footer {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='caption'][.//b[text()='About Us']]")
    private WebElement aboutUs;

    @FindBy(xpath = "//div[@class='caption'][.//b[text()='Get in Touch']]")
    private WebElement getinTouch;

    @FindBy(xpath = "//div[@class='caption']//h4[contains(., 'PRODUCT STORE')]")
    private WebElement productStore;

    public Footer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
