package com.advanced;

import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class FullScreenShot {
	
	public void fullSizeSS(WebDriver driver) {
		driver.get("https://www.amazon.in/");
		Screenshot ss=new AShot()
				.shootingStrategy(ShootingStrategies.viewportPasting(500))
				.takeScreenshot(driver);
		try {
			ImageIO.write(ss.getImage(), "png", new File(System.getProperty("user.dir")+"/NewScreenshot/ss1.png"));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

	}
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		FullScreenShot fs=new FullScreenShot();
		fs.fullSizeSS(driver);
		fs.elementSS(driver);
		driver.close();
	}
	public void elementSS(WebDriver driver ) {
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		WebElement element=driver.findElement(By.id("btnLogin"));
		Screenshot ss = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
		try {
			ImageIO.write(ss.getImage(), "png", new File(System.getProperty("user.dir")+"/NewScreenshot/ss2.png"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
