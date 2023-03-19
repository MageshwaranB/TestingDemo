package com.advanced;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadALogo {
	
	WebDriver driver;
	ChromeOptions option;
	@BeforeMethod
	public void setup() {
		option=new ChromeOptions();
		option.addArguments("--incognito");
		option.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(option);
		driver.get("https://www.foundit.in/seeker/registration");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void logoUploadingTest() throws InterruptedException {
		var uploadElement=driver.findElement(By.xpath("//input[@id='file-upload']"));
		uploadElement.sendKeys(new File("PDFFiles/Readme.pdf").getAbsolutePath());
		Thread.sleep(10000);
	}
	
	@AfterMethod
	public void cleanup() {
		driver.quit();
	}
}
