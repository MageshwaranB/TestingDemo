package com.advanced;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadingAFile {
	WebDriver driver;
	ChromeOptions options;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		
		options=new ChromeOptions();
		options.setExperimentalOption("prefs", setDownloadLocation());
		options.addArguments("--incoginito");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://invoicebus.com/pdf-invoice-generator/");
	}
	
	@Test
	public void downloadSomething() throws InterruptedException, AWTException {
		driver.findElement(By.id("download")).click();
		System.out.println(getDownloadLocation());
		Thread.sleep(10000);
		performRobotActions();
	}
	
	@AfterTest
	public void cleanup() {
		driver.quit();
	}
	
	static String downloadLocation;
	
	public static String getDownloadLocation() {
		return downloadLocation;
	}

	public void setDownloadLocation(String downloadLocation) {
		DownloadingAFile.downloadLocation = downloadLocation;
	}
	
	
	public HashMap<String, String> setDownloadLocation() {
		DownloadingAFile.downloadLocation="C:\\Users\\Makesh\\Documents\\SeleniumTempDownloads";
		HashMap<String, String> map=new HashMap<>();
		map.put("download.default_directory", getDownloadLocation());
		return map;
	}
	
	public void performRobotActions() throws AWTException {
		Robot robo=new Robot();
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
	}
}
