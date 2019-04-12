package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		String error = "";
		
		//Register new manager
		registerUserFunctions.registerUser(driver, "mikeshaww", "Manager");
		
		//Perform validations for login function
		LoginValidations();
		
		//Login with correct details
		loginTestFunctions.loginSuccessFunction(driver, "mikeshaww", "Test@123");
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
