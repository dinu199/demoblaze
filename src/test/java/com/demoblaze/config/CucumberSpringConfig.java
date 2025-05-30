package com.demoblaze.config;

import com.demoblaze.DemoblazeApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@CucumberContextConfiguration
@SpringBootTest(classes = {DemoblazeApplication.class})
@ComponentScan({"com.demoblaze.config"})
public class CucumberSpringConfig {
}
