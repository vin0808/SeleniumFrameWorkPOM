package com.SeleniumFramework.Utility;

import java.io.File;

public class Constants {
	public static final String APP_PROPERTIES = "C:\\workspace\\com.SeleniumFrameworkPOM\\src\\test\\resources\\App_Properties.properties";
	public static final String SCREENSHOTS_DIRECTORY_PATH = "C:\\workspace\\com.SeleniumFrameworkPOM\\results";

	public static final String SPARKS_HTML_REPORT_PATH =  "C:\\workspace\\com.SeleniumFrameworkPOM\\results\\extent\\spark.html";


	// Create directories if they do not exist
	static {
		new File(SCREENSHOTS_DIRECTORY_PATH).mkdirs();
		new File(SPARKS_HTML_REPORT_PATH).getParentFile().mkdirs(); // Create the parent directory for the report
	}
}
