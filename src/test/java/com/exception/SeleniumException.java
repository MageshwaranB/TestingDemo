package com.exception;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumException {
	public WebDriver driver;

	public WebDriver driverInitialization() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		return driver;
	}

	public void exceptionElementNotInteractable(WebDriver driver) {
		/*
		 * When a web element is present, but it is not in the state that can be interacted.
		 * In other words when element is found but we can’t interact with it, then it throws ElementNotInteractableException
		 * Reasons: Element is present in off screen, element present behind another element, element is not visible
		 */
		driver.findElement(By.name("q")).sendKeys("amazon");
		try {
			driver.findElement(By.name("btnK")).click();
		} 
		catch (ElementNotInteractableException e) {
			System.out.println("Pls provide some synchronization points since selenium is much faster ");
		} 
		finally {
			driver.quit();
		}
	}

	public void exceptionAlert(WebDriver driver) {
		// Webdriver is trying to switch to an invalid alert, which is unavailable
		try {
			driver.switchTo().alert();
		} catch (NoAlertPresentException e) {
			System.out.println("There's no such alert present in this page");
		} finally {
			driver.close();
		}
	}

	public void exceptionNoSuchSesssion(WebDriver driver) {
		driver.quit();
		//Using the webdriver commands after closing the webdriver
		try {
			driver.get("https://www.google.com/");
		} 
		catch (NoSuchSessionException e) {
			System.out.println("Encountered a no such session exception");
		}
		finally {
			driver.quit();
		}
	}
	public void exceptionNoSuchFrame(WebDriver driver) {
		//This exception when we try to switch to a non exisiting frame
		try {
			driver.switchTo().frame(0);
		}
		catch (NoSuchFrameException e) {
			System.out.println("No frame is exist in this page");
		}
		finally {
			driver.quit();
		}
	}
	public void exceptionClickIntercepted(WebDriver driver) {
		//The Element Click command could not be properly executed as the element that is receiving the Click command was concealed in some way.
		driver.navigate().to("https://saucelabs.com/");
		try {
			driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		}
		catch (ElementClickInterceptedException e) {
			System.out.println("Element's click was intercepted");
		}
		finally {
			driver.quit();
		}
	}
	public void exceptionNoSuchWindow(WebDriver driver) {
		//This exception will occur if we provide improper window handles
		try {
			driver.switchTo().window("pls_fail");
		}
		catch (NoSuchWindowException e) {
			System.out.println("Switch to an existing window with a window handles");
		}
		finally {
			driver.quit();
		}
	}
	public void exceptionMoveOutOfBounds(WebDriver driver) {
		//This exception occurs in the Selenium when the target provided to the ActionChains move () methodology is invalid
		driver.navigate().to("https://saucelabs.com/");
		
		Actions action=new Actions(driver);
		try {
			action.moveToElement(driver.findElement(By.xpath("(//span[text()='Community'])[3]"))).build().perform();
		}
		catch(MoveTargetOutOfBoundsException e) {
			System.out.println("The target is out of bounds");
			
		}
		finally {
			driver.quit();
		}
		
	}
	public static void main(String[] args) {
		SeleniumException sel = new SeleniumException();
		//sel.exceptionElementNotInteractable(sel.driverInitialization());
		// sel.exceptionAlert(sel.driverInitialization());
		//sel.exceptionNoSuchSesssion(sel.driverInitialization());
		//sel.exceptionNoSuchFrame(sel.driverInitialization());
		//sel.exceptionClickIntercepted(sel.driverInitialization());
		//sel.exceptionMoveOutOfBounds(sel.driverInitialization());
		//sel.exceptionNoSuchWindow(sel.driverInitialization());
	}
}
