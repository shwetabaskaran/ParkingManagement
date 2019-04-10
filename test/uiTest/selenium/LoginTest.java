package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.LoginTestFunctions;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;

public class LoginTest extends LoginTestFunctions{
	  private WebDriver driver;
	  private String baseUrl;
	  Properties prop;
	  Properties appProperties;
	  
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
	  }

	@Test
	public void loginTest() throws Exception {
		  driver.get(baseUrl);
		  String error = "";
		  
		  error = loginErrorFunction(driver, prop, "", "");
		  assertEquals("Please enter the Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginErrorFunction(driver, prop, "Kennet", "");
		  assertEquals("Please enter the Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginErrorFunction(driver, prop, "", "Tsp!3bc127");
		  assertEquals("Please enter the Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginErrorFunction(driver, prop, "Kennet", "Tsp!3bc127");
		  assertEquals("Incorrect Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginErrorFunction(driver, prop, "Kennet", "wrongPassword");
		  assertEquals("Incorrect Username or Password", error);
		  Thread.sleep(2000);
		  
		  loginSuccessFunction(driver, prop, "Kennet", "Test@123");
	}
}
