package com.SeleniumFramework.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Properties_Utility {
	protected WebDriver driver;

	public static String readDataFromPropertyFile(String path, String key) {

		File file = new File(path);
		FileInputStream fi = null;
		Properties propfile = new Properties();
		String data = null;
		try {
			fi = new FileInputStream(file);
			propfile.load(fi);
			data = propfile.getProperty(key);
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("error in the file path.. please try again");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error in loading the file in properties object");
			e.printStackTrace();
		}

		return data;
	}

	public static String writeDataToPropertyFile(String path, String key, String value) {

		// Load existing properties if the file exists
		File file = new File(path);
		String data;
		Properties propfile = new Properties();

		// Load existing properties if the file exists
		if (file.exists()) {
			try (FileInputStream fi = new FileInputStream(file)) {
				propfile.load(fi);
			} catch (IOException e) {
				return "Error: Unable to load the existing properties file.";
			}
		}

		// Set the property
		propfile.setProperty(key, value);

		// Write the properties back to the file
		try (FileOutputStream fo = new FileOutputStream(file)) {
			propfile.store(fo, null); // null for the comment
			data = "Successfully wrote: " + key + " = " + value;
		} catch (IOException e) {
			return "Error: Unable to write to the properties file.";
		}

		return data;
	}

	public static int getSize(String path) {

		// Load existing properties if the file exists
		File file = new File(path);
		int size = 0;
		Properties propfile = new Properties();

		// Load existing properties if the file exists
		if (file.exists()) {
			try (FileInputStream fi = new FileInputStream(file)) {
				propfile.load(fi);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return size;
	}

}
