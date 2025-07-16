package Ecomm_BDD_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecomm_BDD_framework.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".cart_item")
	private List<WebElement> cartProducts;
	@FindBy(xpath = "(//td/div/input[1])[1]")
	private WebElement updateValue;
	@FindBy(name = "update_cart")
	private WebElement updateCart;
	@FindBy(name = "coupon_code")
	private WebElement couponCode;
	@FindBy(name = "apply_coupon")
	private WebElement applyCoupon;
	@FindBy(css = ".woocommerce-error")
	private WebElement Msgerror;
	@FindBy(css = ".checkout-button")
	private WebElement checkOutBtn;
	
	By errorMsg = By.cssSelector(".woocommerce-error");
	By itemName = By.cssSelector(".product-name");
	
	public List<WebElement> getCartProducts()
	{
		return cartProducts;
	}
	public Boolean verifyProduct(String productName)
	{
		Boolean match = getCartProducts().stream().anyMatch(s->s.findElement(itemName).getText().equalsIgnoreCase(productName));
		return match;
	}
	public void updateCart(int item)
	{
		updateValue.clear();
		updateValue.sendKeys(String.valueOf(item));
		updateCart.click();
	}
	public String ApplyCoupon() throws InterruptedException
	{
		Thread.sleep(2000);
		couponCode.sendKeys("abc123");
		applyCoupon.click();
		WaitForEleToAppear(errorMsg);
		String err = Msgerror.getText();
		return err;
	}
	public CheckoutPage goToCheckout() throws InterruptedException
	{
		scrollDown();
		Thread.sleep(2000);
		checkOutBtn.click();
		return new CheckoutPage(driver);
	}

}