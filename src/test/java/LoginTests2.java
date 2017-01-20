import Utilities.DriverFactory;
import Utilities.Listeners.MyTestListener;
import Utilities.ScreenshotReporter;
import data.LoginData;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Website;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners(MyTestListener.class)
public class LoginTests2 {



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
        website  = new Website(driver);

    }

    @AfterMethod(alwaysRun = true)
    public static void tearDown(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE){
            ScreenshotReporter.tackeScreensghot(driver, testResult.getMethod().getMethodName());
        }
        if (driver != null) {
            driver.quit();

        }
    }


    /*@org.testng.annotations.Test
    public void homepageVerification(){
        basePage.openHomePage();
        basePage.searchForHomePageTitle();

    }

    @org.testng.annotations.Test
    public void homePageProducts(){
        basePage.openHomePage();
        basePage.searchForhomepageNewestProducts();
    }*/

    @org.testng.annotations.Test
    public void positiveLogin(){
        basePage.openLoginPage();
        basePage.loginUser("genchevskiy@singree.com", "19021992qaqq");
        basePage.searchForPositiveLoginResults();
        website.loginPage().clickLogoutButton();
    }

    /*@org.testng.annotations.Test(dataProvider = "invalidEmailData", dataProviderClass = LoginData.class)
    public void invalidLogin(String email, String password, String errorMessage){
        basePage.openLoginPage();
        basePage.loginWithInvalidData(email, password);
        basePage.searchForErrorMessage(errorMessage);
    }

    @org.testng.annotations.Test
    public void loginTest(){
        website.loginUserWithCredantials("genchevskiy@singree.com", "19021992qa");
    }*/





}
