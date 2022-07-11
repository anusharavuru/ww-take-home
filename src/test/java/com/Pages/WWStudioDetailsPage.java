package com.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.Util.TestDataObjects.WW_StudioData;
import com.Util.WWElements;

public class WWStudioDetailsPage extends WWElements{

	
	public WWStudioDetailsPage(WebDriver driver,SoftAssert softasert) {
		super(driver,softasert);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.softasert=softasert;
	}

WebElement locationName(){
	return  getObject("xpath","//h1[contains(@class,'locationName')]");
}
WebElement locationAddressFirstLine(){
	return  getObject("xpath","//div[contains(@class,'address-')]/div[1]");
}
WebElement locationAddressSecondLine(){
	return  getObject("xpath","//div[contains(@class,'address-')]/div[2]");
}
WebElement BusinessHours(){
	return getObject("class","hours-IVyrp");
}
List<WebElement> businessDaysDisplayed(){
	return getObjects("xpath","//div[contains(@class,'hoursWrapper')]//div[contains(@class,'day-CZkDC')]");
}
WebElement DayNames(int i){
	return getObject("xpath","//div[contains(@class,'hoursWrapper')]//div[contains(@class,'day-CZkDC')]["+i+"]/div[1]");
}
WebElement DayHours(int i){
	return getObject("xpath","//div[contains(@class,'hoursWrapper')]//div[contains(@class,'day-CZkDC')]["+i+"]/div[2]");
}
WebElement HoursWrapper(){
	return getObject("xpath","//div[contains(@class,'hoursWrapper')]","NotVisible");
}
WebElement CancelPopUp(){
	return getObject("xpath","(.//button[text()='No Thanks'])[1]",2);
}

public void mIsReady()	{
	isPageLoaded();
	mVerifyPageTitle();
	Log.info("WW Studio details Page is ready");
}

public void mVerifyPageTitle(){
	verify.VerifyEqual("Meeting Location: "+Web_GetText(locationAddressFirstLine())+", "+Web_GetText(locationAddressSecondLine())+" | WW USA", driver.getTitle().trim().replace("&nbsp", " "), "Find WW Studios : VERIFY PAGE  TITLE");
	}
 
/********Below method is to verify if the location details are displayed for the location selected in the previous page***********/

public void mVerifyLocationName(WW_StudioData studioData){
	System.out.println("Below Verification is Requested: ");
	verify.VerifyEqual(studioData.studioName,Web_GetText(locationName()), "WW Studios Details : VERIFY selected Studio Name");
	}

/********Below method is to print business hour details***********/

public void mReadandPrintBusinessHours(){
		Web_Click(BusinessHours());
	int daysSize=businessDaysDisplayed().size();
	System.out.println("Below details print is requested: ");
	for (int i=1;i<=daysSize;i++){
		System.out.println(Web_GetText(DayNames(i))+" "+Web_GetText(DayHours(i)));
	}
	}
}

