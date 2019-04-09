package uiTest.selenium.functions;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.RegisterUserTest;

import java.util.Properties;
import java.io.FileInputStream;

public class RegisterUserFunctions extends RegisterUserTest {
	  private WebDriver driver;
	  private String baseUrl;
	  
	  @Before
	  public void setUp() throws Exception {
		  System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080/ParkingManagement/index.jsp";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	@Test
	public void test() throws Exception {
		  Properties prop = new Properties();
		  prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
		  driver.get(baseUrl);
		  registerUser(driver,prop,"seleniumusertwo","Student/Faculty");
		  
	}

}
