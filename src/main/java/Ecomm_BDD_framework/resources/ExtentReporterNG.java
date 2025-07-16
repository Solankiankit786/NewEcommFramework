package Ecomm_BDD_framework.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getExtentReports()
	{
		ExtentSparkReporter sparker = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"//reports//index.html"));
		sparker.config().setDocumentTitle("Automation Results");
		sparker.config().setReportName("Ecomm Automation Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparker);
		extent.setSystemInfo("Tester Name", "Ankit Solanki");
		
		return extent;
	}
}
