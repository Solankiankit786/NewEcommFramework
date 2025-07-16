package Ecomm_BDD_framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecomm_BDD_framework.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "billing_first_name")
	private WebElement firstName;
	@FindBy(id = "billing_last_name")
	private WebElement lastName;
	@FindBy(id = "select2-billing_country-container")
	private WebElement selectCountry;
	@FindBy(css = ".select2-search__field")
	private WebElement enterName;
	@FindBy(css = ".select2-results__option")
	private List<WebElement> countries;
	@FindBy(id = "billing_address_1")
	private WebElement address;
	@FindBy(id = "billing_city")
	private WebElement city;
	@FindBy(id = "select2-billing_state-container")
	private WebElement selectState;
	@FindBy(css = ".select2-search__field")
	private WebElement enterStateName;
	@FindBy(css = ".select2-results__option:last-child")
	private WebElement selState;
	@FindBy(id ="billing_postcode")
	private WebElement postCode;
	@FindBy(name ="payment_method")
	private WebElement PayUsing;
	@FindBy(id = "place_order")
	private WebElement placeOrder;
	
	By postCodeSync= By.id("billing_postcode");
	
	public List<WebElement> getCountryList()
	{
		return countries;
	}
	public void BillingDetails(String firstname,String lastname,String countryName)
	{
		firstName.clear();
		firstName.sendKeys(firstname);
		lastName.clear();
		lastName.sendKeys(lastname);
		selectCountry.click();
		enterName.sendKeys(countryName);
		WebElement country = getCountryList().stream().filter(s->s.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		country.click();
	}
	public void BillingAddressDetail(String Add,String City,String stateName,int pin) throws InterruptedException
	{
		address.clear();
		address.sendKeys(Add);
		city.clear();
		city.sendKeys(City);
		selectState.click();
		Actions a = new Actions(driver);
		a.sendKeys(enterStateName, stateName).build().perform();
		Thread.sleep(2000);
		selState.click();
		WaitForEleTobeClicked(postCodeSync);
		postCode.clear();
		postCode.click();
		postCode.sendKeys(String.valueOf(pin));
	}
	public void PaymentMethod()
	{
		PayUsing.click();
	}

	public ConfirmPage PlaceOrder() throws InterruptedException
	{
		Thread.sleep(1000);
		placeOrder.click();
		return new ConfirmPage(driver);
	}

}