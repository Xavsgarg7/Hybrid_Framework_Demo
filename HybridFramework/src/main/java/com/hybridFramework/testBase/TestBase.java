package com.hybridFramework.testBase;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hybridFramework.excelReader.ExcelReader;
import com.hybridFramework.uiActions.ActionKeyword;
import com.hybridFramework.utility.BrowserUtility;


public class TestBase {
	public static Map<String, String> data;
	public static WebElement getLocator(WebDriver driver, String locatorType, String locatorValue) throws Exception {
		/*
		 * // System.out.println(locator); String[] split = locator.split(":");
		 * String locatorType = split[0]; String locatorValue = split[1];
		 */
		// System.out.println("locatorType:-"+locatorType);
		// System.out.println("locatorValue:-"+locatorValue);
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	/*
	 * public static WebElement getWebElement(String locator) throws Exception {
	 * return getLocator(data.get(locator)); }
	 */

	public void getScreenShot(String img, WebDriver driver) throws IOException {
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File(
				System.getProperty("user.dir") + "/src/main/java/com/HybridFramework/screenshot/" + img + ".png"));

	}

}
