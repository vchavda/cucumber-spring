package cukes.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import restAssuredUtils.CustomLogFilter;
import restAssuredUtils.ResponseApi;
import seleniumUtils.pages.WikiLandingPage;
import seleniumUtils.pages.WikiLogin;


public class WikiSteps extends Base {

    @Autowired
    WebDriver driver;

    @Autowired
    WikiLandingPage wikiLandingPage;

    @Autowired
    WikiLogin wikepediaLogin;


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

    @When("^I am on the landing page: '(.*)'$")
    public void iAmOnTheLandingPage(String url) {
        wikiLandingPage.navigateToWebApp(url);
    }

    @And("^type: \"([^\"]*)\" into message field$")
    public void typeIntoMessageField(String value) throws Throwable {
        wikiLandingPage.setMessageField(value);
    }

    @And("^the title of the page is: '(.*)'$")
    public void theTitleOfThePageIsMountKilimanjaroWikipedia(String value) {
        wikiLandingPage.verifytitle(value);
    }

    @And("^close the browser$")
    public void closeTheBrowser() {
        driver.close();
        driver.quit();
    }

    @And("^the language is changed to French$")
    public void theLanguageIsChangedToFrench() {
        wikiLandingPage.setLanguagetoFrench();
    }

    @And("^the language is changed to English$")
    public void theLanguageIsChangedToEnglish() {
        wikiLandingPage.setLanguagetoEnglish();
    }

    @And("^click on the logn link$")
    public void clickOnTheLognLink() {
        wikiLandingPage.clickLoginLink();
    }

    @And("^enter username: '(.*)'$")
    public void enterUsernameSomeusername(String value) {
        wikepediaLogin.enterUserName(value);
    }

    @And("^enter password: '(.*)'$")
    public void enterPasswordSomepassword(String value) {
        wikepediaLogin.enterPassword(value);
    }


}
