package com.gradeup.configuration;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gradeup.helpers.Constants;
import com.gradeup.helpers.Log;
import com.gradeup.helpers.PropertyReader;

/**
 * 
 * @author dgupta54
 *
 */
public class DriverFactory {

	private static final String WORK_DIRECTORY = System.getProperty("user.dir");

	protected static WebDriver driver;
	private static String pathToDrivers;

	protected static WebDriverWait MICRODRIVERWAIT, MINDRIVERWAIT, MIDDRIVERWAIT, MAXDRIVERWAIT, ULTRAMAXEDDRIVERWAIT;
	protected static Wait<WebDriver> MINFLUENTWAIT, MIDFLUENTWAIT, MAXFLUENTWAIT;
	protected static Robot ROBOT;

	public static JavascriptExecutor JAVASCRIPTEXECUTOR;

	public static Actions ACTION;

	/**
	 * Get Instance of Driver
	 * 
	 * @return
	 */
	public WebDriver getDriver() {			
		return driver;
	}

	/**
	 * Initialize Instance of Driver
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void initialize() {
		if (driver == null) {

			String browser =PropertyReader.getInstance().readProperty("browser");
			String os = PropertyReader.getInstance().readProperty("os");
			String platform = browser.trim() + "_" + os.trim();

			Log.info("Creating Driver instance for Local");
			driver = initBrowserForDriver(platform);

			driver.manage().timeouts().implicitlyWait(Constants.IMPLICITWAIT, TimeUnit.SECONDS);

			MICRODRIVERWAIT = new WebDriverWait(driver, 5);
			MINDRIVERWAIT = new WebDriverWait(driver, 15);
			MIDDRIVERWAIT = new WebDriverWait(driver, 30);
			MAXDRIVERWAIT = new WebDriverWait(driver, 45);
			ULTRAMAXEDDRIVERWAIT = new WebDriverWait(driver, 60);

			MINFLUENTWAIT = new FluentWait<WebDriver>(driver)
					.withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			MIDFLUENTWAIT = new FluentWait<WebDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			MAXFLUENTWAIT = new FluentWait<WebDriver>(driver)
					.withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			JAVASCRIPTEXECUTOR = (JavascriptExecutor) driver;

			ACTION = new Actions(driver);

			try {
				ROBOT = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialize Instance of Driver
	 * 
	 */
	@SuppressWarnings("deprecation")
	public void initialize(String browser, String os) {
		if (driver == null) {

			//String browser =PropertyReader.getInstance().readProperty("browser");
			//String os = PropertyReader.getInstance().readProperty("os");
			String platform = browser.trim() + "_" + os.trim();

			Log.info("Creating Driver instance for Local");
			driver = initBrowserForDriver(platform);

			driver.manage().timeouts().implicitlyWait(Constants.IMPLICITWAIT, TimeUnit.SECONDS);

			MICRODRIVERWAIT = new WebDriverWait(driver, 5);
			MINDRIVERWAIT = new WebDriverWait(driver, 15);
			MIDDRIVERWAIT = new WebDriverWait(driver, 30);
			MAXDRIVERWAIT = new WebDriverWait(driver, 45);
			ULTRAMAXEDDRIVERWAIT = new WebDriverWait(driver, 60);

			MINFLUENTWAIT = new FluentWait<WebDriver>(driver)
					.withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			MIDFLUENTWAIT = new FluentWait<WebDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			MAXFLUENTWAIT = new FluentWait<WebDriver>(driver)
					.withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			JAVASCRIPTEXECUTOR = (JavascriptExecutor) driver;

			ACTION = new Actions(driver);

			try {
				ROBOT = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialize Browser
	 * 
	 * @param platform
	 * @return
	 */
	private WebDriver initBrowserForDriver(String platform) {
		// Write code here that turns the phrase above into concrete actions
		String path = "/drivers/";

		if (platform.toUpperCase().contains("MAC")) {
			pathToDrivers = path.replace("/", "//");
		} else {
			pathToDrivers = path.replace("/", "\\\\");
		}

		switch (platform.toUpperCase()) {
		case "FIREFOX_WINDOWS":
			Log.info("Setting up Firefox Driver for Windows");
			setFireFoxLocalDriverForWindows();
			break;
		case "FIREFOX_MAC":
			Log.info("Setting up Firefox Driver for Mac");
			setFireFoxLocalDriverForMac();
			break;
		case "CHROME_WINDOWS":
			Log.info("Setting up Chrome Driver for Windows");
			setChromeLocalDriverForWindows();
			break;
		case "CHROME_MAC":
			Log.info("Setting up Chrome Driver for Mac");
			setChromeLocalDriverForMac();
			break;
		case "INTERNETEXPLORER_WINDOWS":
			Log.info("Setting up Internet Explorer Driver for Windows");
			setInternetExplorerDriverForWindows();
			break;
		default:
			Log.error("Select correct platform in Properties file");
			Assert.fail("Select correct platform in Properties file");
		}
		return driver;
	}

	/**
	 * Set Chrome Local Driver for Windows
	 * 
	 */
	private static void setChromeLocalDriverForWindows() {
		System.setProperty("webdriver.chrome.driver", WORK_DIRECTORY + pathToDrivers + "\\chromedriver.tmp");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--js-flags=--expose-gc");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("test-type=browser");
		options.addArguments("disable-infobars");
		options.addArguments("disable-extensions");
		//Load Default Memory to Browser
		options.addArguments("user-data-dir=C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Google\\Chrome\\User Data");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		//options.addArguments("--enable-automation");
		driver = new ChromeDriver(options);
	}

	/**
	 * Set Chrome Local Driver for Mac
	 * 
	 */
	private void setChromeLocalDriverForMac() {
		System.setProperty("webdriver.chrome.driver", WORK_DIRECTORY + pathToDrivers + "chrome//chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver(options);
	}

	/**
	 * Set Firefox Local Driver for Mac
	 * 
	 */
	private void setFireFoxLocalDriverForMac() {
		System.setProperty("webdriver.gecko.driver", WORK_DIRECTORY + pathToDrivers + "firefox//geckodriver");
		FirefoxOptions options = new FirefoxOptions();
		options.setAcceptInsecureCerts(true);
		options.setCapability("marionette", true);
		driver = new FirefoxDriver(options);
	}

	/**
	 * Set Firefox Local Driver for Windows
	 * 
	 */
	private void setFireFoxLocalDriverForWindows() {
		System.setProperty("webdriver.gecko.driver", WORK_DIRECTORY + pathToDrivers + "\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setAcceptInsecureCerts(true);
		options.setCapability("marionette", true);
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,application/pdf");
		options.addPreference("browser.download.manager.showWhenStarting", false);
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.alwaysAsk.force", false);
		options.addPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		options.addPreference("plugin.disable_full_page_plugin_for_types", "application/octet-stream,application/pdf");
		options.addPreference("pdfjs.disabled", true);
		driver = new FirefoxDriver(options);
	}

	/**
	 * Set Internet Explorer Driver for Windows
	 * 
	 */
	private void setInternetExplorerDriverForWindows() {
		System.setProperty("webdriver.ie.driver", WORK_DIRECTORY + pathToDrivers + "\\IEDriverServer.exe");
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		options.setCapability("ignoreZoomSetting", true);
		options.setCapability("ignoreProtectedModeSettings", true);
		options.setCapability("requireWindowFocus", true);
		options.setCapability("unexpectedAlertBehaviour", "ignore");
		driver = new InternetExplorerDriver(options);
	}

	/**
	 * Destroy existing Driver instance
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 *
	 */
	public void destroyDriver() throws IOException, InterruptedException {
		driver.quit();
		driver = null;
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.tmp");
	}
}