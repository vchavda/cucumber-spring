package cukes.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import restAssuredUtils.ResponseApi;
import seleniumUtils.Base;

public class Hooks {
    @Autowired
    private ResponseApi respApi;

    private Base base;
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


    @After
    public void addData(Scenario scenario) {
        scenario.write("\n" + "APi Request" + respApi.getRequestValue() + "\n" + "APi Response" + respApi.getResponseValue());
    }

}
