package seleniumUtils;

import org.openqa.selenium.WebDriver;
import seleniumUtils.pages.LandingPage;


public class Base  {

    public WebDriver driver;

    public Base (WebDriver driver)
    {
        this.driver = driver;
    }

    public LandingPage navigateToWebApp(String url) {
        driver.navigate().to(url);
        return new LandingPage(driver);
    }



}
