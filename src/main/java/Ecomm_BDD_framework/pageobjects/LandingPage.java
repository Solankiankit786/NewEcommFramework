package Ecomm_BDD_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecomm_BDD_framework.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "reg_username")
	private WebElement reg_name;
	@FindBy(id = "reg_email")
	private WebElement reg_mail;
	@FindBy(id = "reg_password")
	private WebElement reg_paswd;
	@FindBy(name = "register")
	private WebElement reg;
	@FindBy(css = "ul.woocommerce-error")
	private List<WebElement> errors;
	@FindBy(id = "username")
	private WebElement username;
	@FindBy(id = "password")
	private WebElement password;
	@FindBy(name = "login")
	private WebElement loginAccount;
	@FindBy(linkText = "Account")
	private WebElement account;
	@FindBy(linkText = "Home")
	private WebElement home;
	@FindBy(xpath = "(//a[text()='Shop Now'])[1]")
	private WebElement letsShop;
	@FindBy(css = "ul.woocommerce-error")
	private WebElement loginErr;
	
	By clickHome = By.linkText("Home");
	By errorMsg = By.cssSelector("ul.woocommerce-error");

	private String storeUsername;
	private String storePassword;

	public CataloguePage loginApp(String user,String pass)
	{
		username.clear();
		username.sendKeys(user);
		password.clear();
		password.sendKeys(pass);
		loginAccount.click();
		return new CataloguePage(driver);
	}
	public String loginErrMsg()
	{
		return loginErr.getText();
	}
	public CataloguePage Register(String user, String emailId, String pass) throws InterruptedException {
		this.storeUsername = user;
		this.storePassword = pass;

		reg_name.sendKeys(user);
		reg_mail.sendKeys(emailId);
		reg_paswd.sendKeys(pass);
		reg.click();
//		Thread.sleep(2000);
		return new CataloguePage(driver);
	}

	public List<WebElement> getErrors() throws InterruptedException {
		
		return errors;
	}

	public CataloguePage AlreadyRegistered() throws InterruptedException {
		try {
	        // Try to wait for the error message only if it's likely to appear
			
	        WaitForEleToAppear(errorMsg);
//	        Thread.sleep(2000);

	        if (!errors.isEmpty()) {
	            String errorText = errors.get(0).getText();
	            if (errorText.contains("An account is already registered with that username") || errorText.contains("An account is already registered with your email address")) {
	                System.out.println("Email already registered. Proceeding to login...");
	                username.clear();
	                username.sendKeys(storeUsername);
	                password.clear();
	                password.sendKeys(storePassword);
	                loginAccount.click();
	                Thread.sleep(1000);
	            } else {
	                System.out.println("Unexpected error during registration: " + errorText);
	            }
	        }
	    } catch (org.openqa.selenium.TimeoutException e) {
	        // This means the error never appeared, so registration likely succeeded
	        System.out.println("No registration error appeared, assuming registration was successful.");
	    }
	    return new CataloguePage(driver);
	}

	public void goTo() {
		driver.get("https://askomdch.com/");
		account.click();
	}
	
	public CataloguePage gotoHomePage() {
	    WaitForEleTobeClicked(clickHome);
		home.click();
		letsShop.click();
		return new CataloguePage(driver);
	}

}