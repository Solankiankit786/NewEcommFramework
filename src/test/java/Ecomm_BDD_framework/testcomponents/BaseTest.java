package Ecomm_BDD_framework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Ecomm_BDD_framework.data.DataReader;
import Ecomm_BDD_framework.pageobjects.LandingPage;

public class BaseTest extends DataReader {

	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initialization() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Ecomm_BDD_framework/resources/Global.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		
//		String browserName= prop.getProperty("browserName");
		if (browserName == null) {
		    throw new RuntimeException("Browser name not specified in System properties or Global.properties");
		}
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
	            put("credentials_enable_service", false);
	            put("profile.password_manager_enabled", false);
	        }});
	        options.addArguments("--disable-notifications");
	        options.addArguments("--disable-popup-blocking");
	        options.addArguments("--disable-infobars");
	        options.addArguments("--disable-save-password-bubble");
	        options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	public String getScreenShot(String testcasename,WebDriver driver) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException
	{
		try {
	        driver = initialization();
	        driver.manage().deleteAllCookies();
	        lp = new LandingPage(driver);
	        lp.goTo();
	        return lp;
	    } catch (Exception e) {
	        System.err.println("🔥 Error in @BeforeMethod: " + e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException(e); // let TestNG know it failed
	    }
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
}
