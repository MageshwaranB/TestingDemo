package com.advanced;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrollBarInsideWebpage {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://webflow.com/made-in-webflow/website/vertical-navbar");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		WebElement frameElement=driver.findElement(By.className("css-1m4vocp"));
		driver.switchTo().frame(frameElement);
		JavascriptExecutor javascriptExecutor=(JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("title2")));
		String textName=driver.findElement(By.className("title2")).getText();
		System.out.println(textName);
		driver.quit();
	}
}
