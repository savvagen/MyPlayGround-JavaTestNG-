package Utilities;


import junitx.util.PropertyManager;
import org.apache.bcel.generic.BREAKPOINT;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;

public class DriverFactory {

    public static WebDriver webDriver;

    public enum BrowserType{
        FIREFOX("firefox"),
        CHROME("chrome"),
        IE("Internet_Explorer"),
        SAFARI("Safari"),
        OPERA("opera");

        private String value;

        BrowserType(String value){
            this.value = value;
        }

        public String getBrowsername(){
            return this.value;
        }


    }



    public static WebDriver getDriver(BrowserType type){

        WebDriver webDriver = null;

        switch (type){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "/home/savva/IdeaProjects/TestNgProject/drivers/chromedriver");
                webDriver = new ChromeDriver();
                break;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "/home/savva/IdeaProjects/TestNgProject/drivers/geckodriver");
                webDriver = new FirefoxDriver();
                break;

            case OPERA:
                webDriver = new OperaDriver();
                break;

            /*case IE:
                webDriver = new InternetExplorerDriver();

            case SAFARI:
                webDriver = new SafariDriver();*/

            default:
                webDriver = new ChromeDriver();
                break;
        }
        return webDriver;

    }

    public static BrowserType getBrowserTypeByProperty(){
        BrowserType type = null;
        String browsername = PropertyManager.getProperty("BROWSER");
        for (DriverFactory.BrowserType bType: DriverFactory.BrowserType.values()){
            if(bType.getBrowsername().equalsIgnoreCase(browsername)){
                type = bType;
                System.out.println("BROWSER = " + type.getBrowsername());
            }
        }
        return type;
    }

}
