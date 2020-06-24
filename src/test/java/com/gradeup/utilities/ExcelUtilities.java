package com.gradeup.utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.gradeup.helpers.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author dgupta54
 *
 */
public class ExcelUtilities {

	/**
	 * Return the Sheet index
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static int getSheetIndex(String excelFilePath, String sheetName) throws IOException, InvalidFormatException {

		int sheetNumber=0;
		int check = 0;

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));

		for(Sheet sheet: workbook) {
			if (sheet.getSheetName().equalsIgnoreCase(sheetName)){
				check = 1;
				break;
			}
			else{
				sheetNumber = sheetNumber + 1;
			}
		}

		if (check == 0)
			sheetNumber = -1;

		workbook.close();
		return sheetNumber;
	}

	/**
	 * Return the Row Index
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * @param rowID
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static int getRowIndex(String excelFilePath, String sheetName, String rowID) throws IOException, InvalidFormatException {

		int sheetNumber = getSheetIndex(excelFilePath, sheetName);

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(sheetNumber);

		// Iterate over the rows and columns
		int rowIndexExcel = -1;

		for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(0);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equalsIgnoreCase(rowID)){
					rowIndexExcel = rowIndex;
					break;
				}
			}
		}
		workbook.close();
		return rowIndexExcel;
	}

	/**
	 * Return the column index
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * @param columnName
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static int getColumnIndex(String excelFilePath, String sheetName, String columnName) throws IOException, InvalidFormatException {

		int sheetNumber = getSheetIndex(excelFilePath, sheetName);

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(sheetNumber);

		// Iterate over the rows and columns
		int colIndexExcel = -1;

		Row row = sheet.getRow(0);
		if (row != null) {
			for( int colIndex=0;colIndex <= row.getLastCellNum(); colIndex++){
				Cell cell = row.getCell(colIndex);
				String cellValue = cell.getStringCellValue();
				if (cellValue.equalsIgnoreCase(columnName)){
					colIndexExcel = colIndex;
					break;
				}
			}
		}
		workbook.close();
		return colIndexExcel;
	}

	/**
	 * Read the cell value from Excel
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * @param columnName
	 * @param rowID
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	@SuppressWarnings("deprecation")
	public static String ReadExcelData (String excelFilePath, String sheetName, String columnName, String rowID) throws IOException, InvalidFormatException {

		String cellValue= "";

		int sheetNumber = getSheetIndex(excelFilePath, sheetName);

		int rowIndexExcel = getRowIndex(excelFilePath, sheetName, rowID);
		int colIndexExcel = getColumnIndex(excelFilePath, sheetName, columnName);

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(sheetNumber);

		Row row = sheet.getRow(rowIndexExcel);
		if (row != null) {
			Cell cell = row.getCell(colIndexExcel);
			if (cell != null) {
				if(cell.getCellType() != Cell.CELL_TYPE_STRING) 
					cell.setCellType(Cell.CELL_TYPE_STRING);

				cellValue = cell.getStringCellValue();
			}
		}
		workbook.close();
		return cellValue;
	}

	/**
	 * Write the data to Excel
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * @param columnName
	 * @param rowID
	 * @param cellValue
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static boolean WriteExcelData (String excelFilePath, String sheetName, String columnName, String rowID, String cellValue) throws IOException, InvalidFormatException {

		File file;

		int sheetNumber = getSheetIndex(excelFilePath, sheetName);

		int rowIndexExcel = getRowIndex(excelFilePath, sheetName, rowID);

		int colIndexExcel = getColumnIndex(excelFilePath, sheetName, columnName);

		//String strCheck = "FALSE";

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(excelFilePath));

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		Cell cell = null;
		Row row = sheet.getRow(rowIndexExcel);
		if (row == null) {
			row = sheet.createRow(sheet.getLastRowNum()+1);
			//row = sheet.createRow(rowIndexExcel);
		}

		cell = row.createCell(colIndexExcel);
		cell.setCellValue(cellValue);

		//Create new WriteExcel file to write the output
		String filename = "TestDataWrite.xlsx";
		String excelFilePath1 = System.getProperty("user.dir") + "\\" + filename;
		file = new File(excelFilePath1);

		//Delete if WriteExcel file if exists
		if (file.exists())
			file.delete();

		//Write the data into WriteExcel
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		workbook.close();

		//Delete the Original file
		file = new File(excelFilePath);
		if (file.exists())
			file.delete();

		new File(excelFilePath1).renameTo(file);

		return true;
	}

	/**
	 * Write the data to Excel in Bulk
	 * 
	 * @param excelFilePath
	 * @param sheetName
	 * @param key
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static boolean WriteExcelDataInBulk (String excelFilePath, String sheetName, String key, Map<String, Integer> columnIndexing, List<Map<String, String>> data) throws IOException, InvalidFormatException {

		int sheetNumber = getSheetIndex(excelFilePath, sheetName);
		
		InputStream inp = new FileInputStream(excelFilePath); 
		Workbook wb = WorkbookFactory.create(inp); 
		Sheet sheet = wb.getSheetAt(sheetNumber); 
		
		Row row;

		for(int i=0;i<data.size();i++) {
			System.out.println("Record Number" + i);
			int rowIndexExcel = getRowIndex(excelFilePath, sheetName, data.get(i).get(key));
			
			if(rowIndexExcel == -1) {
				int lastRow = sheet.getLastRowNum();
				row = sheet.createRow(lastRow+1); 
				System.out.println(data.get(i).get(key) + " recordID not found in Excel");
				Log.info(data.get(i).get(key) + " recordID not found in Excel");
				for(String keyName: data.get(i).keySet()) {
					row.createCell(columnIndexing.get(keyName)).setCellValue(data.get(i).get(keyName));
					//System.out.println("Entered "+keyName + " as "+data.get(i).get(keyName));
				}
			}
			else {
				System.out.println(data.get(i).get(key) + " recordID already there in Excel");
				Log.info(data.get(i).get(key) + " recordID already there in Excel");
				continue;
			}
		}

		// Now this Write the output to a file 
		FileOutputStream fileOut = new FileOutputStream(excelFilePath); 
		wb.write(fileOut); 
		fileOut.close();

		return true;
	}
}