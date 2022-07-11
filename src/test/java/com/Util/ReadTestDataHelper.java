package com.Util;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadTestDataHelper {

	/********This class is to maintain code which helps in reading the test data,here its to read Properties file ***********/

	public static Properties config = new Properties();

	public static void initconfigurations(String FileName) {
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//com//Util//"+FileName);
			config.load(fs);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPropValue(String FileName,String propName) {
		String propValue = null;
		if (config.size() == 0) {
			initconfigurations(FileName);
			propValue = config.getProperty(propName);
		} else {
			propValue = config.getProperty(propName);
		}
		return propValue;

	}
}
