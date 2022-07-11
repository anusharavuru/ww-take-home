package com.Util;


import java.util.List;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class WWElements {

	/********This class is to maintain common methods used to perform user actions on the application ***********/

	protected WebDriver driver;
	protected VerificationsHelper verify;
	protected Logger Log = Logger.getLogger("PageLogs");
	protected SoftAssert softasert;
	
	public WWElements(WebDriver driver,SoftAssert softasert) {
		this.driver = driver;
		this.softasert=softasert;
		verify=new VerificationsHelper(softasert);
	}
	

	public WebElement getObject(String by,String key) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, ConfigConstants.TIMEOUT_PAGELOAD);
			WebElement element = null;
		
			switch(by.toLowerCase()){
			
			case "xpath":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(key))));
				break;
			case "id":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(key))));
				break;
			case "name":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(key))));
				break;
			case "class":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(key))));
				break;
			
			}
				
			return element;
			
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
		
	}
	public WebElement getObject(String by,String key,long TIMEOUT_PAGELOAD) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_PAGELOAD);
			WebElement element = null;
		
			switch(by.toLowerCase()){
			
			case "xpath":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(key))));
				break;
			case "id":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(key))));	
				break;
			case "name":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(key))));
				break;
			case "class":
				element =wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(key))));
				break;
			
			}
				
			return element;
			
		} catch (Throwable t) {
			
			return null;
		}
		
	}
	public WebElement getObject(String by,String key,String ElementState) {
		try {
			WebElement element = null;
		if(ElementState.equalsIgnoreCase("NotVisible")){
			switch(by.toLowerCase()){
			case "xpath":
				element = driver.findElement(By.xpath(key));
				break;	
			case "id":
				element = driver.findElement(By.id(key));	
				break;
			case "name":
				element = driver.findElement(By.name(key));
				break;
			}
			}
			return element;
			
		} catch (Throwable t) {
			
			return null;
		}
		
	}
	
	public List<WebElement> getObjects(String by,String key) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, ConfigConstants.TIMEOUT_PAGELOAD);
			List<WebElement> element = null;
		
			
			switch(by.toLowerCase()){
			
			case "xpath":
				element =wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(key))));
				break;
			case "id":
				element =wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id(key))));	
				break;
			case "name":
				element =wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.name(key))));
				break;
			case "class":
				element =wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className(key))));
				break;
			
			}
				
			return element;
			
		} catch (Throwable t) {
			t.printStackTrace();
			return null;
		}
		
	}
	/*********Methods to Validate if Page is loaded*********************/
	public void isPageLoaded(){
		isPageLoadedInternal(true,ConfigConstants.DOCUMENT_STATE_COMPLETE);
	}
	
	private void isPageLoadedInternal(boolean checkJQuery,String documentStateToCheck){

		long timeOut = ConfigConstants.TIMEOUT_PAGELOAD*1000;
		long end = System.currentTimeMillis() + timeOut;
		boolean isjQueryDefine = false;
		String documentState = null;
		Long returnedValue = null;
		long startTime = System.currentTimeMillis();

		while (System.currentTimeMillis() < end) {

			try {

				if(checkJQuery){

					try {
						documentState = String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
					} catch (Exception e) {
						return;
					}
					if (documentState.equalsIgnoreCase(ConfigConstants.DOCUMENT_STATE_COMPLETE) || documentState.equalsIgnoreCase(ConfigConstants.DOCUMENT_STATE_INTERACTIVE)) {

						isjQueryDefine = isJQueryDefined();

						if(isjQueryDefine){

							try {
								returnedValue = (Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active");
							} catch (Exception e) {
								return;
							}

							if(returnedValue==0){
								return;
							}
						}else{
							return;
						}
					}

				}else{

					try {

						documentState = String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));

					} catch (Exception e) {
						return;
					}

					System.out.println("Document state : " + documentState);

					if (documentState.equalsIgnoreCase(ConfigConstants.DOCUMENT_STATE_COMPLETE) || documentState.equalsIgnoreCase(ConfigConstants.DOCUMENT_STATE_INTERACTIVE)) {
						return;
					}
				}

			} catch (Exception e) {
				return;
			}
		}
		System.out.println( "Page loading Timed Out... " );	
		Assert.fail("Page loading Timed Out...");
	}

	public boolean isJQueryDefined() {
		try {
			return (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return typeof jQuery == 'function'");
		} catch (Exception e) {
			System.out.println("Exeception while checking if jQuery defined,returning false value.....");
			return false;
		}
	}
	
	public void waitForSpinner() {
		waitForSpinner(false);
	}
	public void waitForSpinner(boolean logError) {

		Boolean loaded = true;
		long timeOut = ConfigConstants.TIMEOUT_SPINNER*1000;
		long end = System.currentTimeMillis() + timeOut;

		while (System.currentTimeMillis() < end){

			try {
				List<WebElement> spinners = driver.findElements(By.className("spinner"));
				for (WebElement spinner : spinners) {
					if (spinner.isDisplayed()) {
						System.out.println("waitForSpinner: Spinner is Displayed");
						loaded = false;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("waitForSpinner: In Exception");
				loaded = true;
			}
			if(loaded){
				return;
			}
		}
		if (logError){
			Log.fatal( "Wait For Spinner Timed Out... " );	
			Assert.fail("Wait For Spinner Timed Out... ");
		}
		else{
			Log.debug( "Wait For Spinner Timed Out... " );			
		}

	}
	
	/********************************************************************************/
	
	public void Web_Click(WebElement element) {
	
				if (element == null) {
					Log.debug("WEB_Click:: " + " Object: '" + element + "' does not available to click");

				} else {
					element.click();

					Log.debug("WEB_Click:: " + " Object: '" + element + "'");
				}
				
			}
		
	public void WEB_JSClick(WebElement element){
		if (element == null) {
			Log.debug("WEB_JSClick:: " + " Object: '" + element + "' does not available to click");
		}
		else{
		element.sendKeys(org.openqa.selenium.Keys.CONTROL);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		}
	}
	
	public void WEB_Clear(WebElement element) {
		if (element != null) {
			element.clear();
			Log.debug("WEB_Clear:: " + " Object: '" + element + "'");
		} else {
			Log.error("WEB_Clear:: " + " Object: '" + element + "' does not available to clear");
		}

	}

	public String Web_GetText(WebElement element) {
		String text=null;
		if (element == null) {
			Log.debug("Web_GetText:: " + " Object: '" + element + "' does not available to get text");

		} else {
			text=element.getText(); 

			Log.debug("Web_GetText:: " + " Object: '" + element + "'");
		}
		return text;
	}
	
	public void WEB_SetText(WebElement element, String text) {
		if (element != null) {
			element.click();
			element.clear();
			element.sendKeys(text);
			Log.debug("WEB_SET:: " + " Object: '" + element + "' Input Text: '" + text + "'");
		} else {
			Log.error("WEB_SET:: " + " Object: '" + element + "' does not available to input text");
		}

	}
	
	public String WEB_GetAttributeValue(WebElement element,String attribute){
		String AttributVal;
		
		if (element != null) {
			
			AttributVal=element.getAttribute(attribute);
				Log.debug("WEB_AttributeValue:: " + " Object: '" + element + attribute
						+ "' contain text:" + AttributVal);
			
			} 
	else {
		AttributVal=null;
		Log.debug("WEB_AttributeValue:: " + " Object: '" + element + attribute
				+ "' does not contain text:" + AttributVal);
		}
		return AttributVal;
	}
}
