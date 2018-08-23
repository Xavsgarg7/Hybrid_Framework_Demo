package com.hybridFramework.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

public class ReadProperties {
	
	public static String separator = System.getProperty("file.separator");
	
	public static Map<String, String> getPropertiesData(String File) {
		Properties prop = new Properties();
		Map<String,String> propMap = new HashMap<String,String>();
		FileInputStream fis = null;
		try {
			System.out.println("Loading "+File);
			String filePATH = System.getProperty("user.dir") +separator+"src"+separator+"main"+separator+"java"+separator+"com"+separator+"hybridFramework"+separator+"properties"+separator+File;
			
			fis = new FileInputStream(filePATH);
			prop.load(fis);
			for(String key: prop.stringPropertyNames()){
				propMap.put(key, prop.getProperty(key));			
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}			
		return Collections.unmodifiableMap(propMap);	
	}
}
