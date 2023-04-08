package com.lambdatest.driver;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.lambdatest.util.ReadConfigFile;
import com.lambdatest.util.WebEventListenerTracker;

public class BrowserDriver {
	
	private static WebDriver webDriver = null;
	private static EventFiringWebDriver eventFiringWebDriver;
	private static WebEventListenerTracker tracker; 
	
	public static WebDriver launchDriver() {

		Properties properties = ReadConfigFile.getProperty("config");
		String browserName = properties.get("browserName").toString();
		String browserURL = properties.get("browserURL").toString();
		

		switch (browserName) {

		case "chrome":
			webDriver =  launchChrome();
			break;

		case "safari":
			webDriver = launchSafari();
			break;

		case "firefox":
			webDriver = launchFireFox();
			break;
			
		case "IE":
			webDriver = launchIE();
			break;
		}
		
		
		eventFiringWebDriver = new EventFiringWebDriver(webDriver);
		tracker = new WebEventListenerTracker();
		eventFiringWebDriver = eventFiringWebDriver.register(tracker);
		webDriver = eventFiringWebDriver;
		
		webDriver.manage().window().maximize();
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		webDriver.get(browserURL);
		
		return webDriver;
	}

	
	public static void start() {
		if(webDriver==null) {
			launchDriver();
		}
	}
	
	public static void stop() {
		if(webDriver != null) {
			webDriver.quit();
			webDriver = null;
		}
	}
	
	public static WebDriver getWebDriver() {
		return webDriver;
	}
	
	public static WebDriver launchChrome() {
		String basePath = System.getProperty("user.dir");
		String filePath = basePath + "/src/test/resources/driver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", filePath);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("--remote-allow-origins=*");
		return new ChromeDriver(chromeOptions); // laun- browser
	}

	public static WebDriver launchSafari() {
		return new SafariDriver();
	}
	
	public static WebDriver launchFireFox() {

		return new FirefoxDriver();
	}
	
	public static WebDriver launchIE() {
		return new InternetExplorerDriver();
	}

}
