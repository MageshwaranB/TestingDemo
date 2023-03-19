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

public class FiloTest 
{
	@Test(dataProvider = "getData")
	public void test(String uname) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys(uname);
		driver.quit();
	}
	
	@DataProvider
	public Object getData() throws FilloException {
			Fillo fillo=new Fillo();
			Object[] object=null;
			int i=0;
			Connection con=fillo.getConnection("./src/main/java/com/testdata/Credentials.xlsx");
			String sqlQyuery="Select * from Login";
			Recordset set=con.executeQuery(sqlQyuery);
			int row=set.getCount();
			object=new Object[row];
			while (set.next()) {
				object[i]=set.getField("username");
				i++;
			}
			set.close();
			con.close();
			return object;
	}
}
