package com.Util;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestConfig {
	
	/********This class is to maintain driver initialization based on the browser or device ***********/

	public WebDriver driver;
	public WebDriver initialiseDriver(String browser,WebDriver driver)  throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (browser.toLowerCase()) {
		case  "chrome":
		
			capabilities = DesiredCapabilities.chrome();
			
			if(ReadTestDataHelper.getPropValue(ConfigConstants.WW_DataFile, ConfigConstants.OS).equalsIgnoreCase("windows"))
			System.setProperty("webdriver.chrome.driver",ConfigConstants.LOC_CHROMEEXE);
			else if(ReadTestDataHelper.getPropValue(ConfigConstants.WW_DataFile, ConfigConstants.OS).equalsIgnoreCase("mac"))
			System.setProperty("webdriver.chrome.driver",ConfigConstants.LOC_CHROMEMAC);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver=new ChromeDriver(); 
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(ConfigConstants.TIMEOUT_IMPLICITWAIT, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(ConfigConstants.TIMEOUT_PAGELOAD, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			moveMouseOutOfWindow();
		
		break;
		
		case "internetexplorer":
			
           break;
		
		case "firefox":
			
	           break;
		
		}
		return driver;
	}
	
	private void moveMouseOutOfWindow(){
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
		}
		robot.mouseMove(0, 0);
	}
	
	
}
