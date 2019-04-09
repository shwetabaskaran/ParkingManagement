package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.RegisterUserFunctions;

import java.util.Properties;
import java.io.FileInputStream;

public class RegisterUserTest extends RegisterUserFunctions{
	  private WebDriver driver;
	  private String baseUrl;
	  Properties prop;
	  Properties appProperties;
	  
	  @Before
	  public void setUp() throws Exception {
		appProperties = new Properties();
		appProperties.load(new FileInputStream("test/uiTest/selenium/properties/configuration.properties"));
		System.setProperty(appProperties.getProperty("webBrowser"), appProperties.getProperty("webDriverLocation"));
	    driver = new FirefoxDriver();
	    baseUrl = appProperties.getProperty("appUrl");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream(appProperties.getProperty("sharedUiMap")));
	  }

	@Test
	public void test() throws Exception {
		  driver.get(baseUrl);
		  registerUser(driver,prop,"seleniumusertwo","Student/Faculty");
	}
}
