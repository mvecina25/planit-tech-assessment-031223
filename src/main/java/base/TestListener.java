package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import static base.InitializeLogging.*;

public class TestListener implements ITestListener {

	String filePath = "./test-output/screenshots/";
		
	@Override
	public void onTestFailure(ITestResult result) {
    	log.error("***** ERROR: " + result.getName() + " has FAILED *****");
    	
    	// Accessing the driver object that we added in the setUp method
    	String methodName = result.getName().toString().trim();    	
        ITestContext context = result.getTestContext();        
        WebDriver driver = (WebDriver)context.getAttribute("driver");       
    	takeScreenShot(methodName, driver);
    }
	
	public void takeScreenShot(String methodName, WebDriver driverEvent) {
		
   	 	File screenShotFile = ((TakesScreenshot)driverEvent).getScreenshotAs(OutputType.FILE);
   	 
        // The below method will save the screen shot in the report folder under class with test method name 
   	 	try {
			FileUtils.copyFile(screenShotFile, new File(filePath + methodName + ".png"));
			log.info(" *** Placed screen shot in "+ filePath + " ***");				
		} catch (IOException e) {				
			e.printStackTrace();
		}
   }
	
	// Set the driver object to the ITestContext
	public static ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
        iTestContext.setAttribute("driver", driver);
        return iTestContext;
    }
	

}
