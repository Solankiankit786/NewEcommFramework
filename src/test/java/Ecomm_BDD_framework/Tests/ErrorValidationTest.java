package Ecomm_BDD_framework.Tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Ecomm_BDD_framework.pageobjects.CartPage;
import Ecomm_BDD_framework.pageobjects.CataloguePage;
import Ecomm_BDD_framework.testcomponents.BaseTest;


public class ErrorValidationTest extends BaseTest{

	@Test(groups = {"ErrorHandling"})
	public void SubmitOrder()
	{
		lp.loginApp("anki", "anki");
		String errMsg = lp.loginErrMsg();
		System.out.println(errMsg);
		AssertJUnit.assertEquals(errMsg, "Error: The password you entered for the username anki is incorrect. Lost your password?");
	}
	
	@Test(groups = {"ErrorHandling"})
	public void productErrorValidation() throws InterruptedException
	{
		String productName="Basic Blue Jeans";
		CataloguePage cp=lp.loginApp("ram", "ankit");
		lp.gotoHomePage();
		cp.getProductByName(productName);
		cp.AddToCart(productName);
		CartPage cartpage = cp.goToCartPage();
		Boolean match = cartpage.verifyProduct("Basic Blue");
		Assert.assertFalse(match);
	}
}
