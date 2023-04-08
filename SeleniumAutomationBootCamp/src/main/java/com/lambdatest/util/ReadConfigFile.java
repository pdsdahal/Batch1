package com.lambdatest.util;

import java.io.FileReader;
import java.util.Properties;

public class ReadConfigFile {
	
	public static Properties getProperty(String properyFileName) {
		Properties properties = new Properties();
		String basePath = System.getProperty("user.dir");
		String filePath = basePath + "/src/test/resources/" + properyFileName + ".properties";
		try {
			FileReader reader=new FileReader(filePath);  
			properties.load(reader);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return properties;		   
	}
}

/*

Driver Layer

page Layer - Home Page							Login Page						Registration Page 
			Variables webElements, methods		VW/M							VW/M


Test Layer - HomePageTest						LoginPageTest					RegistrationPageTest
			- TestNG assertion					TestNG assertion				TestNG assertion
			
			
Utility Layer - common code

*/