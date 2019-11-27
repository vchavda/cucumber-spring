package seleniumUtils.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Main {

    @FindBy(how = How.LINK_TEXT, using = "Demo Home")
    WebElement homeMenu;

    public Main(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickLink() {
        homeMenu.click();
    }

}
