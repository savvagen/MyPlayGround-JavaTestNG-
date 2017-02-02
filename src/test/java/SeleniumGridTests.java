import Utilities.Listeners.MyTestListener;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Website;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners(MyTestListener.class)
public class SeleniumGridTests {



    public static RemoteWebDriver driver;
    //public static WebDriver driver;
    public static WebDriverWait wait;
    public static BasePage basePage;
    public static Website website;


    @Parameters({"browserName", "platform", "hostName"})
    @BeforeMethod
    public  static void main(String browserName, String platform, String hostName) throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browserName);
        capabilities.setPlatform(Platform.extractFromSysProperty(platform));
        //capabilities.setBrowserName("firefox");
        //capabilities.setPlatform(Platform.LINUX);
        driver = new RemoteWebDriver(new URL(hostName + "/wd/hub"), capabilities);

    }


    @Test
    public void test() throws InterruptedException {
        driver.get("https://google.com.ua/");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getTitle().contains("Google"));
        //website.loginUserWithCredantials("genchevskiy@singree.com", "19021992qa"); THIS IS NOT WORKING BECAUSE DRIVER IS NOT REMOTE-WEBDRIVER
    }


    @AfterMethod
    public static void tearDown() throws MalformedURLException{
        driver.quit();
    }

}



