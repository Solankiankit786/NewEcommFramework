package Ecomm_BDD_framework.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import Ecomm_BDD_framework.pageobjects.CartPage;
import Ecomm_BDD_framework.pageobjects.CataloguePage;
import Ecomm_BDD_framework.pageobjects.CheckoutPage;
import Ecomm_BDD_framework.pageobjects.ConfirmPage;
import Ecomm_BDD_framework.pageobjects.LandingPage;
import Ecomm_BDD_framework.testcomponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	
	public ConfirmPage confPage; 
	public CheckoutPage checkoutPage;
	public CartPage cartpage;
	public CataloguePage cp;
	public LandingPage lp;
	
	@Given("I landed on ecomm website page")
	public void i_landed_on_ecomm_website_page() throws IOException {
	    
		lp = launchApp();
	}
	@Given("^User registers with name (.+), email (.+) and password (.+)$")
	public void user_registers_with_name_email_and_password(String name, String email, String password) throws InterruptedException {
	    
		cp = lp.Register(name, email, password);
		lp.AlreadyRegistered();

	}
	@When("^User adds product (.+) to the cart$")
	public void user_adds_product_to_the_cart(String productName) throws InterruptedException {
	    
		lp.gotoHomePage();
		cp.getProductByName(productName);
		cp.AddToCart(productName);
	}
	@When("User proceeds to checkout")
	public void user_proceeds_to_checkout() throws InterruptedException {
		cartpage = cp.goToCartPage();
		cartpage.updateCart(2);
		checkoutPage = cartpage.goToCheckout();
	}
	@When("^User enters shipping details with first name (.+), last name (.+), country (.+), address (.+), city (.+), state (.+), and pin (.+)$")
	public void user_enters_shipping_details_with_first_name_last_name_country_address_city_state_and_pin(String Fname, String Lname, String country, String address, String city, String state, String pin) throws NumberFormatException, InterruptedException {
	    
		checkoutPage.BillingDetails(Fname,Lname,country);
		checkoutPage.BillingAddressDetail(address,city,state, Integer.parseInt(pin));
	}
	@When("User selects {string} as payment method")
	public void user_selects_as_payment_method(String string) {
	    
		checkoutPage.PaymentMethod();
	}
	@When("User places the order")
	public void user_places_the_order() throws InterruptedException {
		confPage = checkoutPage.PlaceOrder();
	}
	@Then("Order confirmation message should be {string}")
	public void order_confirmation_message_should_be(String expectesmsg) {
	    
		String msg = confPage.confirmMsg();
		Assert.assertEquals(msg, expectesmsg);
		tearDown();
	}
	@When("^User update the product with (.+)$")
	public void user_update_the_product_with(String no) throws InterruptedException {
		cartpage = cp.goToCartPage();
		cartpage.updateCart(Integer.parseInt(no));
	}

	@Then("^Verify the product (.+)$")
	public void verify_the_product_basic_jeans(String checkproductName) throws InterruptedException {
		cartpage = cp.goToCartPage();
		Boolean match = cartpage.verifyProduct(checkproductName);
		Assert.assertFalse(match,"Product [" + checkproductName + "] should NOT be in the cart!");
		tearDown();
	}
}
