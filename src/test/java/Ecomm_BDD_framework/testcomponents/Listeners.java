package Ecomm_BDD_framework.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Ecomm_BDD_framework.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test ;
	ExtentReports extent = ExtentReporterNG.getExtentReports();
	
	@Override
	public void onTestStart(ITestResult result) {
	    test = extent.createTest(result.getMethod().getMethodName());
	    System.out.println("Executing Test: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    System.out.println("Test Passed: " + result.getName());
	    test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail("Test Failed: "+result.getThrowable().getMessage());
		test.fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String filePath="null";
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		
	   
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	    System.out.println("Test Skipped: " + result.getName());
	}

	// Optional: Remaining methods

	@Override
	public void onStart(ITestContext context) {}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {}

}
