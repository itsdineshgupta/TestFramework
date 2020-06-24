package com.gradeup.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gradeup.utilities.GeneralFunctions;

/**
 * 
 * @author dgupta54
 *
 */
public class TestPage extends BasePage{

	By buttonList = By.xpath(".//button");

	By list_Question = By.xpath(".//ul[@class='pv2 ph0 ph2-l ma0 list']");
	By list_CorrectAnswers = By.xpath(".//ul[@class='pv2 ph0 ph2-l ma0 list']//li[contains(@class,'bg-light-green')]");
	By list_WrongAnswers = By.xpath(".//ul[@class='pv2 ph0 ph2-l ma0 list']//li[contains(@class,'bg-light-red')]");

	By button_Submit = By.xpath(".//div[contains(@class,'attempt-aside')]//button");
	By button_CancelConfirmation = By.xpath(".//div[contains(@class,'discard-post-card')]//a[1]");
	By button_SubmitConfirmation = By.xpath(".//div[contains(@class,'discard-post-card')]//a[2]");

	public boolean selectTest() throws InterruptedException {
		waitForPageToLoad();
		waitTimer(5);
		
		List<WebElement> button_StartTestList = driver.findElements(buttonList);
		for(WebElement button: button_StartTestList) {
			
			if(button.findElement(By.xpath("./span")).getText().equalsIgnoreCase("Start Test")) {
				button.click();
				return true;
			}
		}
		return false;
	}

	public boolean selectRandomAnswers() throws InterruptedException {
		waitForPageToLoad();
		waitTimer(5);
		List<WebElement> questionList = driver.findElements(list_Question);
		for(WebElement question:questionList) {
			List<WebElement> options = question.findElements(By.xpath(".//li"));
			options.get(GeneralFunctions.randonNumber(0, 4)).click();
			//waitForPageToLoad();
		}
		return true;
	}

	public Map<String, String> getAnswerData() throws InterruptedException {
		waitForPageToLoad();
		List<WebElement> totalanswers = driver.findElements(list_Question);
		List<WebElement> correctAnswers = driver.findElements(list_CorrectAnswers);
		List<WebElement> wrongAnswers = driver.findElements(list_WrongAnswers);

		Map<String, String> data = new HashMap<>();
		data.put("wrong", String.valueOf(wrongAnswers.size()));
		data.put("correct", String.valueOf(correctAnswers.size()-wrongAnswers.size()));
		data.put("unattempted", String.valueOf(totalanswers.size()-correctAnswers.size()));

		return data;
	}

	public void submitPaper() throws InterruptedException {
		waitForPageToLoad();
		driver.findElement(button_Submit).click();
		waitForPageToLoad();
		driver.findElement(button_SubmitConfirmation).click();;
		waitForPageToLoad();
	}

}