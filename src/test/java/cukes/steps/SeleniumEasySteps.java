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

    @Then("^test inputForm demo$")
    public void testInputFormDemo() {
        seleniumEasyHomePage.testInputForm();
    }

    @Then("^test bootStrapDatePicket demo$")
    public void testBootStrapDatePicketDemo() throws InterruptedException {
        seleniumEasyHomePage.testBootStrapDatePiker();
    }

    @Then("^test tablePagination demo$")
    public void testPaginationDemo() {
        seleniumEasyHomePage.testTablePagination();
    }


    @Then("^test tableFilter demo$")
    public void testTableFilterDemo() {
        seleniumEasyHomePage.testTableFilter();
    }

    @Then("^test tableSortandSearch demo$")
    public void testTableSortandSearchDemo() {
        seleniumEasyHomePage.testTableSortandSearch();
    }

    @Then("^test slider demo$")
    public void testSliderDemo() throws InterruptedException {
        seleniumEasyHomePage.testSlider();
    }

    @Then("^test bootStrap alert demo$")
    public void testBootStrapDemo() throws InterruptedException {
        seleniumEasyHomePage.testBootStrapAlert();
    }

    @Then("^test bootStrap modal alert demo$")
    public void testBootStrapModalAlertDemo() {
        seleniumEasyHomePage.testBootStrapModalAlert();
    }

    @Then("^test windows popup modal demo$")
    public void testWindowsPopupModalDemo() {
        seleniumEasyHomePage.testWindowsPopupModal();
    }

    @Then("^test bootstrap list box demo$")
    public void testBootstrapListBoxDemo() {
        seleniumEasyHomePage.testBootstrapListBox();
    }
}
