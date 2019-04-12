package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import parkingManagement.model.User;
import uiTest.selenium.functions.RegisterUserFunctions;
import uiTest.selenium.functions.LoginTestFunctions;
import uiTest.selenium.functions.SearchUserFunctions;

import static org.junit.Assert.assertEquals;

public class SearchUserManagerTest extends SeleniumTestBase{
	private WebDriver driver;
	private String baseUrl;
	RegisterUserFunctions registerUserFunctions;
	LoginTestFunctions loginTestFunctions;
	SearchUserFunctions searchUserFunctions;
	  
	@Before
	public void setUp() throws Exception {
	    driver = new FirefoxDriver();
		baseUrl = appProperties.getProperty("appUrl");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		registerUserFunctions = new RegisterUserFunctions();
		loginTestFunctions = new LoginTestFunctions();
		searchUserFunctions = new SearchUserFunctions();
	  }

	@Test
	public void searchUserManagerTest() throws Exception {
		driver.get(baseUrl);
		
		//Register new manager
		User user = new User("Mike", "Shaw", "mikeshaw", "mike@123", "mike@123", "1001672278", "Manager",
				"8763546323","mike@uta.edu", "900 Greek Row Dr", "Arlington", "Texas","76014","8765",
				"98767678", "Premium");
		registerUserFunctions.registerUser(driver);
		registerUserFunctions.registerUserSuccess(driver,user);
		
		//Perform validations for login function
		LoginValidations();
		
		//Login with correct details
		loginTestFunctions.loginSuccessFunction(driver, "mikeshaw", "mike@123");
		  Thread.sleep(2000);
		
		//Click on search user  
		driver.findElement(By.xpath(prop.getProperty("Manager_SearchUser_link"))).click();
		  Thread.sleep(2000);
		  
		//Search for a user  
		searchUserFunctions.searchUserSuccessFunction(driver,"smith");
		  Thread.sleep(2000);
		
		//Verify that details are displayed for the right user 
		  verifyUser();
		  
		//Edit violations for the user  
		searchUserFunctions.editViolationsSuccessFunction(driver);
		Thread.sleep(2000);
		
		//Verify that the violations have been edited  successfully
		verifyValuesAfterEditViolation();
		
		//Navigate to home
		goToHome();
		Thread.sleep(1000);
		
		//Logout   
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
	
	private void verifyUser() throws Exception{
		assertEquals("johnsmith",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_userName_txt"))).getText());
	}
	
	private void verifyValuesAfterEditViolation() throws Exception{
		assertEquals("2",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_noShows_txt"))).getText());
		assertEquals("1",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_overdue_txt"))).getText());
	}
	
	private void goToHome() throws Exception {
		
		driver.findElement(By.xpath(prop.getProperty("SearchSpecificUser_Home_link"))).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}
	
	private void logout() throws Exception{
		
		driver.findElement(By.xpath(prop.getProperty("Manager_Logout_link"))).click();
		Thread.sleep(2000);
	}
	
	
}
