package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import uiTest.selenium.functions.LoginTestFunctions;
import uiTest.selenium.functions.PaymentFunctions;
import uiTest.selenium.functions.RegisterUserFunctions;
import uiTest.selenium.functions.SearchParkingSpotFunctions;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import parkingManagement.model.*;

public class ReserveParkingEndToEndTest extends SeleniumTestBase{
	private static WebDriver driver;
	private String baseUrl;
	RegisterUserFunctions registerUserFunctions;
	LoginTestFunctions loginTestFunctions;
	SearchParkingSpotFunctions searchParkingSpotFunctions;
	PaymentFunctions paymentFunctions;
	 
	@Before
	public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = appProperties.getProperty("appUrl");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    searchParkingSpotFunctions = new SearchParkingSpotFunctions();
	    paymentFunctions = new PaymentFunctions();
	    
	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	    driver.quit();
	}

	@Test
	public void reserveParkingEndToEndTest() throws Exception {

		driver.get(baseUrl);
		User user = new User("seleniumuserone", "seleniumuserone", "seleniumuserone", "Test@123", "Test@123", "1001518112", "Student/Faculty",
				"1234567890","abcd@gmail.com", "603 causley ave", "Arlington", "Texas","76010","8112",
				"12345678", "Basic");
		registerUserFunctions.registerUser(driver);
		registerUserFunctions.registerUserSuccess(driver,user);
		
		
		LoginValidations();
		  
		loginTestFunctions.loginSuccessFunction(driver, "seleniumuserone", "Test@123");
		
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_search_link"))).click();
		
		String[] fromAndToTime = getFromAndToTime();

		searchParkingSpotFunctions.searchParkingSpot(driver, "Maverick", "Basic", fromAndToTime[0], fromAndToTime[1]);
		verifyParkingDataBeforeSelection();
		
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_radioButton1"))).click();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_reserveButton_btn"))).sendKeys(Keys.ENTER);
		verifyValuesBeforePayment(fromAndToTime);
		
		driver.findElement(By.id(prop.getProperty("ReserveParkingSpot_makePayment_btn"))).sendKeys(Keys.ENTER);
		paymentFunctions.makeSuccessPayment(driver, "seleniumuserone", "seleniumuserone", "Centennial world", "1234123412341234",
				"VISA", "July", "2020", "333");
		verifyReservationConfirmationDetails(fromAndToTime);
		
		gotoHome();
		logout();
	}
	
	private void LoginValidations() throws Exception{
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
		
	}

	private void logout() throws Exception{
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_Logout_link"))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}

	private void gotoHome() throws Exception {
		driver.findElement(By.xpath(prop.getProperty("ReservationConfirmed_Home_link"))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}

	private void verifyParkingDataBeforeSelection() throws Exception {
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_name1"))).getText());
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_type1"))).getText());
		assertEquals("1", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_floor1"))).getText());
		assertTrue(Integer.parseInt(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_count1"))).getText())>0);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_totalcost1"))).getText()) >= 17.25);
		Thread.sleep(2000);
	}
	
	private void verifyValuesBeforePayment(String[] fromAndToTime) throws Exception {
		verifyCommonValuesOfReservation(fromAndToTime);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("totalCost_txt"))).getAttribute("value")) >= 17.25);
		Thread.sleep(2000);
	}

	private void verifyReservationConfirmationDetails(String[] fromAndToTime) throws Exception {
		verifyCommonValuesOfReservation(fromAndToTime);
		Thread.sleep(2000);
	}

	private void verifyCommonValuesOfReservation(String[] fromAndToTime) {
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("inputParkingName_txt"))).getAttribute("value"));
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("inputParkingType_txt"))).getAttribute("value"));
		assertEquals("1", driver.findElement(By.id(prop.getProperty("inputFloor_txt"))).getAttribute("value"));
		assertEquals(fromAndToTime[0], driver.findElement(By.id(prop.getProperty("inputFromTime_txt"))).getAttribute("value"));
		assertEquals(fromAndToTime[1], driver.findElement(By.id(prop.getProperty("inputToTime_txt"))).getAttribute("value"));
		assertTrue((driver.findElement(By.id(prop.getProperty("options_txt"))).getAttribute("value")).contains("Cart"));
	}

	private String[] getFromAndToTime() {
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		String fromTime = (currentHours+1)+":00";
		String toTime = (currentHours+2)+":00";
		if((currentHours+1)<=9) 
			fromTime = "0"+(currentHours+1)+":00";
		if((currentHours+2)<=9)
			toTime = "0"+(currentHours+2)+":00";
		String[] fromAndToTime = {fromTime, toTime};
		return fromAndToTime;
	}

	private String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    return dateFormat.format(date);
	}
}
