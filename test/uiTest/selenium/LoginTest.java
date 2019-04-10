package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.LoginTestFunctions;

import static org.junit.Assert.assertEquals;

public class LoginTest extends SeleniumTestBase{
	  private WebDriver driver;
	  private String baseUrl;
	  LoginTestFunctions loginTestFunctions;
	  
	  @Before
	  public void setUp() throws Exception {
		    driver = new FirefoxDriver();
		    baseUrl = appProperties.getProperty("appUrl");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    loginTestFunctions = new LoginTestFunctions();
	  }

	@Test
	public void loginTest() throws Exception {
		  driver.get(baseUrl);
		  String error = "";
		  
		  error = loginTestFunctions.loginErrorFunction(driver, "", "");
		  assertEquals("Please enter the Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginTestFunctions.loginErrorFunction(driver, "Kennet", "");
		  assertEquals("Please enter the Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginTestFunctions.loginErrorFunction(driver, "", "Tsp!3bc127");
		  assertEquals("Please enter the Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginTestFunctions.loginErrorFunction(driver, "Kennet", "Tsp!3bc127");
		  assertEquals("Incorrect Username or Password", error);
		  Thread.sleep(2000);
		  
		  error = loginTestFunctions.loginErrorFunction(driver, "Kennet", "wrongPassword");
		  assertEquals("Incorrect Username or Password", error);
		  Thread.sleep(2000);
		  
		  loginTestFunctions.loginSuccessFunction(driver, "Kennet", "Test@123");
	}
}
