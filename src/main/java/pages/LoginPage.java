package pages;

import Utilities.MyWaits;
import org.omg.CORBA.portable.ValueOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class LoginPage {


    WebDriver webDriver;
    WebDriverWait wait;

    public static final String LOGIN_PAGE_URL = "https://chipchin.com.ua/account/signin/";
    public static final String LOGIN_PAGE_TITLE = "Вход";



    @FindBy(id = "email-login")
    WebElement emailField;

    @FindBy(id = "password-login") WebElement passwordField;

    @FindBy(id = "submit-login") WebElement submitButton;

    @FindBy(className = "error-input") WebElement errorMessage;

    @FindBy(xpath = "//*/li/a[@title='Выйти']") WebElement logoutButton;


    public LoginPage(WebDriver driver){//WebDriver driver){
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10, 2);
    }




    public void openLoginPage(){
        webDriver.get(LOGIN_PAGE_URL);
        wait.until(ExpectedConditions.titleIs(LOGIN_PAGE_TITLE));
    }


    public void enterPassword(String password){
        passwordField.sendKeys(password);
    }


    public void enterEmail(String email){
        emailField.sendKeys(email);
    }

    public void pressSubmitButton(){
        submitButton.click();
    }


    public void loginUser(String email, String password){
        enterEmail(email);
        enterPassword(password);
        pressSubmitButton();
        wait.until(ExpectedConditions.titleIs("Личный кабинет"));
    }

    public void searchForPositiveLoginResults(){
        wait.until(MyWaits.visibilityOfElement(logoutButton));
        System.out.println("LOGOUT BUTTON FINDED!!!!!!!!!");
        Assert.assertTrue(webDriver.findElement(By.xpath("//*/li/a[@title='Выйти']")).isDisplayed());
        Assert.assertTrue(webDriver.getTitle().contains("Личный кабинет"));
        String userName = webDriver.findElement(By.xpath(".//*[@id='personal-table']/tbody/tr[1]/td[2]")).getText();
        Assert.assertEquals("Savva Genchevskiy", userName);
    }

    public void loginWithInvalidData(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        pressSubmitButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-input")));
    }

    public void searchForErrorMessage(String errorMessage) {
        Assert.assertEquals(errorMessage, webDriver.findElement(By.className("error-input")).getText());
        Assert.assertTrue(webDriver.getTitle().contains("Вход"));
    }

    public boolean verifyLoginTest(){
        try{
            wait.until(MyWaits.visibilityOfElement(logoutButton));
            Assert.assertTrue(webDriver.findElement(By.xpath("//*/li/a[@title='Выйти']")).isDisplayed());
            Assert.assertTrue(webDriver.getTitle().contains("Личный кабинет"));
            return true;
        } catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        } catch (AssertionError e1){
            e1.getMessage();
            System.out.println("verification Failed" + e1.getMessage());
            return false;
        } finally {
            webDriver.navigate().refresh();
        }
    }

    public void verify2LoginTests(){
        try {
            wait.until(MyWaits.visibilityOfElement(logoutButton));
            Assert.assertTrue(webDriver.findElement(By.xpath("//*/li/a[@title='Выйти']")).isDisplayed());
            Assert.assertTrue(webDriver.getTitle().contains("Личный кабинет"));
        } catch (AssertionError e1){
            System.out.println("verification Failed" + e1.getMessage());
        } finally {
            webDriver.navigate().refresh();
        }
    }

    public void clickLogoutButton(){
        logoutButton.click();
        wait.until(ExpectedConditions.titleContains("Главная"));
    }



}
