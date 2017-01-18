package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BasePage {

    WebDriver webDriver;
    WebDriverWait wait;

    public static final String LOGIN_PAGE_URL = "https://chipchin.com.ua/account/signin/";
    public static final String HOME_PAGE_URL = "https://chipchin.com.ua/";
    public static final String LOGIN_PAGE_TITLE = "Вход";
    public static final String HOME_PAGE_TITLE = "Главная";


    @FindBy(id = "email-login")
    WebElement emailField;

    @FindBy(id = "password-login") WebElement passwordField;

    @FindBy(id = "submit-login") WebElement submitButton;

    @FindBy(className = "error-input") WebElement errorMessage;


    public BasePage(WebDriver driver){
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10, 2);
    }


    public void openHomePage(){
        webDriver.get(HOME_PAGE_URL);
        wait.until(ExpectedConditions.titleIs(HOME_PAGE_TITLE));
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

    public void searchForHomePageTitle(){
        Assert.assertTrue(webDriver.getTitle().contains(HOME_PAGE_TITLE));
    }

    public void searchForhomepageNewestProducts(){
        List<WebElement> newestProducts = webDriver.findElement(By.className("owl-wrapper")).findElements(By.className("owl-item"));
        int newsetProductsCount = newestProducts.size();
        Assert.assertEquals(2, newsetProductsCount);
    }

    public void loginUser(String email, String password){
        enterEmail(email);
        enterPassword(password);
        pressSubmitButton();
        wait.until(ExpectedConditions.titleIs("Личный кабинет"));
    }

    public void searchForPositiveLoginResults(){
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



}
