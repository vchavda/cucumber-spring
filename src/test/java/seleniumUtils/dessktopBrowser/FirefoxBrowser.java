package seleniumUtils.dessktopBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FirefoxBrowser {

    @Value("${browser}")
    private String browser;

    @Value("${gekoDriver}")
    private String gekoDriver;

    @Value("${chromeDriver}")
    private String chromeDriver;

    @Profile("firefox")
    @Bean
    public WebDriver getDriver() {
        System.out.println("browser is Firefox");
        System.setProperty("webdriver.gecko.driver", gekoDriver);
        FirefoxOptions options = new FirefoxOptions();
        // add this option to run this in headless mode.
        //options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
