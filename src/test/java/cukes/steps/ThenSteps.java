package cukes.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import restAssuredUtils.CustomLogFilter;
import restAssuredUtils.Jsonutils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenSteps {

    @Autowired
    private restAssuredUtils.ResponseApi respApi;


    @Autowired
    private CustomLogFilter customLogFilter;

    @Autowired
    Jsonutils jsonutils;

    public ThenSteps() {
    }

    @Then("^I get a numeric '(\\d+)' response code returned$")
    public void iGetAResponseCodeReturned(int code) {
        assertThat(code).isEqualTo(respApi.getResponseCode());
    }

    @Then("^I get a '(.*)' response code returned$")
    public void iGetAResponseCodeStringReturned(int code) {
        assertThat(code).isEqualTo(respApi.getResponseCode());
    }



    @And("^the response should contain values$")
    public void thisNodeShouldBeReturnedInTheResponsex(Map<String, String> table) {
        for (String key : table.keySet()) {
            MatcherAssert.assertThat(jsonutils.getValueForKey(customLogFilter.getLastResponse(), key), Is.is(table.get(key)));
        }
    }
}
