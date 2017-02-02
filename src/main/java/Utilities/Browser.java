package Utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

    public static String driverPath = "C:\\Users\\savva\\IdeaProjects\\TestNgProject\\drivers\\";

    public static WebDriver driver;


    public static WebDriver getBrowser(String browserType) {
        switch (browserType) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                return new FirefoxDriver();
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
            case "opera":
                System.setProperty("webdriver.opera.driver", driverPath + "operadriver.exe");
            default:
                return driver = new ChromeDriver();
        }

    }




}
