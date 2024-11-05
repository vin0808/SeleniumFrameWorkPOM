package com.SeleniumFramework.Utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ScreenshotUtil {

	private WebDriver driver; // WebDriver instance

	// Constructor to initialize WebDriver
	public ScreenshotUtil(WebDriver driver) {
		this.driver = driver;
	}

	// Method to take screenshot
	public String takeScreenshot(String filePath) {
		try {
			// Capture the screenshot
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File(filePath);

			// Copy the screenshot to the destination file
			FileUtils.copyFile(srcFile, destFile);
			System.out.println("Screenshot saved at: " + filePath);
		} catch (Exception e) {
			System.err.println("Error taking screenshot: " + e.getMessage());
		}
		return filePath;
	}
}
