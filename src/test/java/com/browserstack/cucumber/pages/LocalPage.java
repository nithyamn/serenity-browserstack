package com.browserstack.cucumber.pages;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("http://bs-local.com:45454/")
public class LocalPage extends PageObject {
    
    public LocalPage() {
    }

    @After
    public void teardown(){
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}
