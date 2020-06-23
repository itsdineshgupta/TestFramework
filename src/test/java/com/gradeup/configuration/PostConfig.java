package com.gradeup.configuration;

import java.io.IOException;

import org.testng.annotations.AfterSuite;

import com.gradeup.helpers.Log;

/**
 * 
 * @author dgupta54
 *
 */
public class PostConfig extends DriverFactory{

	@AfterSuite
	public void quitBrowser() throws IOException, InterruptedException {
		Log.endTest();
		destroyDriver();
	}

}