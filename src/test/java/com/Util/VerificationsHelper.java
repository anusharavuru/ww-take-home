package com.Util;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class VerificationsHelper {

	/********This class is to maintain methods used for validations. This avoids rewriting same code multiple times ***********/

	SoftAssert softasert=new SoftAssert();
	
	public VerificationsHelper(SoftAssert softasert) {
	this.softasert=softasert;
	}
	
	Logger logger = Logger.getLogger("VerificationsHelper");
	
	public void VerifyEqual(String expected, String actual, String message) {
			if (actual.toLowerCase().replaceAll(" ", "").replaceAll("\\h+", "").trim().equals(expected.toLowerCase().replaceAll(" ", "").replaceAll("\\h+", "").trim())) {
				logger.info( "oVerifyEqual **PASSED** for : "+ message + " Result Expected:"+ expected +" vs Actual:"+ actual);
			} else {
				logger.error( "oVerifyEqual !!!FAILED!!! for : "+ message + " Result Expected:"+ expected +" vs Actual:"+ actual );
				softasert.fail();
			}
		}
	
	public void AssertEqual(String expected, String actual, String message) {
		if (actual.toLowerCase().replaceAll(" ", "").replaceAll("\\h+", "").trim().equals(expected.toLowerCase().replaceAll(" ", "").replaceAll("\\h+", "").trim())) {
			logger.info( "oVerifyEqual **PASSED** for : "+ message + " Result Expected:"+ expected +" vs Actual:"+ actual);
		} else {
			logger.error( "oVerifyEqual !!!FAILED!!! for : "+ message + " Result Expected:"+ expected +" vs Actual:"+ actual );
			Assert.fail("oVerifyEqual !!!FAILED!!! for : "+ message + " Result Expected:"+ expected +" vs Actual:"+ actual );
		}
	}
	
	public boolean TextEquals(String text1,String text2){
		boolean check=true;
		if(text1.trim().length()==text2.trim().length()){
			text1=text1.toString().replace(" ", "");
			text2=text2.toString().replaceAll("\\h+", "");

		for (int i = 0; i < text1.length(); i++) {
		     if (text1.charAt(i) != text2.charAt(i)) {
		         check= false;
		     }
		}
		}
		return check;
	}
}
