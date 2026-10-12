package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utility.ConfigReader;

public class LoginTest extends BaseTest {

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {
		String actualPageHeader = new LoginPage(driver)
				.validLogin(ConfigReader.getValue("validUsername"), ConfigReader.getValue("validPassword"))
				.getPageHeader();
		String expectedPageHeader = ConfigReader.getValue("productsPageHeader");
		Assert.assertEquals(actualPageHeader, expectedPageHeader, "Page header mis-match.");
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		String actualErrorMessage = new LoginPage(driver)
				.invalidLogin(ConfigReader.getValue("invalidUsername"), ConfigReader.getValue("invalidPassword"))
				.getErrorMessage();
		String expectedErrorMessage = ConfigReader.getValue("invalidUsernameError");
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mis-match.");
	}

	@Test(priority = 3)
	public void verifyLoginWithLockedAccountCredentials() {
		String actualErrorMessage = new LoginPage(driver)
				.invalidLogin(ConfigReader.getValue("lockedUsername"), ConfigReader.getValue("validPassword"))
				.getErrorMessage();
		String expectedErrorMessage = ConfigReader.getValue("lockedUserError");
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mis-match.");
	}
}
