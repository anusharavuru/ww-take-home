package com.Util;

public class ConfigConstants {
	/********This class is to maintain constants used in the project***********/

	public static final String TestBrowser = "chrome";
	public static final String WW_URL = "WWURL";
	public static final String WW_DataFile ="Data.properties";
	public static final String ZipCode="zipcode";
	public static final String OS="OS";
	public static final String DOCUMENT_STATE_COMPLETE="complete";
	public static final String DOCUMENT_STATE_INTERACTIVE="interactive";
	public static final long TIMEOUT_PAGELOAD = 20;
	public static final String LOC_CHROMEEXE=mGetProjectPath()+"//src//test//resources//DriverExecutables//chromedriver.exe";
	public static final String LOC_CHROMEMAC=mGetProjectPath()+"//src//test//resources//DriverExecutables//chromedriver";
	public static final String PROJECT_FOLDER = "WW-Test"; 
	public static final long TIMEOUT_IMPLICITWAIT=5;
	public static final long TIMEOUT_SPINNER=1;
	public static final String LOC_IEEXE=mGetProjectPath()+"//src//test//resources//DriverExecutables//IEDriverServer.exe";
	public static final String LOC_FireFoxEXE=mGetProjectPath()+"//src//test//resources//DriverExecutables//geckodriver.exe";

	private static String mGetProjectPath(){
		String workspacePath = mGetWorkspacePath();
		String frameworkPath = workspacePath +"//"+PROJECT_FOLDER;
		return frameworkPath;
	}
	
	private static String mGetWorkspacePath(){
		String path = System.getProperty("user.dir");
		String fileSeperator = System.getProperty("file.separator");
		int lastInstanceIndex =  path.lastIndexOf(fileSeperator);
		String workspacePath = path.substring(0, lastInstanceIndex);
		System.out.println("workspace path:  "+workspacePath);
		return workspacePath;
	}
}
