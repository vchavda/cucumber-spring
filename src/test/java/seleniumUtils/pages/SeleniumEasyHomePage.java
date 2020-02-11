package seleniumUtils.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
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
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
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
        List<WebElement> selectedOptions = multipleDrpDown.getAllSelectedOptions();
        for (WebElement we : selectedOptions) {
            System.out.println("multi-selection value: " + we.getText());
        }

    }


    public void testInputForm() {
        driver.findElement(By.className("dropdown-toggle")).click();
        driver.findElement(By.linkText("Input Form Submit")).click();

        driver.findElement(By.name("first_name")).sendKeys("First name");
        driver.findElement(By.name("last_name")).sendKeys("Last name");
        driver.findElement(By.name("phone")).sendKeys("0123456789");
        driver.findElement(By.name("address")).sendKeys("Address 1");
        driver.findElement(By.name("city")).sendKeys("CITY");

        Select drpDown = new Select(driver.findElement(By.name("state")));
        drpDown.selectByVisibleText("Kansas");

        driver.findElement(By.name("hosting")).click();
        driver.findElement(By.name("comment")).sendKeys("This is a note field");
    }

    public void testBootStrapDatePiker() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Date pickers')]")).click();
        driver.findElement(By.linkText("Bootstrap Date Picker")).click();
        driver.findElement(By.xpath("//div[@class='input-group date']")).click();


        WebElement datePicked2 = driver.findElement(By.xpath("//div[@class='datepicker-days']//table[@class='table-condensed']"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String date = (String) jse.executeScript("return arguments[0].value", datePicked2);


        driver.findElement(By.xpath("//td[@class='today day']")).click();



        Thread.sleep(2000);
        System.out.println("DatePicked : " + driver.findElement(By.xpath("//div[@class='input-group date']")).getAttribute("value"));

        System.out.println("XXXX " + driver.findElement(By.xpath("//input[@placeholder='dd/mm/yyyy']")).getText());

        //not sure how to assert the date picked?
    }


    public void testTablePagination() {
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Table')]")).click();
        driver.findElement(By.linkText("Table Pagination")).click();
    }



    public void testTableFilter() {
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Table')]")).click();
        driver.findElement(By.linkText("Table Filter")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-danger btn-filter']")).click();
    }

    public void testTableSortandSearch() {
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Table')]")).click();
        driver.findElement(By.linkText("Table Sort & Search")).click();
        driver.findElement(By.xpath("//label[contains(text(),'Search:')]//input")).sendKeys("London");
        driver.findElement((By.xpath("//th[@class='sorting_asc']"))).click();
    }

    public void testSlider() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Progress Bars')]")).click();
        driver.findElement(By.linkText("Drag & Drop Sliders")).click();
        Thread.sleep(2000);
        WebElement slider = driver.findElement(By.xpath("//div[@class='range']//input[@name='range']"));
        Actions actions = new Actions(driver);
        actions.click(slider).build().perform();

        for (int i = 0; i < 20; i++)
        {
            actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            Thread.sleep(200);
        }

        assertThat(driver.findElement(By.xpath("//output[@id='range']")).getText()).isEqualTo("70");
    }


    public void testBootStrapAlert() throws InterruptedException {
        //this method clicks on a button and a message is displayed for 5 seconds and the button is disabled
        //we then wait for the button to become enabled and we can click it again
        // we assert to ensure we have clicked the button twice
        int clickCount = 0;
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Alerts & Modals')]")).click();
        driver.findElement(By.linkText("Bootstrap Alerts")).click();
        WebElement wb = driver.findElement(By.id("autoclosable-btn-success"));
        wb.click();
        clickCount = clickCount +1;


        WebDriverWait wait = new WebDriverWait(this.driver,5000);
        wait.until(ExpectedConditions.elementToBeClickable(wb));

        driver.findElement(By.id("autoclosable-btn-success")).click();
        clickCount = clickCount +1;
        assertThat(clickCount).isEqualTo(2);
    }


    public void testBootStrapModalAlert() {
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Alerts & Modals')]")).click();
        driver.findElement(By.linkText("Bootstrap Modals")).click();
        driver.findElement(By.xpath("//body/div[@class='container-fluid text-center']/div[@class='row']/div[@class='col-md-6 text-left']/div[1]/div[1]/div[1]/div[2]/a[1]")).click();
        //driver.switchTo().frame("myModal0");
        driver.findElement(By.xpath("//div[@id='myModal0']//a[@class='btn btn-primary'][contains(text(),'Save changes')]")).click();
    }

    public void testWindowsPopupModal() {
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'Alerts & Modals')]")).click();
        driver.findElement(By.linkText("Window Popup Modal")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Follow On Twitter')]")).click();
        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        // for reference:
        // need a wait here otherwise the window title will be blank as the window is not ready
        // WebDriverWait wait = new WebDriverWait(this.driver,5000);
        // wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //Wait for page title
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleContains("Selenium Easy (@seleniumeasy) on Twitter"));

        // explicit wait (for reference:)
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        assertThat( driver.getTitle()).isEqualTo("Selenium Easy (@seleniumeasy) on Twitter");
        // Close the new window, if that window no more required
        driver.close();

         //Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
    }


    public void testBootstrapListBox() {
        driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'List Box')]")).click();
        driver.findElement(By.linkText("Bootstrap List Box")).click();
        //WebElement list1 = driver.findElements(By.xpath("//li[@class='list-group-item active']"));



    }


}
