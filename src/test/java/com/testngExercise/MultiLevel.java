package com.testngExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultiLevel extends SeleniumInheritance{
	/*
	 * MultiLevel inheritance means inheriting the properties of the parent class of different level
	 */
	public MultiLevel mul;
	@BeforeTest
	public void mulSetup() {
		mul=new MultiLevel();
		mul.selSetup();//selSetup will setup the parent's features like launching the browser 
		mul.sstest();//mulTest will execute the first test case
	}
	@Test
	public void multiTest() {
		mul.driver.findElement(By.name("q")).sendKeys("Amazon");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mul.driver.findElement(By.xpath("(//input[@name='btnK'])[1]")).click();
		mul.takeSS(mul.driver, "2");
	}
	@AfterTest
	public void mulClose() {
		mul.selClose();
		driver.quit();
	}
}
