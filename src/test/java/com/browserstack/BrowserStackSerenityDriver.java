package com.browserstack;

import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import net.thucydides.core.webdriver.DriverSource;

public class BrowserStackSerenityDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        try {
            return new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
