package com.gradeup.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

/**
 * 
 * @author dgupta54
 *
 */
public class ResultPage extends BasePage{

	By label_Score = By.xpath("(.//h1)[1]");
	By label_Rank = By.xpath("(.//h1)[2]");
	By label_CutOff = By.xpath(".//h2/parent::div/parent::div/parent::div//span");
	By label_Correct = By.xpath("(.//div[contains(@class,'performance-overview')]//span)[2]");
	By label_Incorrect = By.xpath("(.//div[contains(@class,'performance-overview')]//span)[4]");
	By label_Unattempted = By.xpath("(.//div[contains(@class,'performance-overview')]//span)[6]");

	public Map<String, String> getctualData() throws InterruptedException {
		waitForPageToLoad();
		waitTimer(5);

		Map<String, String> data = new HashMap<>();
		data.put("wrong", driver.findElement(label_Incorrect).getText().trim());
		data.put("correct", driver.findElement(label_Correct).getText().trim());
		data.put("unattempted", driver.findElement(label_Unattempted).getText().trim());
		String[] cutOffMarks = driver.findElement(label_CutOff).getText().trim().split("/");
		data.put("cutoff", cutOffMarks[0].trim());
		data.put("score", driver.findElement(label_Score).getText().trim());
		data.put("rank", driver.findElement(label_Rank).getText().trim());

		return data;
	}

}