package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class ScreenshotReporter {


    public static void tackeScreensghot(WebDriver driver, String screenshotname) throws IOException{
        try
        {
            TakesScreenshot screenshot =(TakesScreenshot)driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/FailedTests/"+screenshotname+".png"));
            System.out.println("Screenshot Taken");
        }catch(Exception excep) {
            System.out.println("Throwing exception while taking screenshot" +excep.getMessage());
        }


        /*if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String currentDir = "/home/savva/IdeaProjects/TestNgProject/Screenshots/";
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            FileUtils.copyFile(scrFile, new File(currentDir + testResult.getMethod() + timeStamp + ".png"));
            System.out.println("************Placed screenshot placed in "+currentDir+" *************");
        }*/


    }


}
