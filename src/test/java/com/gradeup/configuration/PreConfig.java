package com.gradeup.configuration;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.gradeup.helpers.Log;
import com.gradeup.helpers.PropertyReader;

/**
 * 
 * @author dgupta54
 *
 */
public class PreConfig extends DriverFactory{

	private String url = PropertyReader.getInstance().readProperty("region");
	
	@Parameters({ "browserType", "os" })
	@BeforeSuite
	public void openInstance(String browser, String os) {	
		
		Log.startTest();
		if(driver == null) {
			System.out.println("Creating new instance of Driver");
			initialize(browser, os);
		}
		
		driver = getDriver();

		driver.manage().window().maximize();
		driver.get(url);
	}

}