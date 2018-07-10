import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class RmsysTest {

    private WebDriver driver;
    private final String URL = "https://192.168.100.26/";
    private final String userName = "EugenBorisik";
    private final String password = "qwerty12345";
    private final String URL_HOME_PAGE = "https://192.168.100.26/Home/Index";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void rmsysLoginWithIdLocatorTest() {
        WebElement userNameFildByIdLocator = driver.findElement(By.id("Username"));
        WebElement passwordFildByIdLocator = driver.findElement(By.id("Password"));

        userNameFildByIdLocator.sendKeys(userName);
        passwordFildByIdLocator.sendKeys(password);
        passwordFildByIdLocator.submit();

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "target/screenshots/" + screenshot.getName();
        try {
            FileUtils.copyFile(screenshot, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), URL_HOME_PAGE);
    }
}
