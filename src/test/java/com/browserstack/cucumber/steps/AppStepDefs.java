package com.browserstack.cucumber.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class AppStepDefs{
    public AppiumDriver driver;
    public String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public String appID = "";
    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("projectName", "Serenity App Tests");
        browserstackOptions.put("buildName", "serenity-appium");
        browserstackOptions.put("sessionName", "wiki app test");

        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("deviceName", "Google Pixel 3");
        capabilities.setCapability("app",appID);
        driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),capabilities);
    }
    @When("Open the app")
    public void open_the_app() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(MobileBy.accessibilityId("Search Wikipedia")).click();
        Thread.sleep(2000);

    }
    @When("Search for anything")
    public void search_for_anything() throws InterruptedException {

        // Write code here that turns the phrase above into concrete actions
        driver.findElement(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
    }
    @Then("The search results should appear")
    public void the_search_results_should_appear() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(2000);
    }
    @After
    public void teardown(){
        driver.quit();
    }
}
