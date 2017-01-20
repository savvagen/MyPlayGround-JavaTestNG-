import Utilities.DriverFactory;
import Utilities.Listeners.MyTestListener;
import Utilities.ScreenshotReporter;
import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName;
import data.LoginData;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Website;
import sun.security.krb5.internal.crypto.Des;

import javax.xml.crypto.URIReferenceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

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


   /* @BeforeMethod(alwaysRun = true)
    public static void setUp() throws Exception{

        DesiredCapabilities cap = new DesiredCapabilities().chrome();
        cap.setPlatform(Platform.LINUX);
        cap.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://192.168.0.139:5555/wd/hub"), cap);





        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        basePage = new BasePage(driver);
        //basePage = PageFactory.initElements(driver, pages.BasePage.class);
        website = new Website(driver);

    }


    @AfterMethod(alwaysRun = true)
    public static void tearDown(ITestResult testResult) throws Exception {
        if (testResult.getStatus() == ITestResult.FAILURE){
            ScreenshotReporter.tackeScreensghot(driver, testResult.getMethod().getMethodName());
        }
        driver.quit();
    }




    @org.testng.annotations.Test(groups = {"p1", "homePageTests"})
    public void homepageVerification(){
        basePage.openHomePage();
        basePage.searchForHomePageTitle();

    }

    @org.testng.annotations.Test(groups = {"p1", "homePageTests"}, dependsOnMethods = "LoginTests.homepageVerification")
    public void homePageProducts(){
        basePage.openHomePage();
        basePage.searchForhomepageNewestProducts();
    }



    @org.testng.annotations.Test(groups = {"p2", "loginPageTests"}, dependsOnMethods = "LoginTests.homepageVerification")  //dependsOnGroups = "ClassName.groupName")
    public void positiveLogin(){
        basePage.openLoginPage();
        basePage.loginUser("genchevskiy@singree.com", "19021992qa");
        basePage.searchForPositiveLoginResults();
        website.loginPage().clickLogoutButton();
    }

    @org.testng.annotations.Test(groups = {"p2", "loginPageTests"}, dependsOnMethods = "LoginTests.homepageVerification", dataProvider = "invalidEmailData", dataProviderClass = LoginData.class)
    public void invalidLogin(String email, String password, String errorMessage){
        basePage.openLoginPage();
        basePage.loginWithInvalidData(email, password);
        basePage.searchForErrorMessage(errorMessage);
    }
    */



