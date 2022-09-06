package com.browserstack;

import com.browserstack.local.Local;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.AfterClass;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackSerenityTest {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        MutableCapabilities capabilities = new MutableCapabilities();
        driver = new RemoteWebDriver(
                new URL("https://hub.browserstack.com/wd/hub"), capabilities);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
