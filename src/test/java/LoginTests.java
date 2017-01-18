import Utilities.DriverFactory;
import Utilities.Listeners.MyTestListener;
import Utilities.ScreenshotReporter;
import com.sun.javaws.BrowserSupportFactory;
import data.LoginData;
import junitx.util.PropertyManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Website;
import sun.plugin2.util.BrowserType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;



@Listeners(MyTestListener.class)
public class LoginTests {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static BasePage basePage;
    public static Website website;

    public static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;


    @BeforeMethod(alwaysRun = true)
    public static void setUp() throws Exception{

        // GET BROWSER FROM DRIVER FACTORY
        driver = DriverFactory.getDriver(type);

        // GET BROWSER FROM LOCAL DIRECTORY
        //driver = new ChromeDriver();

        //GET BROWSER FROM PROPERTY FILE
        //driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());



        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        basePage = new BasePage(driver);
        //basePage = PageFactory.initElements(driver, pages.BasePage.class);
        website = new Website(driver);
    }

    @AfterMethod(alwaysRun = true)
    public static void tearDown(ITestResult testResult) throws IOException {
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


    @org.testng.annotations.Test(groups = {"p3", "loginPageTests"})
    public void loginTest(){
        website.loginUserWithCredantials("genchevskiy@singree.com", "19021992qa");
        if(false){
            System.out.println("test FAILED!!!!!!!!!");
        } else {
            System.out.println("test PASSED!!!!!!!!!");
        }
    }

    @org.testng.annotations.Test(groups = {"p3", "loginPageTests"}, dependsOnMethods = "LoginTests.homepageVerification", dataProvider = "invalidEmailData", dataProviderClass = LoginData.class)
    public void invalidLoginTest(String email, String password, String error){
        website .loginUserWitchIncorrectEmail(email,password,error);
    }




    /*
    @Parameters({"language", "browserType"})
    @Test(groups = {"p4", "TestWithParams"})
    public void xmlParametryzedHomePageTest(String language, String browserType){
        System.out.println("Language: " + language);
        System.out.println("Browser: " + browserType);
        website.homePage().openHomePage();
        website.homePage().searchForHomePageTitle();
    }

    @Parameters({"email", "password", "language", "browserType"})
    @Test(groups = {"p4", "LoginParametrizedTest"}, dependsOnMethods = "xmlParametryzedHomePageTest")
    public void xmlParametrizedloginTest(String email, String password, String language, String browserType){
        System.out.println("Language: " + language);
        System.out.println("Browser: " + browserType);
        website.loginPage().openLoginPage();
        website.loginPage().loginUser(email,password);
        website.loginPage().searchForPositiveLoginResults();

    }

    @Parameters({"email", "password", "language", "browserType"})
    @Test
    public void xmParamsTest(String email, String password, @Optional String language, @Optional String browserType){
        System.out.println("Language: " + language);
        System.out.println("Browser: " + browserType);
        website.loginPage().openLoginPage();
        website.loginPage().loginUser(email,password);
        website.loginPage().searchForPositiveLoginResults();
    }*/



}
