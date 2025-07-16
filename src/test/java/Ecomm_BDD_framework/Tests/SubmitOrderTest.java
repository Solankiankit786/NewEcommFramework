package Ecomm_BDD_framework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ecomm_BDD_framework.testcomponents.BaseTest;
import Ecomm_BDD_framework.testcomponents.Retry;
import Ecomm_BDD_framework.pageobjects.CataloguePage;
import Ecomm_BDD_framework.pageobjects.CartPage;
import Ecomm_BDD_framework.pageobjects.CheckoutPage;
import Ecomm_BDD_framework.pageobjects.ConfirmPage;



public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData",groups = {"sanity"},retryAnalyzer = Retry.class)
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException {
		// TODO Auto-generated method stub
		CataloguePage cp = lp.Register(input.get("name"), input.get("email"), input.get("password"));
		lp.AlreadyRegistered();
		lp.gotoHomePage();
		cp.getProductByName(input.get("productName"));
		cp.AddToCart(input.get("productName"));
		CartPage cartpage = cp.goToCartPage();
		Boolean match = cartpage.verifyProduct(input.get("productName"));
		Assert.assertTrue(match);
		cartpage.updateCart(2);
		String errMsg = cartpage.ApplyCoupon();
		Assert.assertEquals(errMsg, "Coupon \"abc123\" does not exist!");
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.BillingDetails(input.get("firstName"), input.get("lastName"), input.get("country"));
		checkoutPage.BillingAddressDetail(input.get("address"), input.get("city"), input.get("state"), Integer.parseInt(input.get("pin")));
		checkoutPage.PaymentMethod();
		ConfirmPage confPage = checkoutPage.PlaceOrder();
		String msg = confPage.confirmMsg();
		AssertJUnit.assertEquals(msg, "Thank you. Your order has been received.");

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Ecomm_BDD_framework//Data//JasonData.json");
		
		return new Object[][] { {data.get(0) }, {data.get(1)} };
	}

}
