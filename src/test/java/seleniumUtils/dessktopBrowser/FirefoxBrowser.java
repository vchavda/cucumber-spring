package seleniumUtils.dessktopBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
