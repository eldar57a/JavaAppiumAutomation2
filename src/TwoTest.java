
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;


public class TwoTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Eldar\\OneDrive\\Рабочий стол\\JavaAppiumAutomation\\JavaAppiumAutomation2\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input",
            5
    );

        waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search…')]"),
            "JAVA",
            "Cannot find search input",
            8
    );

        WebElement search_results_element = findElement(
            By.id("org.wikipedia:id/page_list_item_title"),
            "Cannot find search list results",
            15
    );

        String searchResultText = search_results_element.getText();
        Assert.assertTrue(
                "No matches for the word Java",
                searchResultText.contains("Java")
    );
}

    private WebElement findElement(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutSeconds) {
        WebElement element = findElement(by, error_message, timeoutSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutSeconds) {
        WebElement element = findElement(by, error_message, timeoutSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
