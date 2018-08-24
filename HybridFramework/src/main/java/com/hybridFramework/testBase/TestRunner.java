package com.hybridFramework.testBase;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hybridFramework.excelReader.ExcelReader;
import com.hybridFramework.uiActions.ActionKeyword;

public class TestRunner {
	public static final Logger logger = Logger.getLogger(TestRunner.class.getName());
	
	public static void main(String args[]) {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Executer ex = new Executer();
		try {
			ex.splitTestCases();
		} catch (Exception e) {}

	}
}

class Executer {

	public static final Logger logger1 = Logger.getLogger(TestRunner.class.getName());
	
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Reports/extent.html");
	ExtentReports extent = new ExtentReports();
	ExtentTest exTest;
	
	
	
	String testSuite[][] = ExcelReader.readExcel("Testsuite1.xlsx", "TestSuite");
	int testSuiteLength = testSuite.length;

	ArrayList<String> testCasesList1 = new ArrayList<String>();
	ArrayList<String> testCasesList2 = new ArrayList<String>();

	ArrayList<String> testCaseNamesList1 = new ArrayList<String>();
	ArrayList<String> testCaseNamesList2 = new ArrayList<String>();

	ArrayList<String> testCases = new ArrayList<String>();
	ArrayList<String> testCaseNames = new ArrayList<String>();

	public void splitTestCases() throws Exception {
		System.out.println("My Test suite is having " + (testSuiteLength - 1) + " Test cases");
		if (testSuiteLength > 2) {
			System.out.println("My Test suite is having multiple Test case");
			for (int i = 1; i <= testSuiteLength / 2; i++) {
				testCasesList1.add(testSuite[i][0]);
				testCaseNamesList1.add(testSuite[i][1]);

			}
			for (int i = (testSuiteLength / 2) + 1; i < testSuiteLength; i++) {
				testCasesList2.add(testSuite[i][0]);
				testCaseNamesList2.add(testSuite[i][1]);

			}
			
			System.out.println(testCaseNamesList1 + "----testList1-----");
			System.out.println(testCaseNamesList2 + "----testList2-----");
			
			Thread t1 = new Thread(new Runnable() {
				public void run() {
					try {
						executeTestCases(testCasesList1, testCaseNamesList1);
					} catch (Exception e) {}
				}
			});

			t1.start();
			Thread t2 = new Thread(new Runnable() {
				public void run() {
					try {
						executeTestCases(testCasesList2, testCaseNamesList2);
					} catch (Exception e) {}
				}
			});
			TimeUnit.MILLISECONDS.sleep(1000);
			t2.start();

		} else {
			for (int i = 1; i < testSuiteLength; i++) {
				testCases.add(testSuite[i][0]);
				testCaseNames.add(testSuite[i][1]);

			}
			executeTestCases(testCases, testCaseNames);
		}
		
		/*for (int i = 1; i < testSuiteLength; i++) {
			testCases.add(testSuite[i][0]);
			testCaseNames.add(testSuite[i][1]);

		}*/
		executeTestCases(testCases, testCaseNames);

	}

	public void executeTestCases(ArrayList<String> testCases, ArrayList<String> testCaseNames) throws Exception {
		extent.attachReporter(htmlReporter);
		
		for (int j = 0; j < testCases.size(); j++) {
			String testCaseID = testCases.get(j);
			String fileName=testCaseID + ".xlsx";
			String testCaseName = testCaseNames.get(j);
			String excelData[][] = ExcelReader.readExcel("Testsuite1.xlsx", testCaseID);
			System.out.println("******Running " + testCaseName + "******");
			
			exTest=extent.createTest(testCaseName);

			for (int i = 1; i < excelData.length; i++) {

				String keyword = excelData[i][2];
				if (keyword.equals("openBrowser")) {
					logger1.info("opening the browser");
					ActionKeyword.openBrowser();
				} else if (keyword.equals("navigate")) {
					logger1.info("navigating to the site");
					ActionKeyword.navigate();
				} else if (keyword.equals("sendkeys")) {
					logger1.info("passing the values");
					ActionKeyword.sendKeys(excelData[i][3], excelData[i][4], excelData[i][5]);
				} else if (keyword.equals("clickOn")) {
					ActionKeyword.clickOn(excelData[i][3], excelData[i][4]);
					logger1.info("clicked on element");
				} 
				else if (keyword.equals("scrollDown")) {
					logger1.info("scrolling...");
					ActionKeyword.scrollDown();

				}else if (keyword.equals("quitDriver")) {
					logger1.info("closing the browser...");
					ActionKeyword.quitDriver();

				}else if (keyword.equals("openInNewTab")) {
					ActionKeyword.openInNewTab(excelData[i][3], excelData[i][4]);
					logger1.info("new tab opening");
				} 
				
				exTest.log(Status.INFO,"passed");
			}

			System.out.println(" ");
            extent.flush();
		}

	}

}
