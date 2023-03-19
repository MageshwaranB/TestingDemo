package com.dummy;

import java.util.ArrayList;
import java.util.List;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class FilloDemo {
	public static void main(String[] args) throws FilloException {
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection("./src/main/java/com/testdata/Credentials.xlsx");
		String strQuery = "Select * from Login";
		// Recordset will hold the excel sheet data and each row is treated as a record
		Recordset rs = connection.executeQuery(strQuery);
		List<String> data=new ArrayList<String>();
		// printing data present in excel
		while (rs.next()) {
			System.out.println(rs.getField("username") + " " + rs.getField("password"));
			data.add(rs.getField("username"));
		}
		System.out.println(data.get(0));
		// printing the rows
		System.out.println("Total rows: " + rs.getCount());
		rs.close();
		connection.close();
	}
}
