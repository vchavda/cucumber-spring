package seleniumUtils;

import org.openqa.selenium.WebDriver;

public class BasePage {

    /*public WebDriver driver;

    public Base (WebDriver driver)
    {
        this.driver = driver;
    }

    public LandingPage navigateToWebApp(String url) {
        driver.navigate().to(url);
        return new LandingPage(driver);
    }*/

    protected WebDriver driver;
    void goTo(String url) {
        driver.get(url);
    }


}
