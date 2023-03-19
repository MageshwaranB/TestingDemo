package com.testngExercise;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingTabs {
	public static void main(String[] args) throws AWTException, InterruptedException {
	
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.airbnb.co.in/");
	        driver.manage().window().maximize();
	        String currentHandle = driver.getWindowHandle();
	             

	        String urlToClick = driver.findElement(By.xpath("//a[text()='How Airbnb works']")).getAttribute("href"); //opening the new tab         
	        JavascriptExecutor js=(JavascriptExecutor) driver;
	        js.executeScript("window.open()");
	        Set < String > handles = driver.getWindowHandles();
	        for (String actual: handles) {
	            if (!actual.equalsIgnoreCase(currentHandle)) { //switching to the opened tab              
	                driver.switchTo().window(actual); //opening the URL saved.              
	                driver.get(urlToClick);
	            }
	        }
	        driver.switchTo().window(currentHandle);
	        driver.findElement(By.xpath("//div[text()='Search']")).click();
	        
		}
		
	}

