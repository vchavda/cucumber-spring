package seleniumUtils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumUtils.Base;

import static org.assertj.core.api.Assertions.assertThat;

public class LandingPage extends Base {

    public WebDriver driver;

    @FindBy(id = "searchInput")
    private WebElement searchInputEditBox;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(xpath = "//a[contains(text(),'Français')]")
    private WebElement frenchLang;

    @FindBy(linkText = "English")
    private WebElement englishLang;

    public LandingPage(WebDriver driver) {
        super(driver);
        // you need this for the Findby elements to work otherwise you will get a nullpointer
        PageFactory.initElements(driver, this);
            driver = this.driver;
    }

    public void setMessageField(String value) {
        searchInputEditBox.sendKeys(value);
        searchButton.click();
    }

    public void verifytitle(WebDriver driver, String value) {
        //driver.findElement(By.xpath(".//div[contains(@class,'mw-redirect')]")).getAttribute("title");
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle()).contains(value);
    }


    public void setLanguagetoFrench(WebDriver driver) {
        driver.findElement(By.xpath("//a[contains(text(),'Français')]")).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        System.out.println("current URL: " + driver.getCurrentUrl());
        if (driver.getCurrentUrl() != "https://fr.wikipedia.org/wiki/Kilimandjaro"){
            wait.until(ExpectedConditions.urlToBe("https://fr.wikipedia.org/wiki/Kilimandjaro"));
        }
        assertThat(driver.getCurrentUrl()).contains("fr.wikipedia.org");
    }


    public void setLanguagetoEnglish(WebDriver driver) {
        englishLang.click();
        if (driver.getCurrentUrl() != "https://en.wikipedia.org/wiki/Mount_Kilimanjaro"){
            WebDriverWait wait = new WebDriverWait(driver,5);
            wait.until(ExpectedConditions.urlToBe("https://en.wikipedia.org/wiki/Mount_Kilimanjaro"));
        }
        assertThat(driver.getCurrentUrl()).contains("en.wikipedia.org");

    }
}
