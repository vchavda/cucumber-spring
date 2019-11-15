package cukes.steps;

import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import restAssuredUtils.ResponseApi;

import static org.assertj.core.api.Assertions.assertThat;

//@ContextConfiguration(classes = {AppConfig.class})
//@ComponentScan(basePackages = {"cukes.support.**"})
public class WhenSteps {


    @Autowired
    private ResponseApi respApi;

    @When("^I make a test call using this url: '(.*)'$")
    public void iMakeATestCallUsingThisUrlHttpsReqresInApiUnknown(String val) {

        RestAssured.baseURI = "https://reqres.in/api/unknown";
        Response response = null;
        try {

            response = RestAssured.given()
                    .when()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(response.getStatusCode()).isEqualTo(200);

        if (respApi != null)  {
            System.out.println("NOT NULL");
            respApi.setResponseCode(response.getStatusCode());
        }
        else {
            System.out.println("NULL !");
        }
    }





}
