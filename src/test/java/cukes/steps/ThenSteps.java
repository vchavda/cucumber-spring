package cukes.steps;

import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import restAssuredUtils.ResponseApi;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenSteps {

    @Autowired
    private ResponseApi respApi;


    @Then("^I get a numeric '(\\d+)' response code returned$")
    public void iGetAResponseCodeReturned(int code) {
        assertThat(code).isEqualTo(respApi.getResponseCode());
    }

    @Then("^I get a '(.*)' response code returned$")
    public void iGetAResponseCodeStringReturned(int code) {
        assertThat(code).isEqualTo(respApi.getResponseCode());
    }

}
