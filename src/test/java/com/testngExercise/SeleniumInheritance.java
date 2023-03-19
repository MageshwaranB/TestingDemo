package com.testngExercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.utils.Screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumInheritance extends Screenshot {
	
	WebDriver driver;
	public SeleniumInheritance sel;
	/*
	 * Single level inheritance: Inheriting properties of a parent class
	 */
	@BeforeClass
	public void selSetup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		sel=new SeleniumInheritance();
	}
	@Test
	public void sstest() {
		driver.get("https://www.google.com/");
		sel.takeSS(driver, "1");
	}
	@AfterClass
	public void selClose() {
		driver.quit();
	}
}
