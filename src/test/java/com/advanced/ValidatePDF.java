package com.advanced;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidatePDF 
{
	@Test
	public void testSetup() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://invoicebus.com/pdf-invoice-generator/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame("invoice_ifr");
		var testEle=driver.findElement(By.xpath("//input[@id='company_name']"));
		testEle.click();
		testEle.clear();
		testEle.sendKeys("Demo");
	}
}
