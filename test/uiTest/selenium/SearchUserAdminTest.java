package uiTest.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import uiTest.selenium.functions.RegisterUserFunctions;
import uiTest.selenium.functions.LoginTestFunctions;
import uiTest.selenium.functions.SearchUserFunctions;

import static org.junit.Assert.assertEquals;

public class SearchUserAdminTest extends SeleniumTestBase{
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
	public void loginTest() throws Exception {
		  driver.get(baseUrl);
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
		  
		  loginTestFunctions.loginSuccessFunction(driver, "williamsmith", "william@123");
		  Thread.sleep(2000);
		  
		  driver.findElement(By.xpath(prop.getProperty("Admin_SearchUser_link"))).click();
		  Thread.sleep(2000);
		  
		  searchUserFunctions.searchUserSuccessFunction(driver,"smith");
		  Thread.sleep(2000);
		  
		  searchUserFunctions.changeUserRoleSuccessFunction(driver);
		  Thread.sleep(2000);
		  
		  assertEquals("Manager",driver.findElement(By.id(prop.getProperty("SearchSpecificUser_role_txt"))).getText());
		  
		  driver.findElement(By.xpath(prop.getProperty("SearchSpecificUser_Logout_link"))).click();
		  
	}
}
