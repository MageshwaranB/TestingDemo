package com.advanced;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DependentTesting 
{
	WebDriver driver;
	@Test
	public void goToPage() {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Swag Labs","Title doesn't match");
	}
	
	@Test(dependsOnMethods = "goToPage")
	public void loginActivity() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),"Title doesn't match");
	}
	@Test(dependsOnMethods = "loginActivity")
	public void logout() {
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	
}
