package seleniumUtils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seleniumUtils.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class WikiLandingPage extends BasePage {

    @Autowired
    public WebDriver driver;

    @FindBy(id = "searchInput")
    private WebElement searchInputEditBox;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(xpath = "//a[contains(text(),'Français')]")
    private WebElement frenchLang;

    @FindBy(linkText = "English")
    private WebElement englishLang;

    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    private WebElement loginLink;

    public WikiLandingPage(WebDriver driver) {
        // you need this for the Findby elements to work otherwise you will get a nullpointer
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void setMessageField(String value) {
        searchInputEditBox.sendKeys(value);
        searchButton.click();
    }

    public void verifytitle(String value) {
        //driver.findElement(By.xpath(".//div[contains(@class,'mw-redirect')]")).getAttribute("title");
        System.out.println(this.driver.getTitle());
        assertThat(this.driver.getTitle()).contains(value);
    }


    public void setLanguagetoFrench() {
        driver.findElement(By.xpath("//a[contains(text(),'Fran')]")).click();
        WebDriverWait wait = new WebDriverWait(this.driver,10);
        System.out.println("current URL: " + this.driver.getCurrentUrl());
        if (this.driver.getCurrentUrl() != "https://fr.wikipedia.org/wiki/Kilimandjaro"){
            wait.until(ExpectedConditions.urlToBe("https://fr.wikipedia.org/wiki/Kilimandjaro"));
        }
        assertThat(this.driver.getCurrentUrl()).contains("fr.wikipedia.org");
    }


    public void setLanguagetoEnglish() {
        englishLang.click();
        if (this.driver.getCurrentUrl() != "https://en.wikipedia.org/wiki/Mount_Kilimanjaro"){
            WebDriverWait wait = new WebDriverWait(this.driver,10);
            wait.until(ExpectedConditions.urlToBe("https://en.wikipedia.org/wiki/Mount_Kilimanjaro"));
        }
        assertThat(this.driver.getCurrentUrl()).contains("en.wikipedia.org");

    }

    public void clickLoginLink() {
        loginLink.click();
        assertThat(this.driver.getTitle()).contains("Log in - Wikipedia");
    }

    public void
    navigateToWebApp(String url) {
        this.driver.get(url);
    }
}
