package com.advanced;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicallyFindingProducts {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		String extensionPath="E:\\Download Location\\AdBlocker-Ultimate.crx";
		options.addExtensions(new File(extensionPath));
		WebDriver driver = new ChromeDriver(options);
		List<String> allTabs= new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
		driver.close();
		driver.switchTo().window(allTabs.get(0));
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
		int count=0;
		List<String> expectedProd=new ArrayList<String>();
		expectedProd.add("Sauce Labs Fleece Jacket");
		expectedProd.add("Sauce Labs Onesie");
		List<WebElement> allAvailableProducts=driver.findElements(By.xpath("//div[@class='inventory_item']"));
		for(WebElement currentProduct:allAvailableProducts) {
			scrollingToThatElement(currentProduct, driver);
			String prodTitle=currentProduct.findElement(By.className("inventory_item_name")).getText();
			System.out.println(prodTitle);
			if(expectedProd.contains(prodTitle)) {
				currentProduct.findElement(By.cssSelector("button[class='btn btn_primary btn_small btn_inventory']")).click();
				System.out.println("Added "+prodTitle);
				count++;
			}
		}
		System.out.println(count);
		driver.quit();
	}
	public static void scrollingToThatElement(WebElement element, WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		//WebElement elementToBeScrolled = mainArrivals;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
