package com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.Util.WWElements;
import com.Util.TestDataObjects.WW_StudioData;

public class WWFilterSearchPage extends WWElements{

	
	public WWFilterSearchPage(WebDriver driver,SoftAssert softasert) {
		super(driver,softasert);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.softasert=softasert;
	}

WebElement PageTitle(){
	return  getObject("xpath","//head/title[contains(text(),'Find WW')]","NotVisible");
}
WebElement FirstLocationTextLink(){
	return getObject("xpath","//div[@id='search-results']/a[1]//a[contains(@class,'linkUnderline')]");
}
WebElement FirstLocationDistance(){
	return getObject("xpath","//div[@id='search-results']/a[1]//span[contains(@class,'distance')]");
}
WebElement Studiosearch(){
	return getObject("xpath","//span[text()='In-Person']");
}
WebElement EnterLocationTextBox(){
	return getObject("id","location-search");
}
WebElement LocationSubmit(){
	return getObject("id","location-search-cta");
}


public void mIsReady()	{
	isPageLoaded();
	mVerifyPageTitle();
	Log.info("WW Studio Filter Search Page is ready");
}

public void mVerifyPageTitle(){
	waitForSpinner();
	String location=WEB_GetAttributeValue(EnterLocationTextBox(),"value");
	verify.VerifyEqual("Weight Loss Meeting Locations; "+location+" | WW USA", driver.getTitle().trim().replace("&nbsp", " "), " WW Studios Filter Search Page: VERIFY PAGE  TITLE");
	}
 
/********Below method is to Print the First location and select the location***********/
public void mSelectandPrintFistLocationDetails(WW_StudioData studioData){
	studioData.studioName=Web_GetText(FirstLocationTextLink());
	Log.info(Web_GetText(FirstLocationTextLink())+"           "+Web_GetText(FirstLocationDistance()));
	System.out.println("Message Requested to be Printed:");
	System.out.println(Web_GetText(FirstLocationTextLink())+"           "+Web_GetText(FirstLocationDistance()));
	Web_Click(FirstLocationTextLink());
	}
}

