package cukes.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import restAssuredUtils.CustomLogFilter;
import restAssuredUtils.ResponseApi;
import seleniumUtils.pages.Main;

import static org.assertj.core.api.Assertions.assertThat;



public class WhenSteps extends Base{

    @Autowired
    private ResponseApi respApi;

    @Autowired
    private CustomLogFilter customLogFilter;

    @Value("${user}")
    private String user;

    @Before
    public  void myBeforeStep() {
        System.out.println("beforestep: " + user);
    }

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

        if (respApi != null)  {
            System.out.println("NOT NULL");
            respApi.setResponseCode(response.getStatusCode());
            System.out.println("\n" + "APi Request" + customLogFilter.getRequestBuilderLogs());
            System.out.println("\n" + "APi Response" + customLogFilter.getResponseBuilderLogs());
            respApi.setRequestValue(customLogFilter.getRequestBuilderLogs().toString());
            respApi.setResponseValue(customLogFilter.getResponseBuilderLogs().toString());
        }
        else {
            System.out.println("NULL !");
        }
    }


    @And("^click on the home menu link$")
    public void clickOnTheHomeMenuLink() {
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "C://seleniumdriver//geckodriver.exe");
        driver= new FirefoxDriver();
        Main main = new Main(driver);
        main.clickLink();
    }
}
