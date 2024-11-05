package com.SeleniumFramework.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.File;

public class SalesforceListener implements ITestListener {
    private WebDriver driver;
    private Logger mylog = LogManager.getLogger(SalesforceListener.class);
    private ExtentReportsUtility extentReportUtility = ExtentReportsUtility.getInstance();

    // Default no-arg constructor
    public SalesforceListener() {
    }

    // Set the WebDriver instance via a setter
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onStart(ITestContext context) {
        mylog.info(context.getName() + " started...............");

        // Check and create directories if necessary
        createDirectory(Constants.SCREENSHOTS_DIRECTORY_PATH);
        createDirectory(new File(Constants.SPARKS_HTML_REPORT_PATH).getParent());

        extentReportUtility.startExtentReport();

        // Retrieve WebDriver instance from context if needed
        Object driverObj = context.getAttribute("driver");
        if (driverObj instanceof WebDriver) {
            this.driver = (WebDriver) driverObj;
        }
    }

    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        mylog.info(result.getName() + " test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        mylog.info(result.getName() + " test passed");
        extentReportUtility.logTestPassed(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        mylog.error(result.getName() + " test failed");
        extentReportUtility.logTestFailedWithException(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        mylog.warn(result.getName() + " test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        mylog.info(context.getName() + " finished");
        extentReportUtility.endReport();
    }
}
