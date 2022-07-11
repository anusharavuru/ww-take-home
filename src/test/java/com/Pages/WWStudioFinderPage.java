package com.Pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.Util.WWElements;

public class WWStudioFinderPage extends WWElements{

	
	public WWStudioFinderPage(WebDriver driver,SoftAssert softasert) {
		super(driver,softasert);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.softasert=softasert;
	}

WebElement PageTitle(){
	return  getObject("xpath","//head/title[contains(text(),'Find WW')]","NotVisible");
}
WebElement CancelPopUp(){
	return getObject("xpath",".//a[contains(@id,'bx-close-inside')","NotVisible");
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
	Log.info("WW Studio Finder Page is ready");
}

public void mVerifyPageTitle(){
	verify.VerifyEqual("Find WW Studios & Meetings Near You | WW USA", driver.getTitle().toString().trim().replace("&nbsp;", " "), "Find WW Studios : VERIFY PAGE  TITLE");
	}
 
public void mAssertPageTitleCheck(){
	System.out.println("Below assert is requested: ");
	verify.AssertEqual("Find WW Studios & Meetings Near You | WW USA", driver.getTitle().toString().trim().replace("&nbsp;", " "), "Find WW Studios : VERIFY PAGE  TITLE");
	}

public void mEnterStudioCodeandSubmit(String zipcode){
	Web_Click(Studiosearch());
	WEB_SetText(EnterLocationTextBox(), zipcode);
	Web_Click(LocationSubmit());
	}
}
