package com.advanced;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ClosedShadowDomHandling {
	WebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//driver.get("https://selectorshub.com/xpath-practice-page/");
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	@Test
	public void handlingClosedShadowDOM() {
		/*
		 * When the mode of a shadow root is “closed“, the shadow root’s implementation
		 * internals are inaccessible and unchangeable from JavaScript In other words,
		 * we cannot access the web elements directly using iframe or javascript
		 * executor
		 * 
		 * One of the workaround to automate closed shadow root elements by using the
		 * Actions class in Selenium
		 * 
		 * Locate the closest element which is outside closed shadow root. 
		 * It could be the host element for that shadow root. 
		 * Now focus on this outside element by using click() method. 
		 * Now use Actions class to press the TAB key to focus on
		 * the closed shadow root element. 
		 * Now use Actions class to perform any action
		 * on the closed shadow dom element like sendKeys() or click() etc.
		 */
		driver.findElement(By.id("userPass")).click();
		Actions action=new Actions(driver);
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys("abcde").perform();
	}
	
	@Test
	public void handlingCloseShadowDomInsideIFrame() {
		WebElement iframeElement=driver.findElement(By.id("pact"));
		driver.switchTo().frame(iframeElement);
		driver.findElement(By.id("jest")).click();
		Actions action=new Actions(driver);
		action.sendKeys(Keys.TAB).perform();
		action.sendKeys("Delicious").perform();
	}
	
	@Test
	public void handlingClosedShadowDomAutomationWebsite() {
		driver.findElement(By.className("grippy-host")).click();
//		Actions action=new Actions(driver);
//		action.click(driver.findElement(By.xpath("//g[@class='down']")));
		System.out.println(driver.findElement(By.xpath("//h2[text()='new arrivals']")).getText());
	}
}
