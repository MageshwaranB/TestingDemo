package com.advanced;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadDataFromJsonFile 
{
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	@Test(dataProvider = "readJSON")
	public void login(String data) {
		String[] dataSet=data.split(",");
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		driver.findElement(By.id("Email")).sendKeys(dataSet[0]);
		driver.findElement(By.id("Password")).sendKeys(dataSet[1]);
		
	}
	
	@DataProvider
	public String[] readJSON() throws IOException, ParseException {
		JSONParser jsonParser=new JSONParser();
		FileReader fileReader=new FileReader("./jsonfiles/employees.json");
		Object object=jsonParser.parse(fileReader);
		JSONObject userLoginJSONObject=(JSONObject)object;
		JSONArray userLoginsArray=(JSONArray)userLoginJSONObject.get("userlogins");
		String[] arr=new String[userLoginsArray.size()];
		for(int i=0;i<userLoginsArray.size();i++) {
			JSONObject users=(JSONObject) userLoginsArray.get(i);
			String user=(String)users.get("username");
			String password=(String)users.get("password");
			arr[i]=user+","+password;
		}
		return arr;
	}
}
