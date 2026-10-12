package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.WaitUtil;

public class LoginPage {

	// members
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	// Locators
	private By usernameTextLocator = By.id("user-name");
	private By passwordTextLocator = By.id("password");
	private By loginButtonLocator = By.id("login-button");
	private By errorMessageLocator = By.cssSelector("h3[data-test='error']");

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions/Methods
	private void enterUsername(String username) {
		WaitUtil.WaitForElementToBeClickable(driver, usernameTextLocator).sendKeys(username);
		logger.info("Username entered: " + username);
	}

	private void enterPassword(String password) {
		WaitUtil.WaitForElementToBeClickable(driver, passwordTextLocator).sendKeys(password);
		logger.info("Password entered: " + password);
	}

	private void clickLoginButton() {
		WaitUtil.WaitForElementToBeClickable(driver, loginButtonLocator).click();
		logger.info("Login button clicked.");
	}

	public ProductsPage validLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		return new ProductsPage(driver);
	}

	public LoginPage invalidLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		return this;
	}

	public String getErrorMessage() {
		String errorMessage = WaitUtil.WaitForElementToBeVisible(driver, errorMessageLocator).getText();
		logger.info("Error message displayed: " + errorMessage);
		return errorMessage;
	}

	public boolean isLoginPageDisplayed() {
		boolean isDisplayed = WaitUtil.WaitForElementToBeVisible(driver, loginButtonLocator).isDisplayed();
		logger.info("Login page is displayed: " + isDisplayed);
		return isDisplayed;
	}
}
