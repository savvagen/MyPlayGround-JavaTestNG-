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

public class HomePage {

    WebDriver webDriver;
    WebDriverWait wait;


    public static final String HOME_PAGE_URL = "https://chipchin.com.ua/";
    public static final String HOME_PAGE_TITLE = "Главная";

    @FindBy(xpath = "//*/a[@title='Вход']") WebElement singinButton;



    public HomePage(WebDriver driver){
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10, 2);
    }


    public void openHomePage(){
        webDriver.get(HOME_PAGE_URL);
        wait.until(ExpectedConditions.titleIs(HOME_PAGE_TITLE));
    }

    public void pressSinginButton(){
        singinButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email-login")));
    }


    public void searchForHomePageTitle(){
        Assert.assertTrue(webDriver.getTitle().contains(HOME_PAGE_TITLE));
    }

    public void searchForhomepageNewestProducts(){
        List<WebElement> newestProducts = webDriver.findElement(By.className("owl-wrapper")).findElements(By.className("owl-item"));
        int newsetProductsCount = newestProducts.size();
        Assert.assertEquals(2, newsetProductsCount);
    }


    public void searchForTitle(String title){
        Assert.assertTrue(webDriver.getTitle().contains(title));
    }




}
