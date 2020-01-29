package seleniumUtils.dessktopBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class ChromeBrowser {

    @Value("${browser}")
    private String browser;

    @Value("${gekoDriver}")
    private String gekoDriver;

    @Value("${chromeDriver}")
    private String chromeDriver;

    @Profile("chrome")
    @Bean
    public WebDriver getDriver() {
        System.out.println("browser is Chrome");
        System.setProperty("webdriver.chrome.driver", chromeDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--allow-http-screen-capture");
        options.addArguments("chrome.switches", "--disable-extensions");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver;
    }
}
