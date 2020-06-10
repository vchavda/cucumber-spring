package cukes.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import restAssuredUtils.ResponseApi;

public class Hooks {
    @Value("${testing}")
    private String testing;

    @Value("${screenshotEveryStep}")
    private boolean screenshotEveryStep;

    @Autowired
    public WebDriver driver;

    @Autowired
    private ResponseApi respApi;

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
        System.out.println("testing type is :" + testing);
        System.out.println("Respone time: " + respApi.getResponseTime());

        if (testing.equals("api")) {
            scenario.write("\n" + "APi Request" + respApi.getRequestValue() + "\n" + "APi Response" + respApi.getResponseValue());
        }
        else if (testing.equals("selenium")) {
            if (scenario.isFailed() || screenshotEveryStep) {
                final byte[] screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot,"image/png");
            }
        }
    }

}
