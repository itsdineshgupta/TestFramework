package com.gradeup.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class GeneralFunctions {  

	public static String getscreenshot(WebDriver driver, String screenshotFolder, String screenshotName) throws IOException, InterruptedException, WebDriverException{
		{
			if(driver!=null){
				
				Thread.sleep(2000);
				
				if (screenshotFolder.equalsIgnoreCase("")){
					screenshotFolder = "target/Cucumber-reports/Screenshots";
				}

				if(screenshotName.equalsIgnoreCase(""))
					screenshotName = "Screenshot";

				screenshotName = screenshotName + "_" + GeneralFunctions.gettimeStamp() +".png";

				String destination = screenshotFolder + "\\" + screenshotName;

				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);

				File target = new File(destination);

				FileUtils.copyFile(src, target);

				Thread.sleep(2000);

				return destination;
			}
		}
		return null;
	}

	public static String getscreenshot(WebDriver driver) throws IOException, InterruptedException, WebDriverException{
		{
			if(driver!=null){
				
				Thread.sleep(2000);

				String screenshotFolder = "target/Cucumber-reports/Screenshots";

				String screenshotName = "Screenshot_" +GeneralFunctions.gettimeStamp() +".png";

				String destination = screenshotFolder + "\\" + screenshotName;

				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);

				File target = new File(destination);

				FileUtils.copyFile(src, target);
				
				Thread.sleep(2000);

				return destination;
			}
		}
		return null;
	}

	/**
	 * Get the Timestamp in format i.e. yyyy-MM-dd HH-mm-ss
	 * 
	 * @return
	 */
	public static String gettimeStamp(){
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		return dateFormat.format(date);
	}

	/**
	 * Get the Date in format i.e. dd-MM-yyyy
	 * 
	 * @return
	 */
	public static String getDate(){
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(date);
	}
	
	/**
	 * Generate the random number
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static long randonNumber(long min,long max)
	{
		if(min>max)
		{
			throw new IllegalArgumentException("Invalid Range");
		}
		double rand = Math.random();
		return (int)(rand*((max-min))+1)+min;
	}
	
	/**
	 * Generate the random number
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randonNumber(int  min,int  max)
	{
		if(min>max)
		{
			throw new IllegalArgumentException("Invalid Range");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	

}