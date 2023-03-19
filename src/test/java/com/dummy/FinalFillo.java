package com.dummy;

import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FinalFillo {
	
	public Object[][] filloProvide(String filePath) throws FilloException {
		Fillo fillo=new Fillo();
		Connection con=fillo.getConnection(filePath);
		String query="Select * from Login";
		Recordset set=con.executeQuery(query);
		Hashtable<String, String> table;
		Object[][] data=new Object[set.getCount()][1];
		int rowIndex=0;
		while(set.next())
		{
			table=new Hashtable<String, String>();
			for (String strCol : set.getFieldNames()) {
				table.put(strCol, set.getField(strCol.toString()));
			}
			data[rowIndex][0]=table;
			rowIndex++;
		}
		set.close();
		con.close();
		return data;
	}
	@DataProvider
	public Object[][] getData() throws FilloException {
		return filloProvide("./src/main/java/com/testdata/Credentials.xlsx");
	}
	@Test
	public void test(HashMap<String, String> data) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		System.out.println("Username: "+data.get("username")+" : "+"password: "+data.get("password"));
	}
}
