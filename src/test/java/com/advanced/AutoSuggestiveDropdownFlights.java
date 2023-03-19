package com.advanced;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoSuggestiveDropdownFlights {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/flights/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//li[@data-cy='account']")).click();
		//driver.findElement(By.xpath("//label[@for='fromCity']/span")).click();
		driver.findElement(By.id("fromCity")).sendKeys("Mum");
		String optionToSelect = "Mumbai";
		//driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("Mum");
		Thread.sleep(4000);
		int count=0;
		List<WebElement> optionList = driver
				.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
		for (WebElement options : optionList) {
			String currentOption = options.getText();
			if (currentOption.contains(optionToSelect)) {
				options.click();
				count++;
				Thread.sleep(4000);
				break;
			}
		}
		if(count!=0)
		{
			System.out.println(optionToSelect+" is selected");
		}
		else
			System.out.println(optionToSelect+" is not available");
		driver.close();
	}
}
