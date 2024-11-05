package com.automation.Testscripts_usermenu;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.SeleniumFramework.Base.Salesforce_Login;
import com.SeleniumFramework.Utility.Constants;
import com.SeleniumFramework.Utility.Properties_Utility;
import com.SeleniumFramework.Utility.ScreenshotUtil;
import com.SeleniumFramework.Utility.ExtentReportsUtility;

public class Automationscripts1_1 extends Salesforce_Login {

    private static final Logger mylog = LogManager.getLogger(Automationscripts1_1.class);
    private static final String username_data = Properties_Utility.readDataFromPropertyFile(Constants.APP_PROPERTIES, "username");
    private static final String password_data = Properties_Utility.readDataFromPropertyFile(Constants.APP_PROPERTIES, "password");

    private ExtentReportsUtility reportUtil = ExtentReportsUtility.getInstance();

    @BeforeMethod
    public void setupTest(Method method) {
        reportUtil.startSingleTestReport(method.getName());
        mylog.info("Starting test: " + method.getName());
        reportUtil.logTestInfo("Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDown() {
        reportUtil.endReport();
    }

    private WebElement waitForVisibility(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void verifyAndClick(By locator, String elementDescription) throws InterruptedException {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isDisplayed(), elementDescription + " is not displayed as expected.");
        mylog.info(elementDescription + " is displayed.");
        reportUtil.logTestInfo(elementDescription + " is displayed.");
        element.click();
        Thread.sleep(2000);
        reportUtil.logTestInfo(elementDescription + " clicked successfully.");
    }

    private void loginToSalesforce() throws InterruptedException {
        slfLogin(username_data, password_data);
        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        Thread.sleep(2000);
        mylog.info("Logged in to Salesforce successfully.");
        reportUtil.logTestInfo("Logged in to Salesforce successfully.");
    }

  
    @Test
    public void testforusername_Usermenu() {
        try {
            loginToSalesforce();
            reportUtil.logTestInfo("Logged into Salesforce.");

            verifyAndClick(By.id("userNav-arrow"), "User menu");
            reportUtil.logTestInfo("User menu clicked.");

            WebElement usernameDisplay = waitForVisibility(By.id("userNavLabel"), 20);
            String actualUsername = usernameDisplay.getText();
            Assert.assertEquals(actualUsername, "Vinutha Prabhakar", "Username does not match.");
            reportUtil.logTestInfo("Vinutha Prabhakar:   " + actualUsername);

        } catch (Exception e) {
        	
        	String screenshotPath = new ScreenshotUtil(driver).takeScreenshot("testforusername_Usermenu");
        	reportUtil.logTestWithScreenshot(screenshotPath, "Screenshot on Failure - testforusername_Usermenu");
            reportUtil.logTestFailed("testforusername_Usermenu test failed", e);
            mylog.error("testforusername_Usermenu test failed due to exception: ", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
            
            
        }
    }


    @Test
    public void testforDeveloperConsole_Usermenu() throws InterruptedException {
        loginToSalesforce();

        try {
            verifyAndClick(By.id("userNav-arrow"), "User menu");
            reportUtil.logTestInfo("User menu opened.");

            WebElement consoleTab = waitForVisibility(By.xpath("//*[@id='userNav-menuItems']/a[3]"), 20);
            verifyAndClick(By.xpath("//*[@id='userNav-menuItems']/a[3]"), "Developer Console tab");

            String originalWindow = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    driver.close();
                    mylog.info("Developer Console window closed.");
                    reportUtil.logTestInfo("Developer Console window closed.");
                    break;
                }
            }

            driver.switchTo().window(originalWindow);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://tekarch79-dev-ed.develop.my.salesforce.com/setup/forcecomHomepage.apexp?setupid=ForceCom",
                    "Failed to open Developer Console.");
            reportUtil.logTestInfo("Successfully returned to original window.");
        } catch (Exception e) {
        	String screenshotPath = new ScreenshotUtil(driver).takeScreenshot("testforDeveloperConsole_Usermenu");
        	reportUtil.logTestWithScreenshot(screenshotPath, "Screenshot on Failure - testforusername_Usermenu");
            mylog.error("Test failed with exception: ", e);
            reportUtil.logTestFailed("Developer Console validation failed.", e);
            Assert.fail("Developer Console validation failed.");
        }
    }

    @Test
    public void testforlogout_Usermenu() throws InterruptedException {
        loginToSalesforce();

        try {
            verifyAndClick(By.id("userNav-arrow"), "User menu");

            WebElement logoutOption = waitForVisibility(By.xpath("//a[@title='Logout']"), 20);
            verifyAndClick(By.xpath("//a[@title='Logout']"), "Logout option");
            Thread.sleep(5000);

            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://tekarch79-dev-ed.develop.my.salesforce/",
                    "Logout failed or URL mismatch.");           
            mylog.info("Logout successful, login page displayed.");
            reportUtil.logTestInfo("Logout successful, login page displayed.");
        } catch (Exception e) {
        	String screenshotPath = new ScreenshotUtil(driver).takeScreenshot("testforlogout_Usermenu");
        	reportUtil.logTestWithScreenshot(screenshotPath, "Screenshot on Failure - testforusername_Usermenu");
            mylog.error("Logout test failed with exception: ", e);
            reportUtil.logTestFailed("Logout validation failed.", e);
            Assert.fail("Logout validation failed.");
        }
    }
}
