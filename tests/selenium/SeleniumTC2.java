package selenium;

import org.junit.*;
import org.openqa.selenium.*;

import parkingManagement.model.User;
import selenium.functions.LoginTestFunctions;
import selenium.functions.ManagerFunctions;
import selenium.functions.RegisterUserFunctions;

import static org.junit.Assert.assertEquals;

public class SeleniumTC2 extends SeleniumTestBase{

	RegisterUserFunctions registerUserFunctions;
	LoginTestFunctions loginTestFunctions;
	ManagerFunctions managerFunctions;
	  
	@Before
	public void setUp() throws Exception {
		registerUserFunctions = new RegisterUserFunctions();
		loginTestFunctions = new LoginTestFunctions();
		managerFunctions = new ManagerFunctions();
		setDriver();
	    
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	  }
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	    driver.quit();
	}

	@Test
	public void searchUserManagerTest() throws Exception {
		
		//Register new manager
		User user = new User("Mike", "Shaw", "mikeshaw", "mike@123", "mike@123", "1001672278", "Manager",
				"8763546323","mike@uta.edu", "900 Greek Row Dr", "Arlington", "Texas","76014","8765",
				"98767678", "Basic");
		registerUserFunctions.registerUserSuccess(user);

		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		
		//Login with correct details
		loginTestFunctions.loginSuccessFunction("mikeshaw", "mike@123");
		if(testDelay.equals("delay"))  Thread.sleep(2000);
		
		//Click on search user  
		driver.findElement(By.xpath(prop.getProperty("Manager_SearchUser_link"))).click();
		if(testDelay.equals("delay"))  Thread.sleep(2000);
		  
		//Search for a user  
		managerFunctions.searchUserSuccessFunction("smith");
		if(testDelay.equals("delay"))  Thread.sleep(2000);
		
		//Verify that details are displayed for the right user 
		  verifyUser();
		  
		//Edit violations for the user  
		managerFunctions.editViolationsSuccessFunction();
		if(testDelay.equals("delay")) Thread.sleep(2000);
		
		//Verify that the violations have been edited  successfully
		verifyValuesAfterEditViolation();
		
		//Navigate to home
		goToHome();
		
		//Logout   
		logout();
		  
	}
	
	private void verifyUser() throws Exception{
		assertEquals("johnsmith",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_userName_txt"))).getText());
	}
	
	private void verifyValuesAfterEditViolation() throws Exception{
		assertEquals("2",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_noShows_txt"))).getText());
		assertEquals("1",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_overdue_txt"))).getText());
	}
	
	private void goToHome() throws Exception {
		
		driver.findElement(By.xpath(prop.getProperty("SearchSpecificUser_Home_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	private void logout() throws Exception{
		
		driver.findElement(By.xpath(prop.getProperty("Manager_Logout_link"))).click();
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	
}
