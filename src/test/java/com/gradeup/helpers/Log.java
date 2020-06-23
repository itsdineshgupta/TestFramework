package com.gradeup.helpers;

import org.apache.log4j.Logger;

/**
 * 
 * @author dgupta54
 *
 */
public class Log {

	public static Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());

	/**
	 * Start the Logger instance for individual test
	 * 
	 * @param featureName
	 * @param scenarioName
	 */
	public static void startTest() {
		logger.info("-------------------------------------<<<<<>>>>>-------------------------------------");
		logger.info("################################### TEST STARTED ###################################");
		logger.info("-------------------------------------<<<<<>>>>>-------------------------------------");
	}

	/**
	 * Add a information line in Logger file
	 * 
	 * @param desc
	 */
	public static void info(String desc) {

		logger.info(desc);
	}

	/**
	 * Add a error line in Logger file
	 * 
	 * @param desc
	 */
	public static void error(String desc) {
		logger.error(desc);
	}

	/**
	 * End the Logger Instance for individual test
	 */
	public static void endTest() {
		logger.info("=====================================================================================");
		logger.info("===================================== TEST ENDED ====================================");
		logger.info("=====================================================================================");
	}
}