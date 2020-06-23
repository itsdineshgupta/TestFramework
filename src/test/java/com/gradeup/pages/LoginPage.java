package com.gradeup.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.annotations.BeforeTest;

import com.gradeup.helpers.Log;

/**
 * 
 * @author dgupta54
 *
 */
public class LoginPage extends BasePage{

	By button_savedGmail = By.id("continue-as");

	//@BeforeTest
	public boolean performLogin() throws InterruptedException {
		try{
			waitForPageToLoad();
			waitTimer(30);
			//System.out.println(driver.findElements(By.tagName("iframe")).size());
			driver.switchTo().frame(0);
			driver.switchTo().frame(3);
			waitForPageToLoad();
			//driver.switchTo().frame(driver.findElement(button_savedGmail));
			//MIDDRIVERWAIT.until(ExpectedConditions.visibilityOfElementLocated(button_savedGmail));
			driver.findElement(button_savedGmail).click();
			waitForPageToLoad();
			Log.info("Logged in");
			Assert.assertTrue(true);
		}
		catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("No Saved Gmail account found");
			Log.error("No Saved Gmail account found");
			Assert.assertTrue(false);
		}
		Log.error("No Saved Gmail account found");
		Assert.assertTrue(false);
		return false;
	}
}