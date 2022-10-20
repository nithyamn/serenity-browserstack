package com.browserstack;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import net.thucydides.core.environment.SystemEnvironmentVariables;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;

public class BrowserStackSerenityDriver implements DriverSource {

    public WebDriver newDriver() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) environmentVariables.getProperty("browserstack.user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) environmentVariables.getProperty("browserstack.key");
        }

        String environment = System.getProperty("environment");
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bStackMap = new HashMap<>();

        Iterator it = environmentVariables.getKeys().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();

            if (key.equals("browserstack.user") || key.equals("browserstack.key")
                    || key.equals("browserstack.server")) {
                continue;
            } else if (key.startsWith("bstack_")) {
                bStackMap.put(key.replace("bstack_", ""), environmentVariables.getProperty(key));
                if (key.equals("bstack_local")
                        && environmentVariables.getProperty(key).equalsIgnoreCase("true")) {
                    bStackMap.put("local", "true");
                }
            } else if (environment != null && key.startsWith("environment." + environment)) {
                bStackMap.put(key.replace("environment." + environment + ".", ""),
                        environmentVariables.getProperty(key));
                if (key.equals("environment." + environment + ".local")
                        && environmentVariables.getProperty(key).equalsIgnoreCase("true")) {
//                    System.setProperty("browserstack.local", "true");
                    bStackMap.put("local", true);
                }
            }
        }
        capabilities.setCapability("bstack:options", bStackMap);
        try {
            return new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@"
                    + environmentVariables.getProperty("browserstack.server") + "/wd/hub"), capabilities);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean takesScreenshots() {
        return true;
    }
}
