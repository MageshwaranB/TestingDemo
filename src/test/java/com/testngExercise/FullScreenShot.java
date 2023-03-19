package com.testngExercise;

import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class FullScreenShot {
	WebDriver driver;
	String separator = File.separator;
	/*
	 * To take a full page screenshot we need to use a third party api: Ashot
	 * File.separator is used because windows accepts / and unix accepts \
	 */

	@BeforeSuite
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public void fullSizeSS(WebDriver driver) {
		driver.get("https://www.amazon.in/");
		// We need to create an object for Ashot class
		// if the screenshot of the page is bigger then the screen size, call the
		// shootingStrategy() method before calling takeScreenshot() method
		// viewportPasting: Will scroll viewport while shooting and we need to provide
		// the timeout
		Screenshot ss = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(50)).takeScreenshot(driver);
		// Like in the case of taking a simple screenshot, we need to copy the
		// screenshot to a desired location
		try {
			ImageIO.write(ss.getImage(), "png",
					new File(System.getProperty("user.dir") + separator + "NewScreenshot" + separator + "ss1.png"));
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

	}

	@Test
	public void ssTest() {
		FullScreenShot fs = new FullScreenShot();
		// Taking a screenshot of an entire webpage
		fs.fullSizeSS(driver);
		// Taking a screenshot of a particular webelement
		fs.elementSS(driver);
		driver.close();
	}

	public void elementSS(WebDriver driver) {
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		WebElement element = driver.findElement(By.id("btnLogin"));
		// coordsProvider is used to get the coordinates of that image
		Screenshot ss = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
		try {
			ImageIO.write(ss.getImage(), "png", new File(System.getProperty("user.dir") + separator+"NewScreenshot"+separator+"ss2.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
