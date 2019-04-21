package selenium;

import org.junit.*;
import org.openqa.selenium.*;

import parkingManagement.model.User;
import selenium.functions.AdminFunctions;
import selenium.functions.LoginTestFunctions;
import selenium.functions.RegisterUserFunctions;

import static org.junit.Assert.assertEquals;

public class SeleniumTC3 extends SeleniumTestBase{
	  RegisterUserFunctions registerUserFunctions;
	  LoginTestFunctions loginTestFunctions;
	  AdminFunctions adminFunctions;
	  
	  @Before
	  public void setUp() throws Exception {
		    registerUserFunctions = new RegisterUserFunctions();
		    loginTestFunctions = new LoginTestFunctions();
		    adminFunctions = new AdminFunctions();
		    setDriver();
		    
		    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	  }

	  @After
		public void tearDown() throws Exception {
			driver.close();
		    driver.quit();
		}
	  
	@Test
	public void searchUserAdminTest() throws Exception {
		  
		  	//Register new admin
		  	User user = new User("William", "Smith", "williamsmith", "william@123", "william@123", "1000546372", "Admin",
					"9876354678","william@uta.edu", "80 Green Meadow", "Arlington", "Texas","76013","8755",
					"87675655", "Basic");
			registerUserFunctions.registerUserSuccess(driver,user);

			assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
			driver.findElement(By.id("login_link")).click();
			
			//Login with correct details
			loginTestFunctions.loginSuccessFunction(driver, "williamsmith", "william@123");
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
			//Click on search user  
			driver.findElement(By.xpath(prop.getProperty("Admin_SearchUser_link"))).click();
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			  
			//Search for a user  
			adminFunctions.searchUserSuccessFunction(driver,"smith");
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
			//Verify that details are displayed for the right user 
			  verifyUser();
			  
			//Change Role of the user  
			adminFunctions.changeUserRoleSuccessFunction(driver);
			if(testDelay.equals("delay"))  Thread.sleep(2000);
			
			//Verify that the Role have been changed successfully
			verifyRoleAfterChange();
			
			//Navigate to home
			goToHome();
			
			//Logout   
			logout();
		 	    
	}
	
	private void verifyUser() throws Exception{
		assertEquals("johnsmith",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_userName_txt"))).getText());
	}
	
	private void verifyRoleAfterChange() throws Exception{
		assertEquals("Manager",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_role_txt"))).getText());
	}
	
	private void goToHome() throws Exception {
		
		driver.findElement(By.xpath(prop.getProperty("SearchSpecificUser_Home_link"))).sendKeys(Keys.ENTER);
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
	
	private void logout() throws Exception{
		
		driver.findElement(By.xpath(prop.getProperty("Admin_Logout_link"))).click();
		if(testDelay.equals("delay")) Thread.sleep(2000);
	}
}
