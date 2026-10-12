package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utility.ConfigReader;

public class LogoutTest extends BaseTest {

	@Test(priority = 1)
	public void verifyLogoutFunctionality() {
		boolean actualResult = new LoginPage(driver)
				.validLogin(ConfigReader.getValue("validUsername"), ConfigReader.getValue("validPassword"))
				.clickLogout()
				.isLoginPageDisplayed();
		Assert.assertTrue(actualResult, "Login page is not displayed.");
	}
}
