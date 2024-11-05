package com.SeleniumFrameworkPOM.Base;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class UserPage {
	    private WebDriver driver;

	    // Constructor
	    public UserPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Locators
	    private By myProfileOption = By.linkText("My Profile");
	    private By logoutOption = By.linkText("Logout");

	    // Page Actions
	    public void selectMyProfile() {
	        driver.findElement(myProfileOption).click();
	    }

	    public void logout() {
	        driver.findElement(logoutOption).click();
	    }
	}

