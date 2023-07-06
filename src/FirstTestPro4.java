import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTestPro4 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\Eldar\\OneDrive\\Рабочий стол\\JavaAppiumAutomation\\JavaAppiumAutomation2\\apks\\org.wikipedia.apk");

    driver =new

    AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
}

    @After
    public void tearDown() {

        driver.quit();
    }

    @Test //Этот тест проверяет сохранение двух статей
    public void saveTwoArticleInMyList()
    {
        String search_wikipedia = "//*[contains(@text,'Search Wikipedia')]";
        String search_line = "//*[contains(@text,'Search…')]";
        String search_value = "JAVA";
        String name_of_folder = "Eldar";
        String close_article = "//android.widget.ImageButton[@content-desc='Navigate up']";
        String locator_article_java = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='High-level programming language']";
        String actual_title = "//android.widget.TextView[@text='JavaScript']";
        String expected_title = "//android.widget.TextView[@text='JavaScript']";

        waitForElementAndClick(
                By.xpath(search_wikipedia),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath(search_line),
                search_value,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find Button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find options to add article to reading list",
                7
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text info article folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath(close_article),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath(search_wikipedia),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath(search_line),
                search_value,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath(locator_article_java),
                "Cannot find 'Search Wikipedia' input",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find Button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find options to add article to reading list",
                7
        );

        waitForElementAndClick(
                By.xpath("//*[(@text='" + name_of_folder + "')]"),
                "Cannot close article, cannot find click to My List",
                6
        );

        waitForElementAndClick(
                By.xpath(close_article),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation Button to My list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[(@text='" + name_of_folder + "')]"),
                "Cannot find '" + name_of_folder + "' input",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        int amount_of_search_result = getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']")
        );

        Assert.assertTrue(
                "Cannot find High-level programming language of result!",
                amount_of_search_result > 0
        );

        String title_article_mylist = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "text",
                "Cannot find title of article my list",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "Cannot find article in to my list",
                5
        );

        String title_in_to_article = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );


        Assert.assertEquals(
                "Cannot find title 'JavaScript' in article",
                title_article_mylist,
                title_in_to_article
        );


    }
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String error_message)
    {
        return  waitForElementPresent(by, error_message, 5);
    }

    // Это метод который кликает по выбранному элементу
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSecond)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }
    // Ex2 Это функция которая проверяет наличие ожидаемого текста
    private boolean assertElementHasText(By by, String error_message, long timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    protected  void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        /*driver.findElements(by);//Находит сразу все элементы
        driver.findElements(by).size();//Дает количество найденых элементов*/

        int already_swipes = 0;
        while (driver.findElements(by).size()==0) {
            if (already_swipes > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n " + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();

    }

    private  int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_element = getAmountOfElements(by);
        if (amount_of_element > 0) {
            String default_message = "AN element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError( default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message,timeoutInSeconds);
        return element.getAttribute(attribute);

    }
}
