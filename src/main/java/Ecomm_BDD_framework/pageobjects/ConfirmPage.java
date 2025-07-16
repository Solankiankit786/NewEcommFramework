package Ecomm_BDD_framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmPage {

	WebDriver driver;
	public ConfirmPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".woocommerce-notice")
	private WebElement errMsg;
	
	public String confirmMsg()
	{
		String msg = errMsg.getText();
		return msg;
	}

}