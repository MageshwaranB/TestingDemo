package com.advanced;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingChromeOptionsFunctionality {
	
	WebDriver driver;
	ChromeOptions chromeOptions;
	
	
	@Test
	public void handlingSSLCertificateTest() {
		chromeOptions=new ChromeOptions();
		// By adding the below line, we can bypass the not secure website
		chromeOptions.setAcceptInsecureCerts(true);
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(chromeOptions);
		driver.get("https://expired.badssl.com/");
		System.out.println("Title of the page:"+ driver.getTitle());
	}
	
	@Test
	public void handlingCookies() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://twitter.com/home");
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
