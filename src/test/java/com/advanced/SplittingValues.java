package com.advanced;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SplittingValues {
	
	public static void splittingStrings(String s) {
		String requiredString = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
		System.out.println(requiredString);
	}
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		driver.get("https://www.makemytrip.com/flights/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//li[@data-cy='account']")).click();
		WebElement element= driver.findElement(By.xpath("//label[@for='fromCity']/span"));
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
		element.click();
		String text="Mellia";
		WebElement dropdown = driver.findElement(By.xpath("//input[@placeholder='From']"));
		dropdown.sendKeys(text);
		List<String> allItems=new ArrayList<String>();;
		Thread.sleep(3000);
		List<WebElement> allsuggestions=driver.findElements(By.xpath("(//ul[@role='listbox'])[1]"));
		for (int i = 0; i < allsuggestions.size(); i++) {
			System.out.println(allsuggestions.get(i).getText());
			allItems.add(allsuggestions.get(i).getText());
		}
		while ((text.equalsIgnoreCase("Melilla, Spain"))) {
			dropdown.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(3000);
		}
		dropdown.sendKeys(Keys.ENTER);
		System.out.println("Done");
		
//		String[] arr=text.split(" ");
//		for (int i = 0; i < arr.length/2; i++) {
//			String temp=arr[i];
//			arr[i]=arr[arr.length-1-i];
//			arr[arr.length-1-i]=temp;
//		}
//		System.out.println(arr[0]);
		
		driver.close();
		
	}
}
