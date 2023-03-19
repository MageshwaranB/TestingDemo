package com.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppliToolsEg {
	/*
	 * Appli tools are used for visual testing Like in some cases when we are using
	 * selenium to click a button, we can do that irrespective of its position as
	 * long as it on the view, however we didn't necessarily check the color and
	 * position of that element we can do that with manual but it will take some
	 * time, to overcome we can go for applitools but it's a paid tool
	 */
	WebDriver driver;
	Eyes eyes;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public void eyesSetup(WebDriver driver,String apiKey,String appName,String testName) {
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
	public void eyesTest() {
		driver.get("https://applitools.com/helloworld/?diff1");
		eyesSetup(driver,"IFwSLqOJJjqnwjZawvXR102eJGNAIxYdHwCZ5B5YluYWY110",this.getClass().getPackageName(),"HelloTest2");
		eyes.close();
	}
	
	@Test
	public void swagLabTest() {
		driver.get("https://www.saucedemo.com/");
		eyes=new Eyes();
		eyes.setApiKey("waXR9ADPACBLgmR2gdT0FeVkxj8mHLr299ELLZRHMfV0110");
		eyes.open(driver, "swaglabs", "logintest");
		eyes.checkWindow("LoginPage");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		//eyes.open(driver, "swaglabs", "Entered data");
		eyes.checkWindow("Entered data");
		eyes.close();
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}
