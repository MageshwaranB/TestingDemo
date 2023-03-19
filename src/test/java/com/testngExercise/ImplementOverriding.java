package com.testngExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImplementOverriding implements OverridingSelenium{
	public WebDriver driver;
	Select select;
	/*
	 * Polymorphism is the ability to show different behaviours in its lifetime
	 * In simple terms, ability to perform the same operation in different ways
	 * Polymorphism is achieved with method overloading and method overriding
	 */
	
	//Performing both method overloading and method overriding 
	/*
	 * Select class is used to handle dropdown menu considering the dropdown is created with the select tag
	 * We can select an element by its index, value, visibleText
	 * To show the workings of method overriding, I've created an interface with three methods, that has to be implemented
	 * and it must to provide definition for these methods by the class that is inheriting it
	 * Also, these methods are overloaded with different arguments, thus achieving method overloading as well
	 */
	//selecting a dropdown based on index
	public void selectDropDownData(WebDriver driver, WebElement element, Integer index) {
		 select=new Select(element);
		select.selectByIndex(index-1);
	}
	//selecting a dropdown based on value
	public void selectDropDownData(WebDriver driver, WebElement element, String value) {
		select=new Select(element);
		select.selectByValue(value);
	}
	//selecting a dropdown based on visibleText
	public void selectDropDownData(WebDriver driver, WebElement element, Long visibleText) {
		select =new Select(element);
		select.selectByVisibleText(Long.toString(visibleText));
		/*
		 *Since I've already used String datatype as an argument, I've used passed long as an argument and
		 *and with the help toString method, I'm converting the value into a string 
		 */
	}
	
	@BeforeMethod
	public void settingUpBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	@Test
	public void selectingDOB() {
		driver.get("https://www.facebook.com/r.php");
		//driver.findElement(By.xpath("(//a[@role='button'])[3]")).click();
		WebElement dayElement = driver.findElement(By.id("day"));
		selectDropDownData(driver, dayElement, 12);
		WebElement monthElement=driver.findElement(By.id("month"));
		selectDropDownData(driver, monthElement, "11");
		WebElement yearElement=driver.findElement(By.id("year"));
		selectDropDownData(driver, yearElement, "1998");
		try {
			Thread.sleep(4000);
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
