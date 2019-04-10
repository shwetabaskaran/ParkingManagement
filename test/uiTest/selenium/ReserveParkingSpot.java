package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.LoginTestFunctions;
import uiTest.selenium.functions.ReserveParkingSpotFunctions;

import java.util.Properties;

import java.io.FileInputStream;

public class ReserveParkingSpot extends ReserveParkingSpotFunctions{
	  private WebDriver driver;
	  private String baseUrl;
	  Properties prop;
	  Properties appProperties;
	  LoginTestFunctions loginTestFunctions;
	  
	  @Before
	  public void setUp() throws Exception {
		appProperties = new Properties();
		appProperties.load(new FileInputStream("./properties/configuration.properties"));
		System.setProperty(appProperties.getProperty("webBrowser"), appProperties.getProperty("webDriverLocation"));
	    driver = new FirefoxDriver();
	    baseUrl = appProperties.getProperty("appUrl");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream(appProperties.getProperty("sharedUiMap")));
	    loginTestFunctions = new LoginTestFunctions();
	  }

	@Test
	public void reserveParkingSpotTest() throws Exception {
		
		driver.get(baseUrl);
		loginTestFunctions.loginSuccessFunction(driver, prop, "Kennet", "Test@123");
	}
}
