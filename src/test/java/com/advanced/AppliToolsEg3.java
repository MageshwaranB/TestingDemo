package com.advanced;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppliToolsEg3 {
	WebDriver driver;
	AppliToolsEg appli;
	Eyes eyes;
	@BeforeMethod
	public void setupBrowser() {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		appli=new AppliToolsEg();
	}
	@Test
	public void goIbiboTest() {
		driver.get("https://www.goibibo.com/");
		appli.eyesSetup(driver, "waXR9ADPACBLgmR2gdT0FeVkxj8mHLr299ELLZRHMfV0110", "Ibibo", "Homepage");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		appli.eyes.close();
		
	}
	@AfterMethod
	public void close() {
		
		driver.close();
	}

}
