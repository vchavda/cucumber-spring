package cukes.steps;

import cucumber.api.java.Before;
import seleniumUtils.Baseutils;

public class Hooks {

    private Baseutils baseutils;

    public Hooks() {
        System.out.println("Constructor");
    }

    @Before
    public void InitializeTest() {
        //call the relevant procedure
    }

    public void BeforeHookCucumber() {
        // this is where before hook for cucumber specific code is set
    }

    public void BeforeHookSelenium() {
        // this is where before hook for Selenium specific code is set
    }

}
