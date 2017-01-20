package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Website {


    WebDriver webDriver;
    private WebDriverWait wait;


    public Website(WebDriver driver){
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 10, 2);

    }

    public HomePage homePage(){return new HomePage(webDriver);}
    public LoginPage loginPage(){return new LoginPage(webDriver);}

    public void loginUserWithCredantials(String email, String password){
        loginPage().openLoginPage();
        loginPage().loginUser(email, password);
        loginPage().searchForPositiveLoginResults();
        loginPage().verifyLoginTest();
        loginPage().clickLogoutButton();
    }

    public void loginUserWitchIncorrectEmail(String email, String password, String error){
        loginPage().openLoginPage();
        loginPage().emailField.clear();
        loginPage().passwordField.clear();
        loginPage().loginWithInvalidData(email, password);
        loginPage().searchForErrorMessage(error);
    }


}
