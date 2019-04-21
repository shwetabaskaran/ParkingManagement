package selenium;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import parkingManagement.model.*;
import selenium.functions.LoginTestFunctions;
import selenium.functions.PaymentFunctions;
import selenium.functions.RegisterUserFunctions;
import selenium.functions.SearchParkingSpotFunctions;

public class ReserveParkingSpotWithoutOptions extends SeleniumTestBase{
	RegisterUserFunctions registerUserFunctions;
	LoginTestFunctions loginTestFunctions;
	SearchParkingSpotFunctions searchParkingSpotFunctions;
	PaymentFunctions paymentFunctions;
	 
	@Before
	public void setUp() throws Exception {
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    searchParkingSpotFunctions = new SearchParkingSpotFunctions();
	    paymentFunctions = new PaymentFunctions();
	    
	    setDriver();
	    
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void reserveParkingEndToEndTest() throws Exception {

		User user = new User("Brocoline", "John", "brocolineJohn", "Test@123", "Test@123", "1001518112", "Student/Faculty",
				"1234567890", "Brocoline@gmail.com", "603 causley ave", "Arlington", "Texas", "76010", "8112",
				"12345678", "Basic");
		registerUserFunctions.registerUserSuccess(user);
		
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		
		loginTestFunctions.loginSuccessFunction("brocoline", "Test@123");
		
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_search_link"))).click();
		
		String[] fromAndToTime = getFromAndToTime();

		searchParkingSpotFunctions.searchParkingSpotWithoutOptions("Maverick", "Basic", fromAndToTime[0], fromAndToTime[1]);
		verifyParkingDataBeforeSelection();
		
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_radioButton1"))).click();
		driver.findElement(By.id(prop.getProperty("SearchParkingSpot_reserveButton_btn"))).sendKeys(Keys.ENTER);
		verifyValuesBeforePayment(fromAndToTime);
		
		driver.findElement(By.id(prop.getProperty("ReserveParkingSpot_confirm_btn"))).sendKeys(Keys.ENTER);
		verifyReservationConfirmationDetails(fromAndToTime);
		
		gotoHome();
		logout();
	}

	private void logout() throws Exception{
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_Logout_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void gotoHome() throws Exception {
		driver.findElement(By.xpath(prop.getProperty("ReservationConfirmed_Home_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void verifyParkingDataBeforeSelection() throws Exception {
		Thread.sleep(1000);
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_name1"))).getText());
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_type1"))).getText());
		assertEquals("1", driver.findElement(By.id(prop.getProperty("SearchParkingSpot_floor1"))).getText());
		assertTrue(Integer.parseInt(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_count1"))).getText())>0);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("SearchParkingSpot_totalcost1"))).getText()) == 0.00);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	private void verifyValuesBeforePayment(String[] fromAndToTime) throws Exception {
		verifyCommonValuesOfReservation(fromAndToTime);
		assertTrue(Double.parseDouble(driver.findElement(By.id(prop.getProperty("totalCost_txt"))).getAttribute("value")) == 0.00);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void verifyReservationConfirmationDetails(String[] fromAndToTime) throws Exception {
		verifyCommonValuesOfReservation(fromAndToTime);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}

	private void verifyCommonValuesOfReservation(String[] fromAndToTime) {
		assertEquals("Maverick", driver.findElement(By.id(prop.getProperty("inputParkingName_txt"))).getAttribute("value"));
		assertEquals("Basic", driver.findElement(By.id(prop.getProperty("inputParkingType_txt"))).getAttribute("value"));
		assertEquals("1", driver.findElement(By.id(prop.getProperty("inputFloor_txt"))).getAttribute("value"));
		assertEquals(fromAndToTime[0], driver.findElement(By.id(prop.getProperty("inputFromTime_txt"))).getAttribute("value"));
		assertEquals(fromAndToTime[1], driver.findElement(By.id(prop.getProperty("inputToTime_txt"))).getAttribute("value"));
		assertTrue((driver.findElement(By.id(prop.getProperty("options_txt"))).getAttribute("value")).equals(""));
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
