package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.LoginTestFunctions;
import uiTest.selenium.functions.PaymentFunctions;
import uiTest.selenium.functions.SearchParkingSpotFunctions;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReserveParkingEndToEndTest extends SeleniumTestBase{
	  private WebDriver driver;
	  private String baseUrl;
	  LoginTestFunctions loginTestFunctions;
	  SearchParkingSpotFunctions searchParkingSpotFunctions;
	  PaymentFunctions paymentFunctions;
	  
	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = appProperties.getProperty("appUrl");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    loginTestFunctions = new LoginTestFunctions();
	    searchParkingSpotFunctions = new SearchParkingSpotFunctions();
	    paymentFunctions = new PaymentFunctions();
	  }

	@Test
	public void reserveParkingSpotTest() throws Exception {
		
		driver.get(baseUrl);
		loginTestFunctions.loginSuccessFunction(driver, "Kennet", "Test@123");
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_search_link"))).click();
		
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		
		String fromTime = (currentHours+1)+":00";
		String toTime = (currentHours+2)+":00";
		
		searchParkingSpotFunctions.searchParkingSpot(driver, "Maverick", "Basic", fromTime, toTime);
		driver.findElement(By.xpath(prop.getProperty("SearchParkingSpot_radioButton1"))).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(prop.getProperty("SearchParkingSpot_reserveButton_btn"))).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath(prop.getProperty("ReserveParkingSpot_makePayment_btn"))).sendKeys(Keys.ENTER);
		
		paymentFunctions.makeSuccessPayment();
		
	}
	
	private String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    return dateFormat.format(date);
	}
}
