package seleniumUtils;

import org.openqa.selenium.WebDriver;


public class Baseutils {

    public WebDriver Driver;

    public WebDriver getDriver() {
        return Driver;
    }

    public void setDriver(WebDriver driver) {
        Driver = driver;
    }
}
