package com.FileHandling;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import com.applitools.eyes.selenium.Eyes;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MovingaFile 
{
	WebDriver driver;
	Eyes eyes;
	public String moveFile() {
		File srcFile=new File("D:\\Original\\LevelsofTesting.pdf");
		String destFileLocation="./PDFFiles/sampleTest.pdf";
		File destFile=new File(destFileLocation);
		try {
			Files.copy(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destFileLocation;
	}
	
	@Test
	public void executePDFTest() throws IOException, InterruptedException {
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		eyes=EyesManager(driver);
		
		validatePDF(moveFile());
		eyesClose(eyes);
		driver.quit();
	}
	
	public Eyes EyesManager(WebDriver driver) {
		eyes=new Eyes();
		eyes.setApiKey("waXR9ADPACBLgmR2gdT0FeVkxj8mHLr299ELLZRHMfV0110");
		eyes.open(driver,"DummyPDF","TestPDFS");
		//eyes.checkWindow();
		return eyes;
	}
	
	public static boolean validatePDF(String filepath) throws IOException, InterruptedException {
        String command = String.format(
                "java -jar resources/ImageTester.jar -f %s",
             
                filepath);
        //Runtime.getRuntime().exec("set APPLITOOLS_API_KEY=waXR9ADPACBLgmR2gdT0FeVkxj8mHLr299ELLZRHMfV0110");
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        String stream = IOUtils.toString(process.getInputStream(), "UTF-8");
        System.out.println(stream);

        if(stream != null && stream.contains("Mismatch")){
            return false;
        }

        return true;
    }
	public void eyesClose(Eyes eyes) {
		eyes.close();
		eyes.abortIfNotClosed();
	}
}
