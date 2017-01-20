package Utilities;


import junitx.util.PropertyManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.security.krb5.internal.crypto.Des;

import java.net.URL;

public class DriverFactory {



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



    public static WebDriver getDriver(BrowserType type) throws Exception{


        if (PropertyManager.getProperty("USE_GRID").equalsIgnoreCase("true")){

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(type.getBrowsername());
            desiredCapabilities.setPlatform(Platform.LINUX);
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
        }


        switch (type){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "/home/savva/IdeaProjects/TestNgProject/drivers/chromedriver");
                return new ChromeDriver();
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "/home/savva/IdeaProjects/TestNgProject/drivers/geckodriver");
                return new FirefoxDriver();
            case OPERA:
                System.setProperty("webdriver.opera.driver", "/home/savva/IdeaProjects/TestNgProject/drivers/operadriver");
                return new OperaDriver();
            /*case IE:
                return new InternetExplorerDriver();
            case SAFARI:
                return new SafariDriver();*/
            default:
                return new ChromeDriver();
        }
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
