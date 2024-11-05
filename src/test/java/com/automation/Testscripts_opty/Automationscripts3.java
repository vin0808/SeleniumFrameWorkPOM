package com.automation.Testscripts_opty;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.SeleniumFramework.Base.Base_Login_1;
import com.SeleniumFramework.Base.Salesforce_Login;
import com.SeleniumFramework.Utility.Constants;
import com.SeleniumFramework.Utility.Properties_Utility;

public class Automationscripts3 extends Salesforce_Login {

	private static Logger mylog = LogManager.getLogger(Base_Login_1.class);

	static String username_data = Properties_Utility.readDataFromPropertyFile(Constants.APP_PROPERTIES, "username");
	static String password_data = Properties_Utility.readDataFromPropertyFile(Constants.APP_PROPERTIES, "password");

	@Test
	public static void test15_opty() throws InterruptedException {

		slflogin(username_data, password_data);

		WebElement buttonelement = driver.findElement(By.id("Login"));
		buttonelement.click();

		Thread.sleep(2000);

		WebElement username_lable = driver.findElement(By.id("userNavLabel"));
		String str = username_lable.getText();
		System.out.println("username: " + str + " is logged in");
		Thread.sleep(2000);

		WebElement more_tab = driver.findElement(By.xpath("//*[@id=\"AllTab_Tab\"]/a"));
		more_tab.click();
		Thread.sleep(2000);

		WebElement opportunities = driver
				.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[2]/table/tbody/tr[15]/td[2]/a"));
		opportunities.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement opportunities_header = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Opportunities')]")));

		if (opportunities_header.isDisplayed()) {
			mylog.info("Opportunities Home page is displayed successfully.");
			extentreport.logTestInfo("Opportunities Home page is displayed successfully.");
		} else {
			mylog.info("Error: Opportunities Home page is not displayed.");
			extentreport.logTestInfo("Error: Opportunities Home page is not displayed.");
		}

		WebElement opportunities_dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fcf")));
		// necessary

		if (opportunities_dropdown.isDisplayed()) {
			mylog.info("Opportunities dropdown is present.");

			// Step 6: Verify the dropdown options
			Select dropdown = new Select(opportunities_dropdown);
			List<WebElement> options = dropdown.getOptions();

			// Expected options
			String[] expectedOptions = { "All Opportunities", "Closing Next Month", "Closing This Month",
					"My Opportunities", "New This Week", "Recently Viewed Opportunities", "Won" };

			// Check if the expected options are present in the dropdown
			boolean allOptionsPresent = true;

			for (String expected : expectedOptions) {
				boolean optionFound = false; // Flag to track if the option is found
				for (WebElement option : options) {
					if (option.getText().equals(expected)) {
						optionFound = true; // Set flag to true if option is found
						break; // Exit inner loop once found
					}
				}
				if (!optionFound) {
					mylog.info("Option not found: " + expected);
					extentreport.logTestInfo("Option not found: " + expected);
					allOptionsPresent = false;
				}

				else {
					mylog.info("Option found: " + expected);
					extentreport.logTestInfo("Option found: " + expected);

				}
			}

			if (allOptionsPresent) {
				mylog.info("All expected options are present in the Opportunities dropdown.");
				extentreport.logTestInfo("All expected options are present in the Opportunities dropdown.");
			} else {
				mylog.info("Some expected options are missing in the Opportunities dropdown.");
				extentreport.logTestFailed("Some expected options are missing in the Opportunities dropdown.");
			}

		}
		closeBrowser();
	}

	private static void slflogin(String username_data2, String password_data2) {
		// TODO Auto-generated method stub

	}

