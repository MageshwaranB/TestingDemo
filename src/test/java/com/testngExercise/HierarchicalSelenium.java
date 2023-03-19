package com.testngExercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utils.Screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HierarchicalSelenium extends Screenshot{
	/*
	 * Hierarchical inheritance means inheriting the properties from a class present in the same level 
	 */
	
	WebDriver driver;
	public HierarchicalSelenium hier;
	@BeforeMethod
	public void hierSetup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		hier=new HierarchicalSelenium();
	}
	@Test
	public void hierachicalTest() {
		driver.get("https://www.actitime.com/");
		hier.takeSS(driver, "3");
	}
	@AfterMethod
	public void hierClose() {
		driver.quit();
	}
	
}
