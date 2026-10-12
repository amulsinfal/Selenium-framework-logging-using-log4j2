package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	public static WebElement WaitForElementToBeClickable(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("explicitWait"))));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static WebElement WaitForElementToBeVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("explicitWait"))));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
		
	public static List<WebElement> WaitForAllElementsToBeVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("explicitWait"))));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
}
