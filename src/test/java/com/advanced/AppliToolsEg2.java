package com.advanced;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppliToolsEg2 {
	WebDriver driver;
	AppliToolsEg appli;
	Eyes eyes;
	@BeforeSuite
	public void setup() {
		WebDriverManager.edgedriver().setup();
		appli = new AppliToolsEg();
		driver = new EdgeDriver();

	}

	public void newEyesSetup(WebDriver driver, String apiKey, String appName,String testName) {
		// To start with the applitools, we need to initialize eyes class
		eyes = new Eyes();
		// Then set the api key to connect with applitools website, to check whether we
		// are an authenticated user or not
		eyes.setApiKey(apiKey);
		// To start this eyes class
		eyes.open(driver, appName, testName);
		// providing a checkpoint
		eyes.checkWindow("Home page");
	}

	@Test
	public void amazonHomeTest() {
		driver.get("https://www.amazon.in/");
		//appli.eyesSetup(appli.driver, "IFwSLqOJJjqnwjZawvXR102eJGNAIxYdHwCZ5B5YluYWY110", "AMAZON", "HomepageTest");
		newEyesSetup(driver, "IFwSLqOJJjqnwjZawvXR102eJGNAIxYdHwCZ5B5YluYWY110", "AMAZON", "HomepageTest");
		eyes.close();
	}

	@AfterSuite
	public void setupClose() {
		driver.close();
	}
}
