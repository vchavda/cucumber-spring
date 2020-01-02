package cukes.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import restAssuredUtils.CustomLogFilter;
import restAssuredUtils.ResponseApi;
import seleniumUtils.pages.LandingPage;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


public class WhenSteps extends Base {

    WebDriver driver;
    LandingPage landingPage;

    @Autowired
    private ResponseApi respApi;

    @Autowired
    private CustomLogFilter customLogFilter;

    @Value("${user}")
    private String user;

    @Value("${browser}")
    private String browser;

    @Value("${gekoDriver}")
    private String gekoDriver;

    @Value("${chromeDriver}")
    private String chromeDriver;


    @Before
    public void myBeforeStep() {
        System.out.println("user: " + user);
        System.out.println("browser: " + browser);

        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", gekoDriver);
            driver = new FirefoxDriver();
        }
        else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            driver = new ChromeDriver();
        }
        else
            System.out.println("driverPath not specified in the Configuration.properties file");

        if (driver != null) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
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

    //-----------------------------------------------------------------------------------------------------------------//

    @When("^I am on the landing page: '(.*)'$")
    public void iAmOnTheLandingPage(String url) {
        landingPage = new LandingPage(driver);
        landingPage.navigateToWebApp(url);
    }

    @And("^type: \"([^\"]*)\" into message field$")
    public void typeIntoMessageField(String value) throws Throwable {
        landingPage.setMessageField(value);
    }

    @And("^the title of the page is: '(.*)'$")
    public void theTitleOfThePageIsMountKilimanjaroWikipedia(String value) {
        landingPage.verifytitle(driver, value);
    }

    @And("^close the browser$")
    public void closeTheBrowser() {
        driver.quit();
    }

    @And("^the language is changed to French$")
    public void theLanguageIsChangedToFrench() {
        landingPage.setLanguagetoFrench(driver);
    }

    @And("^the language is changed to English$")
    public void theLanguageIsChangedToEnglish() {
        landingPage.setLanguagetoEnglish(driver);
    }


}
