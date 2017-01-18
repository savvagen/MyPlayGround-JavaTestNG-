package Utilities.Listeners;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTestListener extends TestListenerAdapter{

    public static TestResults results = new TestResults();

    WebDriver driver = null;


    @Override
    public void onTestSuccess(ITestResult testResult) {
        System.out.println(testResult.getName() + " was successfull!!!");
        //WRITE TO EXEL SHEET THE RESULTS
        results.incrementPassed();
        System.out.println("Passed Tests: " + results.getPassed());
        System.out.println("Total Tests: " + results.getTotalTests());
    }


    @Override
    public void onTestFailure(ITestResult testResult) {
        System.out.println(testResult.getName() + " failed!!!" + testResult.getThrowable());
        //WRITE TO EXEL SHEET THE RESULTS
        results.incrementFailed();
        System.out.println("Failed Tests: " + results.getFailed());
        System.out.println("Total Tests: " + results.getTotalTests());

    }


    @Override
    public void onTestSkipped(ITestResult testResult){
        System.out.println(testResult.getName() + " was skipped!!!");
        //WRITE TO EXEL SHEET THE RESULTS
        results.incrementSkipped();
        System.out.println("Skipped Tests: " + results.getSkipped());
        System.out.println("Total Tests: " + results.getTotalTests());
    }


    @Override
    public void onFinish(ITestContext testContext){
        System.out.println("Passed Tests: " + results.getPassed());
        System.out.println("Failed Tests: " + results.getFailed());
        System.out.println("Skipped Tests: " + results.getSkipped());
        System.out.println("Total Tests: " + results.getTotalTests());
        try {
            results.writeToSheet();
        } catch (IOException e)
            { e.printStackTrace();
        }

    }



       /* String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        //String methodName = testResult.getName();
        String currentDir = "/home/savva/IdeaProjects/TestNgProject/Screenshots/";
        if(!testResult.isSuccess()){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                org.apache.commons.io.FileUtils.copyFile(scrFile, new File(currentDir + testResult.getMethod() + timeStamp + ".png"));
                System.out.println("************Placed screenshot placed in " + currentDir + " *************");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/



}