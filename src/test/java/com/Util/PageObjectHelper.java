package com.Util;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.testng.asserts.SoftAssert;

import com.Pages.WWFilterSearchPage;
import com.Pages.WWStudioDetailsPage;
import com.Pages.WWStudioFinderPage;

public class PageObjectHelper {
	
	/********This class is to maintain objects of all the pages which makes their access simpler***********/

	protected WebDriver driver;
	SoftAssert softasert=new SoftAssert();
	
	public PageObjectHelper(WebDriver driver,SoftAssert softasert) {
		this.driver = driver;
		this.softasert=softasert;	
	}
	
	protected TestConfig oTestConfig=new TestConfig();
	protected WWStudioFinderPage pWWStudioFinderHomePage;
	protected WWFilterSearchPage pWWStudioSearchPage;
	protected WWStudioDetailsPage pWWStudioDetailsPage;

	public void loadwebPage(String URL) throws IOException {
		System.out.println(URL);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		this.driver.get(URL);
	    this.driver.manage().window().maximize(); 
	}

	public WWStudioFinderPage pWWStudioFinderHomePage(){
		pWWStudioFinderHomePage=new WWStudioFinderPage(driver,softasert);
		pWWStudioFinderHomePage.mIsReady();
		return pWWStudioFinderHomePage;
	}
	
	public WWFilterSearchPage pWWStudioSearchPage(){
		pWWStudioSearchPage=new WWFilterSearchPage(driver,softasert);
		pWWStudioSearchPage.mIsReady();
		return pWWStudioSearchPage;
	}
	
	public WWStudioDetailsPage pWWStudioDetailsPage(){
		pWWStudioDetailsPage=new WWStudioDetailsPage(driver,softasert);
		pWWStudioDetailsPage.mIsReady();
		return pWWStudioDetailsPage;
	}
}
