package com.browserstack;

import java.util.HashMap;
import java.util.Map;

import com.browserstack.local.Local;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.openqa.selenium.MutableCapabilities;

public class BrowserStackSerenityTest {
    private static Local bsLocal;

    public static void checkAndStartBrowserStackLocal(MutableCapabilities capabilities, String accessKey)
            throws Exception {
        if (bsLocal != null) {
            return;
        }
        if (capabilities.getCapability("bstack:options") != null
                && ((JSONObject) capabilities.getCapability("bstack:options")).get("local") != null
                && ((boolean) ((JSONObject) capabilities.getCapability("bstack:options")).get("local")) == true) {
            bsLocal = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            bsLocal.start(options);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (bsLocal != null) {
            bsLocal.stop();
        }
    }
}
