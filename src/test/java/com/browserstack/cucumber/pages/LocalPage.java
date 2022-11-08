package com.browserstack.cucumber.pages;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("http://bs-local.com:45691/check")
public class LocalPage extends PageObject {
    private WebDriver driverInstance;

    public LocalPage(WebDriver driver) {
        super(driver);
        driverInstance = driver;
    }

    public void bodyShouldMatch(String matchTitle) {
        assertThat(driverInstance.getPageSource()).containsIgnoringCase(matchTitle);
    }

    @After
    public void teardown(){
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}
