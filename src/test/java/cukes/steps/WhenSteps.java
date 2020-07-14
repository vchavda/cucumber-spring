package cukes.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import restAssuredUtils.CustomLogFilter;
import restAssuredUtils.ResponseApi;

import java.net.MalformedURLException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class WhenSteps extends Base {
    private Logger log = LogManager.getLogger(WhenSteps.class);
    @Autowired
    private ResponseApi respApi;

    @Autowired
    private CustomLogFilter customLogFilter;

    @Value("${user}")
    private String user;

    @Value("${baseUrl}")
    private String baseUrl;


    private RequestSpecification requestSpecification;

    @Before
    public void myBeforeStep() {

        // need this baseconfiguration for log4j to work properly
        BasicConfigurator.configure();
        this.logoutPertinentEnvironmentVariable();
    };

    //-----------------------------------------------------------------------------------------------------------------//
    private void logoutPertinentEnvironmentVariable() {
        this.log.info("user: " + this.user);
        this.log.debug("baseUrl: " + this.user);
    }


    @When("^I make a test call using this url: '(.*)'$")
    public void iMakeATestCallUsingThisUrlHttpsReqresInApiUnknown(String url) throws MalformedURLException {

        // reason for the substring her is that the baseUrl has single quotes so we need to remove it
        // otherwise you will get malformedurlexception no protocol exception
        RestAssured.baseURI = (url.isEmpty() ? baseUrl.substring(1, baseUrl.length() - 1) : url);
        Response response = null;
        try {

            response = given()
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
            respApi.setResponseTime(response.getTime());

        } else {
            System.out.println("NULL !");
        }
    }


    @Given("^a user spcifies query parameters:$")
    public void aUserSpcifiesQueryParameters(Map<String, String> qp) {
        requestSpecification = given().filter(customLogFilter).queryParams(qp);
    }

    @Given("^I submit dynamic headers$")
    public void iSubmitDynamicHeaders(Map<String, String> hp)  {
        requestSpecification = given().filter(customLogFilter).headers(hp);
    }

    @When("^I make a request to this url: '(.*)'$")
    public void iMakeARequestToThisUrlUrl(String endpoint) {
        Response response  = requestSpecification.get(endpoint);

        if (respApi != null) {
            System.out.println("NOT NULL");
            respApi.setResponseCode(response.getStatusCode());
            System.out.println("\n" + "APi Request" + customLogFilter.getRequestBuilderLogs());
            System.out.println("\n" + "APi Response" + customLogFilter.getResponseBuilderLogs());
            respApi.setRequestValue(customLogFilter.getRequestBuilderLogs().toString());
            respApi.setResponseValue(customLogFilter.getResponseBuilderLogs().toString());
            respApi.setResponseTime(response.getTime());

        } else {
            System.out.println("NULL !");
        }
    }
}

//-----------------------------------------------------------------------------------------------------------------//

