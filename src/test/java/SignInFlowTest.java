import Utilities.DriverFactory;
import Utilities.ScreenshotReporter;
import data.LoginData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Website;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SignInFlowTest {


    public static WebDriver driver;
    public static WebDriverWait wait;

    public static BasePage basePage;
    public static Website website;

    public static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;



    HashMap<String, String> invalidLoginDataMap;


    @Factory(dataProvider = "invalidLoginData",dataProviderClass = LoginData.class)
    public SignInFlowTest(String pageTitle, String email, String password, String errorMessage){
        invalidLoginDataMap =new HashMap<String, String>();
        invalidLoginDataMap.put("pageTitle", pageTitle);
        invalidLoginDataMap.put("email", email);
        invalidLoginDataMap.put("password", password);
        invalidLoginDataMap.put("errorMessage", errorMessage);

    }



    @BeforeClass(alwaysRun = true)
    public static void setUp(){
        // GET BROWSER FROM DRIVER FACTORY
        //driver = DriverFactory.getDriver(type);

        // GET BROWSER FROM LOCAL DIRECTORY
        //driver = new ChromeDriver();

        //GET BROWSER FROM PROPERTY FILE
        //driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);


        basePage = new BasePage(driver);
        //basePage = PageFactory.initElements(driver, pages.BasePage.class);
        website  = new Website(driver);
    }

    @AfterClass(alwaysRun = true)
    public static void tearDown(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE){
            ScreenshotReporter.tackeScreensghot(driver, testResult.getMethod().getMethodName());
        }
        driver.quit();
    }



    @org.testng.annotations.Test(groups = {"g1"})
    public void loadHomePage(){
        website.homePage().openHomePage();
        website.homePage().searchForHomePageTitle();
        website.homePage().searchForTitle(invalidLoginDataMap.get("pageTitle"));
    }

    @org.testng.annotations.Test(groups = {"g1"}, dependsOnMethods = "LoginTests3.loadHomePage")
    public void setInvalidData(){
        website.loginPage().openLoginPage();
        website.loginPage().loginWithInvalidData(invalidLoginDataMap.get("email"), invalidLoginDataMap.get("password"));
    }

    @Test(groups = {"g1"}, dependsOnMethods = "LoginTests3.setInvalidData")
    public void checkInvaldlogin(){
        website.loginPage().searchForErrorMessage(invalidLoginDataMap.get("errorMessage"));
    }









}
