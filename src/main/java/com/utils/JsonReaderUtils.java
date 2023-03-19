package com.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

public class JsonReaderUtils 
{
	public static Object[][] getJsonData(String jsonFileName,String nodeName,int recordCount) throws FileNotFoundException, IOException, ParseException {
		Object[][] objData=null;
		String filePath="./JsonData/"+jsonFileName;
		//Create an object of jsonparser
		JSONParser parser=new JSONParser();
		//Pass the location where the file is located using FileReader
		//Parse the file and downcast it to json object
		JSONObject jsonObject=(JSONObject) parser.parse(new FileReader(filePath));
		//Since we're handling JsonArray, get json objects and cast it to json array
		JSONArray jsonArray=(JSONArray) jsonObject.get(nodeName);
		//Set the row count for the multi dimensional object array
		objData=new Object[recordCount][1];
		//Create a hashmap to hold the data temporarily
		HashMap<String, String> map=null;
		//Iterate over the json array to get the each json objects
			for(int i=0;i<jsonArray.size();i++) {
				//Getting the jsonobjects from the json array one by one based on the index
				JSONObject jsonObj=(JSONObject) jsonArray.get(i);
				//Get the keysets and stored it inside set variable since we can use it to iterate over
				@SuppressWarnings("unchecked")
				Set<String> jsonKeys=jsonObj.keySet();
				Iterator<String> jsonIterate=jsonKeys.iterator();
				map=new HashMap<>();
				while(jsonIterate.hasNext()) {
					//the while loop will executes till the last json objects
					String key=(String) jsonIterate.next();
					String value=(String) jsonObj.get(key);
					map.put(key, value);
				}
				objData[i][0]=map;
		}
			return objData;
	}
	
	public static <T> By  getByLocatorMethod(T className,String methodName, Object ...varargs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		var methods=className.getClass().getDeclaredMethods();
		By methodType = null;
		for (Method method : methods) {
			if(method.getName().equals(methodName)) {
				methodType= (By) method.invoke(className);
			}		}
		return methodType;
	}
}
