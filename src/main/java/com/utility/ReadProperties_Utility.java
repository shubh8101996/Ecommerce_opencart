//package com.utility;
//
//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class ReadProperties_Utility {
//
//	private static Properties properties;
//
//	static {
//		properties = new Properties();
//		try {
//			FileInputStream file = new FileInputStream(
//					System.getProperty("user.dir") + "\\test_data\\properties_file\\env_config.properties");
//			properties.load(file);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static String getProperty(String key) {
//		return properties.getProperty(key);
//	}
//
//}