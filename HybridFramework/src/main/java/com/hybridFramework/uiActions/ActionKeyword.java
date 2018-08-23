package com.hybridFramework.uiActions;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hybridFramework.testBase.TestBase;
import com.hybridFramework.utility.BrowserUtility;
import com.hybridFramework.utility.ReadProperties;

public class ActionKeyword {
	static WebDriver driver;
	public static Map<String,String> env;

	public static void openBrowser(){
		BrowserUtility bu = new BrowserUtility();
		driver = bu.invokeBrowser("chrome");
		driver.manage().window().maximize();
	}
	public static void navigate() {
		env = ReadProperties.getPropertiesData("LoginPage.properties");
		driver.get(env.get("url"));
	}

	public static void AlertDismiss() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static void AlertAccept() {
		System.out.println(driver.switchTo().alert().getText());
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public static void sendKeys(String locatorType, String locatorValue, String value) throws Exception {
		WebElement we=TestBase.getLocator(driver,locatorType,locatorValue);
		we.sendKeys(value);
	}
	public static void clickOn(String locatorType, String locatorValue) throws Exception {
		Thread.sleep(1000);
		WebElement we=TestBase.getLocator(driver,locatorType,locatorValue);
		we.click();
		
	}
	public static void quitDriver() throws Exception {
		driver.quit();
		
	}

	

	
}
