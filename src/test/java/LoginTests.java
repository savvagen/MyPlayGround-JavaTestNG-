import Utilities.DriverFactory;
import Utilities.Listeners.MyTestListener;
import Utilities.ScreenshotReporter;
import data.LoginData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.BasePage;
import pages.Website;

import java.util.concurrent.TimeUnit;


@Listeners(MyTestListener.class)
public class LoginTests {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static BasePage basePage;
    public static Website website;

    public static DriverFactory.BrowserType type = DriverFactory.BrowserType.FIREFOX;





    @BeforeClass(alwaysRun = true)
    public static void seUpClass(Object[] args) throws Exception {
        // GET BROWSER FROM DRIVER FACTORY
        //driver = DriverFactory.getDriver(type);
        // GET BROWSER FROM LOCAL DIRECTORY
        //driver = new ChromeDriver();
        //GET BROWSER FROM PROPERTY FILE
        driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByProperty());
        // GET BROWSER FROM BROWSER UTILITY
        //driver = Browser.getBrowser("firefox");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        basePage = new BasePage(driver);
        //basePage = PageFactory.initElements(driver, pages.BasePage.class);
        website = new Website(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public static void setUp() throws Exception{    }

    @AfterMethod(alwaysRun = true)
    public static void tearDown(ITestResult testResult) throws Exception {
        if (testResult.getStatus() == ITestResult.FAILURE){
            ScreenshotReporter.tackeScreensghot(driver, testResult.getMethod().getMethodName());
        }

    }

    @AfterClass(alwaysRun = true)
    public static void tearDownClass(Object[] args){
        driver.quit();
    }




    /*@org.testng.annotations.Test(groups = {"p1", "homePageTests"})
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
    }*/

    //ddt from exel sheet
    @Test(dataProvider = "exelData-fromExelUtil", dataProviderClass = LoginData.class)
    public void invalidLoginTestEXEL(String email, String password, String errorMessage){
        website .loginUserWitchIncorrectEmail(email,password,errorMessage);
    }

    //ddt from csv sheet
    @Test(dataProvider = "csvData", dataProviderClass = LoginData.class)
    public void invalidLoginTestCSV(String email, String password, String errorMessage){
        website .loginUserWitchIncorrectEmail(email,password,errorMessage);
    }




}
