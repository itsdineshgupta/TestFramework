package com.gradeup.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.gradeup.configuration.DriverFactory;
import com.gradeup.helpers.Constants;
import com.gradeup.helpers.Log;
import com.gradeup.utilities.GeneralFunctions;

/**
 * 
 * @author dgupta54
 *
 */
public class BasePage extends DriverFactory{

	/**
	 * Store the Start Time
	 * 
	 */
	public static void storeStartTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		//Check Login Time
		Constants.STARTTIME = LocalDateTime.parse(GeneralFunctions.gettimeStamp(), formatter);
	}

	/**
	 * Store the End Time
	 * 
	 */
	public static void storeEndTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		//Check Login Time
		Constants.ENDTIME = LocalDateTime.parse(GeneralFunctions.gettimeStamp(), formatter);
	}

	/**
	 * Calculate the time difference for Performance Testing
	 * 
	 * @return
	 */
	public static long calculateTime() {
		if(Constants.STARTTIME.equals("") || Constants.ENDTIME.equals("") || Constants.STARTTIME.equals(null) || Constants.ENDTIME.equals(null)) {
			Log.error("Please check the Start/End Time as it is null");
			return 0;
		}
		else if(Constants.ENDTIME.isBefore(Constants.STARTTIME)) {
			Log.error("Please check the Start/End Time as Start Time is greater than End Time");
			return 0;
		}
		else{
			Log.info("Time taken(in milli sec):- " + java.time.Duration.between(Constants.STARTTIME, Constants.ENDTIME).toMillis());
			return java.time.Duration.between(Constants.STARTTIME, Constants.ENDTIME).toMillis();
		}
	}

	/**
	 * It will wait or hold for given seconds
	 * 
	 * @throws InterruptedException
	 */
	public static void waitTimer(int seconds) throws InterruptedException {
		Thread.sleep(seconds*1000);
	}

	/**
	 * It will wait or hold until the page loads
	 * 
	 * @throws InterruptedException
	 */
	public static void waitForPageToLoad() throws InterruptedException {
		do {
			Thread.sleep(1000);
		}while(!JAVASCRIPTEXECUTOR.executeScript("return document.readyState").equals("complete"));
	}

}