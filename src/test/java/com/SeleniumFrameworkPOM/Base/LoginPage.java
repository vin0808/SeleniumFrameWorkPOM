package com.SeleniumFrameworkPOM.Base;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	public class LoginPage {
	    private WebDriver driver;

	    // Constructor
	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Locators
	    private By usernameField = By.id("username");
	    private By passwordField = By.id("password");
	    private By loginButton = By.id("Login");

	    // Page Actions
	    public void enterUsername(String username) {
	        driver.findElement(usernameField).sendKeys(username);
	    }

	    public void enterPassword(String password) {
	        driver.findElement(passwordField).sendKeys(password);
	    }

	    public void clickLogin() {
	        driver.findElement(loginButton).click();
	    }
	}

