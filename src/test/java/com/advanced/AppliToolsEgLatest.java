package com.advanced;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppliToolsEgLatest 
{
	WebDriver driver;
	Eyes eyes;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	public Eyes openEyes(String apikey) {
		eyes=new Eyes();
		setApiKey(apikey);
		eyes.open(driver, "swaglabs", "MascotTest");
		return eyes;
	}
	public void visualValidationByElement(WebElement webElement, Eyes eyes) {
		eyes.checkElement(webElement);
		closeEyes();
	}
	
	public void closeEyes() {
		eyes.close();
	}
	
	public void setApiKey(String apiKey) {
		eyes.setApiKey(apiKey);
	}
	
	@Test
	public void mascotImageTest() {
//		setApiKey("waXR9ADPACBLgmR2gdT0FeVkxj8mHLr299ELLZRHMfV0110");
//		openEyes();
//		visualValidationByElement(driver.findElement(By.className("bot_column")));
		eyes=openEyes("waXR9ADPACBLgmR2gdT0FeVkxj8mHLr299ELLZRHMfV0110");
		visualValidationByElement(driver.findElement(By.className("orangehrm-login-branding")), eyes);
	}
	
	@AfterMethod
	public void teardown() {
		//eyes.close();
		driver.close();
	}
}
