package com.topics;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverMethods {
	public WebDriver driver;
	public WebDriver setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
	public void tearDown() {
		driver.quit();
	}
	public void webElementMethods(WebDriver driver,By element, By newElement) {
		/*
		 * WebElement methods are getText(),getAttribute(),getSize(),getLocation(),sendkeys(),click(),clear(),
		 * submit(),getTagname(),isDisplayed(),isEnabled(),isSelected()
		 */
		String ele = driver.findElement(element).getText();
		System.out.println(ele);
		System.out.println("Attribute value: "+driver.findElement(element).getAttribute("name"));
		System.out.println("Tagname: "+driver.findElement(element).getTagName());
		int locX = driver.findElement(element).getLocation().getX();
		int locY=driver.findElement(element).getLocation().getY();
		System.out.println("X axis: "+locX+ " "+"Y axix: "+locY);
		driver.findElement(element).click();
		driver.findElement(element).submit();
		int height = driver.findElement(element).getSize().getHeight();
		int width = driver.findElement(element).getSize().getWidth();
		System.out.println("Height and Width of the element: "+height+" "+ width);
		System.out.println(driver.findElement(element).isDisplayed());
		System.out.println(driver.findElement(element).isEnabled());
		System.out.println(driver.findElement(element).isSelected());
		driver.findElement(newElement).sendKeys("standard");
		System.out.println(driver.findElement(newElement).getText());
		driver.findElement(newElement).clear();
		String[] arr=driver.findElement(By.id("login_credentials")).getText().split(" ");
		System.out.println("The complete text: ");
		for (String string : arr) {
			System.out.println(string+" ");
		}
		
	}
	@Test
	public void test() {
		WebDriverMethods methods=new WebDriverMethods();
		methods.webElementMethods(methods.setup(),By.id("login-button") , By.id("user-name")); ;
		methods.tearDown();
	}
}
