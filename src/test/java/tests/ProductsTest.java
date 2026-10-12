package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utility.ConfigReader;

public class ProductsTest extends BaseTest {

	@Test(priority = 1)
	public void verifyProductCount() {
		int actualProductCount = new LoginPage(driver)
				.validLogin(ConfigReader.getValue("validUsername"), ConfigReader.getValue("validPassword"))
				.getProductCount();
		int expectedProductCount = Integer.parseInt(ConfigReader.getValue("productCount"));
		Assert.assertEquals(actualProductCount, expectedProductCount,
				"Products count on the Products page a mis-match.");
	}

	@Test(priority = 2)
	public void verifyProductIsPresent() {
		boolean actualResult = new LoginPage(driver)
				.validLogin(ConfigReader.getValue("validUsername"), ConfigReader.getValue("validPassword"))
				.isProductPresent(ConfigReader.getValue("productName"));
		Assert.assertTrue(actualResult, "Product missing on the Products page.");
	}
}
