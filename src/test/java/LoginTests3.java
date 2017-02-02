import Utilities.Browser;
import Utilities.DriverFactory;
import Utilities.Listeners.MyTestListener;
import Utilities.ScreenshotReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Website;

import java.util.concurrent.TimeUnit;

@Listeners(MyTestListener.class)
public class LoginTests3 {


    public static WebDriver driver;
    public static WebDriverWait wait;
    public static BasePage basePage;
    public static Website website;

    public static DriverFactory.BrowserType type = DriverFactory.BrowserType.CHROME;


    @BeforeMethod(alwaysRun = true)
    public static void setUp() throws Exception{

        // GET BROWSER FROM DRIVER FACTORY
        //driver = DriverFactory.getDriver(type);
        // GET BROWSER FROM LOCAL DIRECTORY
        //driver = new ChromeDriver();
        //GET BROWSER FROM PROPERTY FILE
        //driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());
        // GET BROWSER FROM BROWSER UTILITY
        driver = Browser.getBrowser("firefox");
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
    }



}
