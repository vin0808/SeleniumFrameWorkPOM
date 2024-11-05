package com.SeleniumFramework.Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.SeleniumFramework.Utility.Properties_Utility;
import com.SeleniumFramework.Utility.Constants;

public class Salesforce_Login extends Base_Login_1 {

    private static Logger mylog = LogManager.getLogger(Salesforce_Login.class);

    @BeforeMethod
    public void setupBeforeMethod(ITestContext context) {
        mylog.info("-------------------------- Setup Before Method -------------------------------------");
        launchBrowser("edge");
        context.setAttribute("driver", driver); // set driver in the context
        String url = Properties_Utility.readDataFromPropertyFile(Constants.APP_PROPERTIES, "url");
        goToUrl(url);
    }

    @AfterMethod
    public void setupAfterMethod() {
        closeBrowser();
        mylog.info("-------------------------- Setup After Method -------------------------------------");
        mylog.info(
                "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    }

    public static void slfLogin(String username, String password) {

        mylog.info("Logging into Salesforce with username: {}", username); // Log the username for tracking

        WebElement usernameElement = driver.findElement(By.id("username"));
        enterText(usernameElement, username, "Username field");

        WebElement passwordElement = driver.findElement(By.id("password"));
        enterText(passwordElement, password, "Password field");

    }
}
