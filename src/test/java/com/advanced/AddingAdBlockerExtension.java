package com.advanced;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.ThreadSafe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddingAdBlockerExtension
{
	WebDriver driver;

	@BeforeMethod
	public void launchBrowser()  {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		String extensionPath="E:\\Download Location\\AdBlocker-Ultimate.crx";
		options.addExtensions(new File(extensionPath));
		driver = new ChromeDriver(options);
		List<String> allTabs= new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
		driver.close();
		driver.switchTo().window(allTabs.get(0));
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		driver.get("https://practice.automationtesting.in/");	
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}
	
	@Test
	public void withoutAdsTest() {
		driver.findElement(By.linkText("Shop")).click();
	}
	
	@Test
	public void withoutAdsTest2() {
		System.out.println(driver.findElement(By.xpath("//h2[text()=\"new arrivals\"]")).getText());
		driver.findElement(By.xpath("//a[text()='My Account']")).click();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
