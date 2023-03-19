package com.dummy;

import java.util.ArrayList;

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

public class FilloReader {	
	ArrayList<String> row=new ArrayList<String>();
	ArrayList<String> loginValues=new ArrayList<String>();
	ArrayList<String> credentials=new ArrayList<String>();
	public String filePath;
	public ArrayList<String> getData(String sheetName) throws FilloException 
	{
		Fillo fillo=new Fillo();
		String newFilePath="src/main/java/com/testdata/Credentials.xlsx";
		Connection connection=fillo.getConnection(newFilePath);
		String strQuery="Select * from "+sheetName;
		Recordset set=connection.executeQuery(strQuery);
		while(set.next()) {
			row=set.getFieldNames();
			for (int i = 0; i < row.size()-1; i++) {
				loginValues.add(set.getField(row.get(i)));
			}
		}
		set.close();
		connection.close();
		return loginValues;
	}
	@DataProvider
	public void test() throws FilloException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		FilloReader fillo=new FilloReader();
		credentials=fillo.getData("Login");
		String username = credentials.get(0);
		String password=credentials.get(1);
		
	}
}
