package com.advanced;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.JsonReaderUtils;

public class JsonDataDriven 
{
	@DataProvider
	private Object[][] provideJsonData() throws FileNotFoundException, IOException, ParseException {
		return JsonReaderUtils.getJsonData("testdata.json", "logindata", 2);
	}
	
	@Test(dataProvider = "provideJsonData")
	public void loginTest(HashMap<String, String> data) throws Exception {
		ChromeOptions options=new ChromeOptions();
		//options.addArguments("--incognito");
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("user-name")).sendKeys(data.get("username"));
		driver.findElement(By.id("password")).sendKeys(data.get("password"));
		Thread.sleep(5000);
		driver.quit();
	}
}