	@Test
	public static void test16_opty() throws InterruptedException {

		slflogin(username_data, password_data);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement buttonelement = driver.findElement(By.id("Login"));
		mylog.info("Login succesfull");
		extentreport.logTestInfo("Login succesfull");
		buttonelement.click();

		Thread.sleep(3000);

		WebElement username_lable = driver.findElement(By.id("userNavLabel"));
		String str = username_lable.getText();
		mylog.info("username: " + str + " is logged in");
		extentreport.logTestInfo("username: " + str + " is logged in");

		Thread.sleep(3000);

		WebElement more_tab = driver.findElement(By.xpath("//*[@id=\"AllTab_Tab\"]/a"));
		more_tab.click();
		Thread.sleep(3000);

		WebElement opportunities = driver
				.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[2]/table/tbody/tr[15]/td[2]/a"));
		opportunities.click();

		WebElement opportunities_header = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Opportunities')]")));

		if (opportunities_header.isDisplayed()) {
			mylog.info("Opportunities Home page is displayed successfully.");
			extentreport.logTestInfo("Opportunities Home page is displayed successfully.");
		} else {
			mylog.info("Error: Opportunities Home page is not displayed.");
			extentreport.logTestFailed("Error: Opportunities Home page is not displayed.");
		}

		WebElement new_opty = driver.findElement(By.xpath("//input[@title='New']"));
		new_opty.click();

		Thread.sleep(3000);

		WebElement name_opty = driver.findElement(By.id("opp3"));
		name_opty.sendKeys("vinutha");
		Thread.sleep(3000);

		WebElement ACCname_opty = driver.findElement(By.id("opp4"));
		ACCname_opty.sendKeys("vinutha");
		Thread.sleep(3000);

		WebElement date_opty = driver.findElement(By.id("opp9"));
		date_opty.sendKeys("10/10/2023");
		Thread.sleep(3000);

		WebElement stage_opty = driver.findElement(By.xpath("//select[@id='opp11']/option[2]"));
		stage_opty.click();

		WebElement probability_opty = driver.findElement(By.id("opp12"));
		probability_opty.sendKeys("");
		Thread.sleep(3000);

		WebElement Lead_Source_opty = driver.findElement(By.xpath("//select[@id='opp6']"));
		Lead_Source_opty.click();

		Thread.sleep(3000);
		WebElement PrimaryCampaign_Source_opty = driver.findElement(By.id("opp17"));
		PrimaryCampaign_Source_opty.sendKeys("");

		// Enter Opportunity Name,Account Name,Close Date,Stage,Probabili"ty,Lead Source
		// , Primary Campaign Source and click on save button.

		WebElement save_opty = driver.findElement(By.xpath("//input[@name='save']"));
		save_opty.click();

		WebElement save_page = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[1]/table/tbody/tr/td[1]/h2"));
		String title = save_page.getText();

		mylog.info("title " + title + " page is displayed");
		extentreport.logTestInfo("Opportunities Home page is displayed successfully.");

		closeBrowser();
	}

	@Test
	public static void test17_opty() throws InterruptedException {

		slflogin("navindu796@gmail.com", "1Am06ee022@");

		// WebDriverWait wait = new WebDriverWait(driver, 10);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement buttonelement = driver.findElement(By.id("Login"));
		buttonelement.click();

		Thread.sleep(3000);

		WebElement username_lable = driver.findElement(By.id("userNavLabel"));
		String str = username_lable.getText();
		System.out.println("username: " + str + " is logged in");
		Thread.sleep(3000);

		WebElement more_tab = driver.findElement(By.xpath("//*[@id=\"AllTab_Tab\"]/a"));
		more_tab.click();
		Thread.sleep(3000);

		WebElement opportunities = driver
				.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[2]/table/tbody/tr[15]/td[2]/a"));
		opportunities.click();

		WebElement opportunities_header = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Opportunities')]")));

		if (opportunities_header.isDisplayed()) {
			mylog.info("Opportunities Home page is displayed successfully.");
			extentreport.logTestInfo("Opportunities Home page is displayed successfully.");
		} else {
			mylog.info("Error: Opportunities Home page is not displayed.");
			extentreport.logTestFailed("Error: Opportunities Home page is not displayed.");
		}

		WebElement optypipeline_link = driver
				.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a"));
		optypipeline_link.click();
		Thread.sleep(2000);

		WebElement optypipeline_title = driver
				.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		String title = optypipeline_title.getText();

		WebElement report_title = driver.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[2]/h2"));
		String report = report_title.getText();

		WebElement status = driver.findElement(By.xpath("//*[@id=\"status\"]"));
		String report_status = status.getText();

		System.out.println(report + " is " + report_status + " in " + title + "page");
		extentreport.logTestInfo(report + " is " + report_status + " in " + title + "page");

		closeBrowser();
	}

}
