package base;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utility.ConfigReader;

public class BaseTest {
	protected WebDriver driver;
	String browser = null;
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
			
	@BeforeMethod
	public void setup() {
		browser = ConfigReader.getValue("browser");
		String url = ConfigReader.getValue("url");
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--private");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--inprivate");
			driver = new EdgeDriver(edgeOptions);
			break;
		default:
			System.out.println("Invalid browser name.");
		}
		logger.info(browser + " browser launched successfully.");
		driver.manage().window().maximize();
		logger.info(browser + " browser maximised.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("implicitWait"))));
		logger.info("ImplicitlyWait set to " + ConfigReader.getValue("implicitWait") + " seconds.");
		driver.get(url);
		logger.info("Navigted to " + url);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		logger.info(browser + " browser quit successfully.");
	}
}