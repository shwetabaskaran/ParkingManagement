package uiTest.selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import uiTest.selenium.functions.SeleniumFunctionsBase;

public class SeleniumTestBase extends SeleniumFunctionsBase{
	
	public SeleniumTestBase () {
		appProperties = new Properties();
		prop = new Properties();
		try {
			appProperties.load(new FileInputStream("./properties/configuration.properties")); 
		    prop.load(new FileInputStream(appProperties.getProperty("sharedUiMap")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperty(appProperties.getProperty("webBrowser"), appProperties.getProperty("webDriverLocation"));
	}
}
