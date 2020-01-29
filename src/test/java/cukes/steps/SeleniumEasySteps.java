package cukes.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import seleniumUtils.pages.SeleniumEasyHomePage;

public class SeleniumEasySteps {

    @Autowired
    SeleniumEasyHomePage seleniumEasyHomePage;

    @Given("^My landing page is: '(.*)'$")
    public void myLandingPageIsHttpsEnWikipediaOrgWikiMain_Page(String url) {
        seleniumEasyHomePage.navigateToWebApp(url);
    }


    @Then("^test simple form$")
    public void testSimpleForm() {
        seleniumEasyHomePage.testSimpleForm();
    }

    @Then("^test checkbox demo$")
    public void testCheckboxDemo() {
        seleniumEasyHomePage.testCheckBoxDemo();
    }

    @Then("^test radiobutton demo$")
    public void testradiobutton() {
        seleniumEasyHomePage.testRadioButtonDemo();
    }

    @Then("^test dropdownlist demo$")
    public void testDropDownList() throws InterruptedException {
        seleniumEasyHomePage.testDropDownListDemo();
    }

}
