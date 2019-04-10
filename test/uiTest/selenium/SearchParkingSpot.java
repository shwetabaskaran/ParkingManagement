package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.LoginTestFunctions;
import uiTest.selenium.functions.SearchParkingSpotFunctions;

import java.util.Date;
import java.util.Properties;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SearchParkingSpot extends SearchParkingSpotFunctions{
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
	public void searchParkingSpotTest() throws Exception {
		driver.get(baseUrl);
		loginTestFunctions.loginSuccessFunction(driver, prop, "Kennet", "Test@123");
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_search_link"))).click();
		
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		
		String fromTime = (currentHours+1)+":00";
		String toTime = (currentHours+2)+":00";
		
		searchParkingSpot(driver, prop, "Maverick", "Basic", fromTime, toTime);
	}
	
	private String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    return dateFormat.format(date);
	}

}
