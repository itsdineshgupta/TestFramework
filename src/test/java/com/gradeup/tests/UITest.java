package com.gradeup.tests;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gradeup.helpers.Constants;
import com.gradeup.helpers.Log;
import com.gradeup.pages.HomePage;
//import com.gradeup.pages.LoginPage;
import com.gradeup.pages.ResultPage;
import com.gradeup.pages.TestPage;
import com.gradeup.utilities.ExcelUtilities;

/**
 * 
 * @author dgupta54
 *
 */
public class UITest extends BaseTest {

	@Test
	public void UITest() throws InterruptedException, InvalidFormatException, IOException {
		Log.info("Starting UI Test");
		
		//LoginPage loginPage = new LoginPage();
		//loginPage.performLogin();
		
		//Open Prev Papers from Menu
		HomePage homePage = new HomePage();
		Log.info("Selecting 'Prev. Papers' under 'Bank & Insurance' Menu");
		Assert.assertTrue(homePage.selectMenu("Prev. Papers", "Bank & Insurance"));
		
		
		//Select Answers
		TestPage testPage = new TestPage();
		Log.info("Select 1st incomplete Test");
		Assert.assertTrue(testPage.selectTest());
		
		Log.info("Selecting Random answers");
		testPage.selectRandomAnswers();
		Map<String, String> expectedData = testPage.getAnswerData();
		
		Log.info("Submit Exam");
		testPage.submitPaper();
		
		ResultPage resultPage = new ResultPage();
		Map<String, String> ActualData = resultPage.getctualData();

		Assert.assertEquals(expectedData.get("wrong").trim().toUpperCase(), ActualData.get("wrong").trim().toUpperCase(), "Matching Wrong answers count");
		Assert.assertEquals(expectedData.get("correct").trim().toUpperCase(), ActualData.get("correct").trim().toUpperCase(), "Matching Correct answers count");
		Assert.assertEquals(expectedData.get("unattempted").trim().toUpperCase(), ActualData.get("unattempted").trim().toUpperCase(), "Matching Unattempted answers count");
	
		ExcelUtilities.WriteExcelData(Constants.UIEXCELPATH, "Sheet1", "wrong", "data", ActualData.get("wrong").trim());
		ExcelUtilities.WriteExcelData(Constants.UIEXCELPATH, "Sheet1", "correct", "data", ActualData.get("correct").trim());
		ExcelUtilities.WriteExcelData(Constants.UIEXCELPATH, "Sheet1", "unattempted", "data", ActualData.get("unattempted").trim());
		ExcelUtilities.WriteExcelData(Constants.UIEXCELPATH, "Sheet1", "cutoff", "data", ActualData.get("cutoff").trim());
		ExcelUtilities.WriteExcelData(Constants.UIEXCELPATH, "Sheet1", "score", "data", ActualData.get("score").trim());
		ExcelUtilities.WriteExcelData(Constants.UIEXCELPATH, "Sheet1", "rank", "data", ActualData.get("rank").trim());
	}

}