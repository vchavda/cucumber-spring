package seleniumUtils.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WikiLogin {
    @Autowired
    public WebDriver driver;

    @FindBy(id = "wpName1")
    private WebElement usernameEdit;

    @FindBy(id = "wpPassword1")
    private WebElement password;

    public WikiLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void enterUserName(String username) {
        usernameEdit.sendKeys(username);
        //WebDriverWait wait = new WebDriverWait(driver,10);
        //wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("wpPassword1"),username));
        //assertThat(usernameEdit.getText()).isEqualTo(username);
    }

    public void enterPassword(String pw) {
        password.sendKeys(pw);
    }
}
