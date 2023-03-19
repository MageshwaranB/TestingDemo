package com.testngExercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface OverridingSelenium {
	void selectDropDownData(WebDriver driver,WebElement element,Integer index);
	void selectDropDownData(WebDriver driver,WebElement element,String value);
	void selectDropDownData(WebDriver driver,WebElement element,Long visibleText);
}
