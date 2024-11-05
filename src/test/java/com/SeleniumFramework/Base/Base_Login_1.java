package com.SeleniumFramework.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.io.Files;
import com.SeleniumFramework.Utility.ExtentReportsUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Login_1 {
	protected static WebDriver driver;
	private static Logger mylog = LogManager.getLogger(Base_Login_1.class);
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();

	// Method to launch a browser based on the input
	public static void launchBrowser(String browserName) {

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}

		// Set timeouts
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // 30 seconds implicit wait
		driver.manage().window().maximize(); // Maximize the browser window
	}

	public void goToUrl(String url) {
		driver.get(url);
		mylog.info(url + " is entered");
	}

	// Explicit wait for visibility of elements
	protected static void waitForVisibility(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Enter text into a textbox
	public static void enterText(WebElement ele, String data, String objectName) {
		if (ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			mylog.info("Data is entered in the " + objectName);
		} else {
			mylog.warn(objectName + " textbox is not displayed");
		}
	}

	// Click a button or link
	public static void clickElement(WebElement ele, String objectName) {
		if (ele.isEnabled()) {
			ele.click();
			mylog.info(objectName + " button is clicked");
		} else {
			mylog.warn(objectName + " button is not enabled");
		}
	}

	// Take a screenshot
	public static void takeScreenshot(String filepath) {
		TakesScreenshot screenCapture = (TakesScreenshot) driver;
		File src = screenCapture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filepath);
		try {
			Files.copy(src, destFile);
			mylog.info("Screenshot captured: " + filepath);
		} catch (IOException e) {
			mylog.error("Problem occurred during screenshot taking: " + e.getMessage());
		}
	}

	// Close the browser
	public static void closeBrowser() {
		if (driver != null) {
			driver.quit(); // Close all browser windows and end the WebDriver session
			mylog.info("Browser is closed");
		} else {
			mylog.warn("Driver is not initialized, no browser to close");
		}
	}
}
