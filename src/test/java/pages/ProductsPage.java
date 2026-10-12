package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utility.WaitUtil;

public class ProductsPage {

	// members
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(ProductsPage.class);

	// Locators
	private By pageHeaderLocator = By.cssSelector("span[data-test='title']");
	private By productsLocator = By.cssSelector("div.inventory_item");
	private By productsNameLocator = By.cssSelector("div[data-test='inventory-item-name']");
	private By menuButtonLocator = By.id("react-burger-menu-btn");
	private By logoutLinkLocator = By.id("logout_sidebar_link");

	// Constructor
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions/Methods
	public String getPageHeader() {
		String text = WaitUtil.WaitForElementToBeVisible(driver, pageHeaderLocator).getText();
		logger.info("Products Page header: " + text);
		return text;
	}

	public int getProductCount() {
		List<WebElement> allProducts = WaitUtil.WaitForAllElementsToBeVisible(driver, productsLocator); //driver.findElements(productsLocator);
		int count = allProducts.size();
		logger.info("Total Products on the Products Page: " + count);
		return count;
	}

	public boolean isProductPresent(String expProductName) {
		List<WebElement> allProductsName = WaitUtil.WaitForAllElementsToBeVisible(driver, productsNameLocator); //driver.findElements(productsNameLocator);
		for (int i = 0; i < allProductsName.size(); i++) {
			String actProductName = allProductsName.get(i).getText();
			if (actProductName.equalsIgnoreCase(expProductName)) {
				logger.info("Product " + expProductName + " found on the Products Page.");
				return true;
			}
		}
		logger.info("Product " + expProductName + " not found on the Products Page.");
		return false;
	}

	public LoginPage clickLogout() {
		
		WaitUtil.WaitForElementToBeClickable(driver, menuButtonLocator).click();
		logger.info("Menu button clicked");
		WaitUtil.WaitForElementToBeClickable(driver, logoutLinkLocator).click();
		logger.info("Logout button clicked");
		return new LoginPage(driver);
	}
}
