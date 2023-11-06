package com.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties_Utility {

	public Properties properties;
	String propertiesFilePath = System.getProperty("user.dir") + "\\test_data\\properties_file\\env_config.properties";

	public ReadProperties_Utility() {

		properties = new Properties();
		try {
			FileInputStream file = new FileInputStream(propertiesFilePath);
			properties.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBrowserValue() {

		String browserVal = properties.getProperty("browser");
		return browserVal;
	}

	public String getUrl() {

		String url = properties.getProperty("url");
		return url;

	}

	public String getUsername() {
		String username = properties.getProperty("username");
		return username;

	}

	public String getPassword() {
		String password = properties.getProperty("password");
		return password;

	}

}