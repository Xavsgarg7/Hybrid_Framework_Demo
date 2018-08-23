package com.hybridFramework.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hybridFramework.testBase.TestRunner;

public class ExcelReader {

	public static String separator = System.getProperty("file.separator");
	public static final Logger logger = Logger.getLogger(ExcelReader.class.getName());

	public static String[][] readExcel(String File, String SheetName) {

		String data[][] = null;
		String res;

		try {

			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+separator+"src"+separator+ "main"+separator+"java"+separator+"com"+separator+"hybridFramework"+separator+"data"+separator+ File ));
					
			// System.getProperty("user.dir")
			// +separator+"src"+separator+"main"+separator+"java"+separator+"com"+separator+"automation"+separator+"POM"+separator+"Resources"+separator+"InputFiles"+separator+"Excels"+separator+File));

			logger.debug("Get the workbook instance for XLS file");
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(SheetName);

			// Get the number of rows
			int rowCount = sheet.getLastRowNum();

			// Get the number of columns
			int colCount = sheet.getRow(0).getLastCellNum();

			data = new String[rowCount+ 1][colCount];
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {

					Object result = null;
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						// System.out.print(cell.getBooleanCellValue() +
						// "\t\t");
						result = cell.getBooleanCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// System.out.print(cell.getNumericCellValue() +
						// "\t\t");
						double numeric = cell.getNumericCellValue();
						int num = (int) numeric;
						result = String.valueOf(num);
						break;
					case Cell.CELL_TYPE_STRING:
						// System.out.print(cell.getStringCellValue() + "\t\t");
						result = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_BLANK:
						result = "";
						break;
					}

					data[i][j] = (String) result;

					// System.out.print(i+" "+j);
					j++;

				}
				// System.out.println("");
				i++;
			}
			file.close();
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
