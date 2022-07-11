package com.Tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.PropertyConfigurator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Util.ConfigConstants;
import com.Util.PageObjectHelper;
import com.Util.ReadTestDataHelper;
import com.Util.TestConfig;
import com.Util.TestDataObjects.WW_StudioData;

public class WWStudioFinderTest extends TestConfig {
	
	@Parameters({"browser"})
	@BeforeClass
	public void setup(@Optional(ConfigConstants.TestBrowser) String browser) throws MalformedURLException, InterruptedException {
		System.out.println("browser: "+browser);
		driver=initialiseDriver(browser,driver);
		PropertyConfigurator.configure("Log4j.properties");
	}

	@Test(priority = 1)
	public void StudioFinderTest() throws InterruptedException, IOException {
		SoftAssert softasert=new SoftAssert();
		try {
			PageObjectHelper obj=new PageObjectHelper(driver, softasert);
			WW_StudioData studioData=new WW_StudioData();
			studioData.location=ReadTestDataHelper.getPropValue(ConfigConstants.WW_DataFile, ConfigConstants.ZipCode);
			
			obj.loadwebPage(ReadTestDataHelper.getPropValue(ConfigConstants.WW_DataFile, ConfigConstants.WW_URL));
			
			obj.pWWStudioFinderHomePage().mAssertPageTitleCheck();
			obj.pWWStudioFinderHomePage().mEnterStudioCodeandSubmit(studioData.location);
			obj.pWWStudioSearchPage().mSelectandPrintFistLocationDetails(studioData);
			
			obj.pWWStudioDetailsPage().mVerifyLocationName( studioData);
			obj.pWWStudioDetailsPage().mReadandPrintBusinessHours();
		} 
		catch(Exception E){
			E.printStackTrace();
		}
		
		finally {
			driver.quit();
			softasert.assertAll();
		}
	}
}
