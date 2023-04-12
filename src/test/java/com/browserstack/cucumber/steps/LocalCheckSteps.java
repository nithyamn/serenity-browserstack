package com.browserstack.cucumber.steps;

import io.cucumber.java.en.Then;
import com.browserstack.cucumber.pages.LocalPage;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalCheckSteps {
    LocalPage localPage;

    @Then("^Title should contain \"([^\"]*)\"$")
    public void matchTitle(String matchTitle) throws Throwable {
        localPage.open();
        assertThat(localPage.getTitle()).containsIgnoringCase(matchTitle);
    }
}
