package com.testngExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderConcept 
{
	public WebDriver driver;
	/*
	 * BeforeMethod will execute before every method and AfterMethod will execute after every method
	 * Test is an annotation which is used to convert an ordinary method into a test script
	 * Data Provider is used for achieving Parameterization and need to provide the annotation
	 * For a simple example, We can use a multi-dimensional object array
	 * It's also a must, to mention in the test annotation which gets this data provider method 
	 */
	
	@BeforeMethod
	public void initialize() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}
	@DataProvider
	public Object[][] provideData() {
		Object[][] data=new Object[2][2];
		data[0][0]="standard_user";
		data[0][1]="secret_sauce";
		data[1][0]="problem_user";
		data[1][1]="secret_sauce";
		return data;
	}
	@Test(dataProvider = "provideData")
	public void loginSauceTest(String userName,String passWord) {
		driver.findElement(By.id("user-name")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(passWord);
		driver.findElement(By.id("login-button")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
