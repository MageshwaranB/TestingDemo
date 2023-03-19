package com.advanced;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FluentWaitTest 
{
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void FluentWaitEg() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
//		WebElement element=simpleFluentWaitEx(driver);
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		simpleFluentWaitEx(driver);
		System.out.println("Element name: "+driver.findElement(By.xpath("//div[@id='finish']")).getText());
	}
	
	@Test
	public void fetchingDataSpeedTest() {
		driver.get("https://fast.com/");
		explicitWaitTest(By.xpath("(//div[contains(@class,'succeeded')])[1]"));
	System.out.println(driver.findElement(By.xpath("(//div[contains(@class,'succeeded')])[1]")).getText());
		
	}
	
	public void simpleFluentWaitEx(WebDriver driver) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
								.withTimeout(Duration.ofSeconds(30))
								.pollingEvery(Duration.ofSeconds(2))
								;
		WebElement element=wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				if(driver.findElement(By.xpath("//div[@id='finish']")).isDisplayed()) {
					return driver.findElement(By.xpath("//div[@id='finish']"));
				}
				else {
					return null;
				}
				
			};
		});
		
	}
	
	public void explicitWaitTest(By webElement) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(webElement));
	}
	
	
}
