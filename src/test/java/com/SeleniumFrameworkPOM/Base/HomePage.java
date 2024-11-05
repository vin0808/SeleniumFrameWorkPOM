package com.SeleniumFrameworkPOM.Base;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class HomePage {
	    private WebDriver driver;

	    // Constructor
	    public HomePage(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Locators
	    private By userMenu = By.id("userNav-arrow");

	    // Page Actions
	    public void openUserMenu() {
	        driver.findElement(userMenu).click();
	    }
	}


