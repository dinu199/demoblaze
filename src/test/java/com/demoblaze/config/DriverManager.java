package com.demoblaze.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
@Slf4j
public class DriverManager {

    @Value("#{'${demoblaze-url}'}")
    private URL demoblazeUrl;

    @Value("#{'${browser}'}")
    private String browser;

    private static WebDriver driver;

    @Bean
    public WebDriver getDriver() {
        synchronized (DriverManager.class) {
            if (driver == null) {
                String os = System.getProperty("os.name").toLowerCase();
                String selectedBrowser = browser.toLowerCase();

                log.info("OS detected: {}", os);
                log.info("Browser detected: {}", selectedBrowser);

                switch (selectedBrowser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--start-maximized");
                        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                        driver = new ChromeDriver(chromeOptions);
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--start-maximized");
                        edgeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                        driver = new EdgeDriver(edgeOptions);
                        break;
                }
                log.info("Browser initialized: {}", selectedBrowser);
                driver.navigate().to(demoblazeUrl);;
            }
        }
        return driver;
    }
}
