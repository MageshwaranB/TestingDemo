package com.dummy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FiloNewTest {
	@Test(dataProvider = "provideData")
	public void filloTest(String uname,String pword) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		driver.findElement(By.id("login-button")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@DataProvider
	public Object[][] provideData() throws FilloException {
		Fillo fillo=new Fillo();
		Connection con=fillo.getConnection("./src/main/java/com/testdata/Credentials.xlsx");
		String sqlQuery="Select * from Login";
		Recordset set=con.executeQuery(sqlQuery);
		int rows=set.getCount();
		Object[][] object=new Object[rows][rows];
		while(set.next())
		{
			for (int i = 0; i < object.length; i++) {
				for (int j = 0; j < object.length; j++) {
					object[i][0]=set.getField("username");
					object[0][j]=set.getField("password");
				}
			}
		}
		set.close();
		con.close();
		return object;
	}
}
