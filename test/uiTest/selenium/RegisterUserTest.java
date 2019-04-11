package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.RegisterUserFunctions;

public class RegisterUserTest extends SeleniumTestBase{
	  private WebDriver driver;
	  private String baseUrl;
	  RegisterUserFunctions registerUserFunctions;
	  
	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = appProperties.getProperty("appUrl");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    registerUserFunctions = new RegisterUserFunctions();
	  }

	@Test
	public void test() throws Exception {
		  driver.get(baseUrl);
		  registerUserFunctions.registerUser(driver,"kennet","Student/Faculty");
	}
}
