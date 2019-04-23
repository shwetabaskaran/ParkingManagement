package selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import parkingManagement.model.*;
import selenium.SeleniumTestBase;
import selenium.functions.LoginTestFunctions;
import selenium.functions.RegisterUserFunctions;
import selenium.functions.MyProfileFunctions;
@RunWith(JUnitParamsRunner.class)
public class MyProfileTest extends SeleniumTestBase {
	RegisterUserFunctions registerUserFunctions;
	LoginTestFunctions loginTestFunctions;
	MyProfileFunctions myprofilefunctions;
	
	@Before
	public void setUp() throws Exception {
	    registerUserFunctions = new RegisterUserFunctions();
	    loginTestFunctions = new LoginTestFunctions();
	    myprofilefunctions = new MyProfileFunctions();
	    setDriver();
	    
	    driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}
	@Test
	@FileParameters("./seleniumTestData/MyProfileTestData.csv")
	public void MyProfileandUpdateTest(int testno, String username, String password, String confirmPassword, String firstname,
			String lastname, String email, String phone, String utaid, String plate_number, String permit_id,
			String permit_type, String street_add, String city, String state, String role, String zip,String ZipErr,String UpdatedZip) throws Exception {
		User user = new User(firstname, lastname, username, password, confirmPassword, utaid, role,
				phone, email, street_add, city, state, zip, plate_number,
				permit_id, permit_type);
		registerUserFunctions.registerUserSuccess(user);
		assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		driver.findElement(By.id("login_link")).click();
		loginTestFunctions.loginSuccessFunction(username, password);
		myprofilefunctions.MyProfileFunction(ZipErr,UpdatedZip);
		}
}