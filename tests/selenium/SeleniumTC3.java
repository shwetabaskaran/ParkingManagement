package selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import parkingManagement.model.User;
import selenium.functions.AdminFunctions;
import selenium.functions.LoginTestFunctions;
import selenium.functions.RegisterUserFunctions;

import static org.junit.Assert.assertEquals;

public class SeleniumTC3 extends SeleniumTestBase{
	  private WebDriver driver;
	  private String baseUrl;
	  RegisterUserFunctions registerUserFunctions;
	  LoginTestFunctions loginTestFunctions;
	  AdminFunctions adminFunctions;
	  
	  @Before
	  public void setUp() throws Exception {
		    driver = new FirefoxDriver();
		    baseUrl = appProperties.getProperty("appUrl");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    registerUserFunctions = new RegisterUserFunctions();
		    loginTestFunctions = new LoginTestFunctions();
		    adminFunctions = new AdminFunctions();
	  }

	  @After
		public void tearDown() throws Exception {
			driver.close();
		    driver.quit();
		}
	  
	@Test
	public void searchUserAdminTest() throws Exception {
		  driver.get(baseUrl);
		  
		  	//Register new admin
		  	User user = new User("William", "Smith", "williamsmith", "william@123", "william@123", "1000546372", "Admin",
					"9876354678","william@uta.edu", "80 Green Meadow", "Arlington", "Texas","76013","8755",
					"87675655", "Basic");
			registerUserFunctions.registerUserError(driver);
			registerUserFunctions.registerUserSuccess(driver,user);
			
			//Perform validations for login function
			LoginValidations();
			
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
	
	private void LoginValidations() throws Exception{
		String error = "";
		  
		error = loginTestFunctions.loginErrorFunction(driver, "", "");
		assertEquals("Please enter the Username or Password", error);
		if(testDelay.equals("delay")) Thread.sleep(2000);
		  
		error = loginTestFunctions.loginErrorFunction(driver, "Kennet", "");
		assertEquals("Please enter the Username or Password", error);
		if(testDelay.equals("delay")) Thread.sleep(2000);
		  
		error = loginTestFunctions.loginErrorFunction(driver, "", "Tsp!3bc127");
		assertEquals("Please enter the Username or Password", error);
		if(testDelay.equals("delay")) Thread.sleep(2000);
		  
		error = loginTestFunctions.loginErrorFunction(driver, "Kennet", "Tsp!3bc127");
		assertEquals("Incorrect Username or Password", error);
		if(testDelay.equals("delay")) Thread.sleep(2000);
		  
		error = loginTestFunctions.loginErrorFunction(driver, "Kennet", "wrongPassword");
		assertEquals("Incorrect Username or Password", error);
		if(testDelay.equals("delay")) Thread.sleep(2000);
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
