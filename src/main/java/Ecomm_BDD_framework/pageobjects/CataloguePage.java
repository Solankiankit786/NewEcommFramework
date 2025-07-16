package Ecomm_BDD_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecomm_BDD_framework.abstractcomponents.AbstractComponent;

public class CataloguePage extends AbstractComponent {

	WebDriver driver;
	public CataloguePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".product")
	private List<WebElement> productList;
	@FindBy(xpath = "(//div[@class='ast-cart-menu-wrap'])[1]")
	private WebElement goToCartPg;
	
	By productTitle = By.cssSelector(".woocommerce-loop-product__title");
	By addToCart = By.cssSelector(".add_to_cart_button");
	
	public List<WebElement> getProductList()
	{
		return productList;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(s->s.findElement(productTitle).getText()
				.equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void AddToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		WaitForEleToAppear(addToCart);
		prod.findElement(addToCart).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException
	{
		Thread.sleep(2000);
		goToCartPg.click();
		return new CartPage(driver);
	}

}