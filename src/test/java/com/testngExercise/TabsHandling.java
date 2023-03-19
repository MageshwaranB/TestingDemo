package com.testngExercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TabsHandling {
	WebDriver driver;
	JavascriptExecutor js;
	List<String> listHandles;
	
	/*
	 * Before and After Method is used to initialize and close the browser
	 */
	
	@BeforeMethod
	public void init() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		
	}
	
	public void openAWindow() {
		js.executeScript("window.open()");
		//with the help of JS executor, I'm opening a new tab
	}
	
	//This method is used to switch the control to the new tab 
	public WebDriver switchToANewTab(Set<String> handles, int windowID) {
		listHandles=new ArrayList<String>(handles);
		return driver.switchTo().window(listHandles.get(windowID));
	}
	public void newBrowser(String url) {
		driver.get(url);
		System.out.println("New window: " + driver.getTitle());
		driver.findElement(By.id("weekend-header-link")).click();
	}
	public void parentWindow(String url) {
		driver.get(url);
		System.out.println("Parent window: " + driver.getTitle());
	}
	/*
	 * WindowHandles concept is used to get the handles of all currently opened windows
	 */
	
	@Test
	public void handleTest() {
		parentWindow("https://www.amazon.in/");
		openAWindow();
		switchToANewTab(driver.getWindowHandles(),1);
		newBrowser("https://www.trivago.in/");
		switchToANewTab(driver.getWindowHandles(), 0);
	}
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
}
