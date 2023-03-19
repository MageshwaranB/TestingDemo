package com.utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Screenshot {
	public void takeSS(WebDriver driver,String number) {
		TakesScreenshot ss=(TakesScreenshot) driver;
		File src = ss.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"/screenshot/"+"Screenshot"+number+".png");
		try {
			Files.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
