package com.Util;

import java.util.ArrayList;

public class TestDataObjects {

public static class WW_StudioData{
		
	/********This class is to maintain data objects.Better to save data in object than multiple variable to make usage easier***********/

		public String studioName;
		public String location;
		
		public ArrayList<WW_StudioData> convertObjectToMyClass(ArrayList<Object> a){
			return (ArrayList) a;
		}
		
		@Override
		public String toString() {
			String output="studioName=" + studioName + ", location=" + location ; 	
			//System.out.println(output);
			return output; 
		}
	}
}
