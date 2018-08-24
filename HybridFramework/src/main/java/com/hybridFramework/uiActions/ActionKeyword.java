package com.hybridFramework.uiActions;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	/*public static void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}*/
	public static void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	public static void openInNewTab(String locatorType, String locatorValue) throws Exception {
		WebElement we=TestBase.getLocator(driver,locatorType,locatorValue);
		Actions oAction = new Actions(driver);
		oAction.moveToElement(we);
		oAction.contextClick(we).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform(); 
	}

	

	
}
