package seleniumUtils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class SeleniumEasyHomePage {
    @Autowired
    public WebDriver driver;



    public void
    navigateToWebApp(String url) {
        this.driver.get(url);
    }


    public void testSimpleForm() {
        driver.findElement(By.className("dropdown-toggle")).click();
        driver.findElement(By.linkText("Simple Form Demo")).click();
        driver.findElement(By.id("sum1")).sendKeys("5");
        driver.findElement(By.id("sum2")).sendKeys("7");
        driver.findElement(By.xpath("//button[contains(text(),'Get Total')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Get Total')]")).sendKeys(Keys.RETURN);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String text = driver.findElement((By.xpath("//span[@id='displayvalue']"))).getText();
        System.out.println("text is " + text);
        assertThat(text).isEqualTo("12");
    }

    public void testCheckBoxDemo() {
        driver.findElement(By.className("dropdown-toggle")).click();
        driver.findElement(By.linkText("Checkbox Demo")).click();
        driver.findElement(By.id("isAgeSelected")).click();
        assertThat(driver.findElement(By.id("txtAge")).getText()).isEqualTo("Success - Check box is checked");
    }

    public void testRadioButtonDemo() {
        driver.findElement(By.className("dropdown-toggle")).click();
            driver.findElement(By.linkText("Radio Buttons Demo")).click();
        driver.findElement(By.xpath("//html//body//div//div//div//div//div//div//label[contains(text(),'Male')]//input")).click();
        assertThat(driver.findElement(By.xpath("//html//body//div//div//div//div//div//div//label[contains(text(),'Male')]//input")).isSelected()).isTrue();

    }

    public void testDropDownListDemo() throws InterruptedException {
        driver.findElement(By.className("dropdown-toggle")).click();
        driver.findElement(By.linkText("Select Dropdown List")).click();

        // single select dropdown
        Select drpDown = new Select(driver.findElement(By.id("select-demo")));
        drpDown.selectByVisibleText("Saturday");
        List<WebElement> listOptionDropdown = drpDown.getOptions();
        System.out.println("the list total is : " + listOptionDropdown.size());
        assertThat(listOptionDropdown.size()).isEqualTo(8);
        drpDown.selectByIndex(2);
        System.out.println(drpDown.getFirstSelectedOption().getText());
        assertThat(drpDown.getFirstSelectedOption().getText()).isEqualTo("Monday");

        //multiple select dropdown
        Select multipleDrpDown = new Select(driver.findElement(By.id("multi-select")));
        multipleDrpDown.selectByIndex(1);
        Thread.sleep(2000);
        multipleDrpDown.selectByIndex(2);
        Thread.sleep(2000);
        List<WebElement> selectedOptions =multipleDrpDown.getAllSelectedOptions();
        for(WebElement we : selectedOptions) {
            System.out.println("multi-selection value: "+ we.getText());
        }

    }


}
