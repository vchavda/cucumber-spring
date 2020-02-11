package cukes.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import restAssuredUtils.CustomLogFilter;
import restAssuredUtils.ResponseApi;

import static org.assertj.core.api.Assertions.assertThat;


public class WhenSteps extends Base {

    @Autowired
    private ResponseApi respApi;

    @Autowired
    private CustomLogFilter customLogFilter;

    @Value("${user}")
    private String user;

    @Before
    public void myBeforeStep() {
        System.out.println("user: " + user);
    }

    //-----------------------------------------------------------------------------------------------------------------//

    @When("^I make a test call using this url: '(.*)'$")
    public void iMakeATestCallUsingThisUrlHttpsReqresInApiUnknown(String val) {
        CustomLogFilter customLogFilter = new CustomLogFilter();

        RestAssured.baseURI = "https://reqres.in/api/unknown";
        Response response = null;
        try {

            response = RestAssured.given()
                    .when()
                    .filter(customLogFilter)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(response.getStatusCode()).isEqualTo(200);

        if (respApi != null) {
            System.out.println("NOT NULL");
            respApi.setResponseCode(response.getStatusCode());
            System.out.println("\n" + "APi Request" + customLogFilter.getRequestBuilderLogs());
            System.out.println("\n" + "APi Response" + customLogFilter.getResponseBuilderLogs());
            respApi.setRequestValue(customLogFilter.getRequestBuilderLogs().toString());
            respApi.setResponseValue(customLogFilter.getResponseBuilderLogs().toString());
        } else {
            System.out.println("NULL !");
        }
    }



}

    //-----------------------------------------------------------------------------------------------------------------//

