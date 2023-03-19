package com.advanced;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utils.JsonReaderUtils;

public class JsonDataDrivenUserForm 
{
	@DataProvider
	private Object[][] provideData() throws FileNotFoundException, IOException, ParseException {
		return JsonReaderUtils.getJsonData("datatypes.json", "testdata", 3);
	}
	JsonDataDrivenUserForm reader;
	ChromeOptions options;
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		options=new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		reader=new JsonDataDrivenUserForm();
	}
	
	@AfterMethod
	public void cleanup() {
		driver.quit();
	}
	
	@Test(dataProvider = "provideData")
	public void setUpTheFormTest(HashMap<String, String> map) throws Exception{
		driver.findElement(By.name("first-name")).sendKeys(map.get("firstname"));
		driver.findElement(By.name("last-name")).sendKeys(map.get("lastname"));
		driver.findElement(By.name("address")).sendKeys(map.get("address"));
		driver.findElement(By.name("zip-code")).sendKeys(map.get("zipcode"));
		driver.findElement(By.name("city")).sendKeys(map.get("city"));
		//var locator=JsonReaderUtils.getByLocatorMethod(reader, map.get("countryKey"));)
		var locator=JsonReaderUtils.getByLocatorMethod(reader, map.get("countryKey"));
		driver.findElement(locator).sendKeys(map.get("country"));
		driver.findElement(By.name("e-mail")).sendKeys(map.get("email"));
		driver.findElement(By.name("phone")).sendKeys(map.get("phone"));
		driver.findElement(By.name("job-position")).sendKeys(map.get("position"));
		driver.findElement(By.name("company")).sendKeys(map.get("Company"));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	
	public By countryTxtBox(){
		return By.name("country");
	}
	
	
}
