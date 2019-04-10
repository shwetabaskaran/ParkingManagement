package uiTest.selenium.functions;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterUserFunctions extends SeleniumFunctionsBase{
	
	public void registerUser(WebDriver driver,Properties prop,String username,String user_role)
	{
		  String firstnameErr = "";
		  String lastnameErr = "";
		  String usernameErr = "";
		  String passwordErr = "";
		  String cpasswordErr = "";
		  String utaidErr = "";
		  String emailErr = "";
		  String phoneErr = "";
		  String saddressErr = "";
		  String cityErr = "";
		  String stateErr = "";
		  String zipErr = "";
		  String platenumErr = "";
		  String permitidErr = "";
		  driver.findElement(By.xpath(prop.getProperty("Index_Register"))).click();
		  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  firstnameErr = driver.findElement(By.id(prop.getProperty("Register_FirstnameError_txt"))).getAttribute("value").toString();
		   lastnameErr = driver.findElement(By.id(prop.getProperty("Register_LastnameError_txt"))).getAttribute("value").toString();
		   usernameErr = driver.findElement(By.id(prop.getProperty("Register_UsernameError_txt"))).getAttribute("value").toString();
		   passwordErr = driver.findElement(By.id(prop.getProperty("Register_PasswordError_txt"))).getAttribute("value").toString();
		   cpasswordErr = driver.findElement(By.id(prop.getProperty("Register_ConfirmPasswordError_txt"))).getAttribute("value").toString();
		   utaidErr = driver.findElement(By.id(prop.getProperty("Register_UtaIdError_txt"))).getAttribute("value").toString();
		   emailErr = driver.findElement(By.id(prop.getProperty("Register_EmailError_txt"))).getAttribute("value").toString();
		   phoneErr = driver.findElement(By.id(prop.getProperty("Register_PhoneError_txt"))).getAttribute("value").toString();
		   saddressErr = driver.findElement(By.id(prop.getProperty("Register_StreetAddrError_txt"))).getAttribute("value").toString();
		   cityErr = driver.findElement(By.id(prop.getProperty("Register_CityError_txt"))).getAttribute("value").toString();
		   stateErr = driver.findElement(By.id(prop.getProperty("Register_StateError_txt"))).getAttribute("value").toString();
		   zipErr = driver.findElement(By.id(prop.getProperty("Register_ZipCodeError_txt"))).getAttribute("value").toString();
		   platenumErr = driver.findElement(By.id(prop.getProperty("Register_CarNmbrError_txt"))).getAttribute("value").toString();
		   permitidErr = driver.findElement(By.id(prop.getProperty("Register_PermitIdError_txt"))).getAttribute("value").toString();
		  assertEquals("First Name cannot be empty",firstnameErr);
		  assertEquals("Last Name cannot be empty",lastnameErr);
		  assertEquals("Your Username should not be empty",usernameErr);
		  assertEquals("Password should have atleast 8 characters but not more than 12",passwordErr);
		  assertEquals("UTA Id should be a 10-digit number",utaidErr);
		  assertEquals("Email cannot be blank",emailErr);
		  assertEquals("Please enter a valid 10-digit phone number",phoneErr);
		  assertEquals("Zip code should be a 5-digit number",zipErr);
		  assertEquals("Car plate number should be a 4-digit number",platenumErr);
		  assertEquals("Your permit Id should be a 8-digit number",permitidErr);
		  assertEquals("This is a required field",saddressErr);
		  assertEquals("This is a required field",cityErr);
		  assertEquals("This is a required field",stateErr);
		  /*start of username */
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).sendKeys("1shakthi");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  usernameErr = driver.findElement(By.id(prop.getProperty("Register_UsernameError_txt"))).getAttribute("value").toString();
		  assertEquals("Username cannot contain special characters or numeric characters",usernameErr);
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).sendKeys("tshakthipra");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  usernameErr = driver.findElement(By.id(prop.getProperty("Register_UsernameError_txt"))).getAttribute("value").toString();
		  assertEquals("Username is already taken",usernameErr);
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).sendKeys("@shakthi");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  usernameErr = driver.findElement(By.id(prop.getProperty("Register_UsernameError_txt"))).getAttribute("value").toString();
		  assertEquals("Username cannot contain special characters or numeric characters",usernameErr);
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).sendKeys("sh");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  usernameErr = driver.findElement(By.id(prop.getProperty("Register_UsernameError_txt"))).getAttribute("value").toString();
		  assertEquals("Username should be 3 and 16 characters long",usernameErr);
		  //End of username
		  //start of password
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys("Sh!kthi");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  passwordErr = driver.findElement(By.id(prop.getProperty("Register_PasswordError_txt"))).getAttribute("value").toString();
		  assertEquals("Password should have atleast 8 characters but not more than 12",passwordErr);
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys("Shakthi1");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  passwordErr = driver.findElement(By.id(prop.getProperty("Register_PasswordError_txt"))).getAttribute("value").toString();
		  assertEquals("Password should contain atleast 1 special character",passwordErr);
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys("Shakthippppp");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  passwordErr = driver.findElement(By.id(prop.getProperty("Register_PasswordError_txt"))).getAttribute("value").toString();
		  assertEquals("Your password must contain atleast:1 special character & 1 number character",passwordErr);
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys("Sh@kthip");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  passwordErr = driver.findElement(By.id(prop.getProperty("Register_PasswordError_txt"))).getAttribute("value").toString();
		  assertEquals("Password should include atleast one number character",passwordErr);
		  //End of password
		  //start of confirmpassword
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys("Sh@kthip");
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).sendKeys("Sh");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  cpasswordErr = driver.findElement(By.id("confirmPasswordError")).getAttribute("value").toString();
		  assertEquals("The passwords don't match",cpasswordErr);
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys("Sh@kthip");
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).sendKeys("Sh@kthip");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  cpasswordErr = driver.findElement(By.id("confirmPasswordError")).getAttribute("value").toString();
		  assertEquals("",cpasswordErr);
		//End of cpassword
		  //start of firstname
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).sendKeys("shakthishakthishakthishakthisha");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  firstnameErr = driver.findElement(By.id(prop.getProperty("Register_FirstnameError_txt"))).getAttribute("value").toString();
		  assertEquals("Firstname should be between 3 and 30 characters long",firstnameErr);
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).sendKeys("shak1");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  firstnameErr = driver.findElement(By.id(prop.getProperty("Register_FirstnameError_txt"))).getAttribute("value").toString();
		  assertEquals("First name should only have alphabets",firstnameErr);
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).sendKeys("shakthi@p");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  firstnameErr = driver.findElement(By.id(prop.getProperty("Register_FirstnameError_txt"))).getAttribute("value").toString();
		  assertEquals("First name should only have alphabets",firstnameErr);
		  //End of firstname
		  //Start of Lastname
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).sendKeys("pr");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  lastnameErr = driver.findElement(By.id(prop.getProperty("Register_LastnameError_txt"))).getAttribute("value").toString();
		  assertEquals("Lastname name should be between 3 and 30 characters long",lastnameErr);
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).sendKeys("prak1");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  lastnameErr = driver.findElement(By.id(prop.getProperty("Register_LastnameError_txt"))).getAttribute("value").toString();
		  assertEquals("Last name should only have alphabets",lastnameErr);
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).sendKeys("prak@");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  lastnameErr = driver.findElement(By.id(prop.getProperty("Register_LastnameError_txt"))).getAttribute("value").toString();
		  assertEquals("Last name should only have alphabets",lastnameErr);
		  //end of lastname
		  //start of email
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).sendKeys("tshakthi");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  emailErr = driver.findElement(By.id(prop.getProperty("Register_EmailError_txt"))).getAttribute("value").toString();
		  assertEquals("Please enter a valid email address",emailErr);
		  //end of email
		  //start of phone
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).sendKeys("123456789");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  phoneErr = driver.findElement(By.id("phoneError")).getAttribute("value").toString();
		  assertEquals("Please enter a valid 10-digit phone number",phoneErr);
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).sendKeys("123456789@");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  phoneErr = driver.findElement(By.id("phoneError")).getAttribute("value").toString();
		  assertEquals("Please enter a valid 10-digit phone number",phoneErr);
		  //end of phone
		  //start of utaId
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).sendKeys("10015188121");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  utaidErr = driver.findElement(By.id(prop.getProperty("Register_UtaIdError_txt"))).getAttribute("value").toString();
		  assertEquals("UTA Id should be a 10-digit number",utaidErr);
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).sendKeys("100151881a");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  utaidErr = driver.findElement(By.id(prop.getProperty("Register_UtaIdError_txt"))).getAttribute("value").toString();
		  assertEquals("UTA Id should be a 10-digit number",utaidErr);
		  //end of utaid
		  //start of car plate number
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).sendKeys("811");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  platenumErr = driver.findElement(By.id(prop.getProperty("Register_CarNmbrError_txt"))).getAttribute("value").toString();
		  assertEquals("Car plate number should be a 4-digit number",platenumErr);
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).sendKeys("811a");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  platenumErr = driver.findElement(By.id(prop.getProperty("Register_CarNmbrError_txt"))).getAttribute("value").toString();
		  assertEquals("Car plate number should be a 4-digit number",platenumErr);
		  //end of carplate number
		  //start of permitid number
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).sendKeys("1234567");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  permitidErr = driver.findElement(By.id(prop.getProperty("Register_PermitIdError_txt"))).getAttribute("value").toString();
		  assertEquals("Your permit Id should be a 8-digit number",permitidErr);
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).sendKeys("1234567a");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  permitidErr = driver.findElement(By.id(prop.getProperty("Register_PermitIdError_txt"))).getAttribute("value").toString();
		  assertEquals("Your permit Id should be a 8-digit number",permitidErr);
		  //end of permitid
		  //start of street add
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).sendKeys("1234");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  saddressErr = driver.findElement(By.id(prop.getProperty("Register_StreetAddrError_txt"))).getAttribute("value").toString();
		  assertEquals("Street address should be between 5 and 30 characters long",saddressErr);
		  //End of street address
		  //start of city
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).sendKeys("s");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  cityErr = driver.findElement(By.id(prop.getProperty("Register_CityError_txt"))).getAttribute("value").toString();
		  assertEquals("City name should be between 2 and 20 characters long",cityErr);
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).sendKeys("c!ty");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  cityErr = driver.findElement(By.id(prop.getProperty("Register_CityError_txt"))).getAttribute("value").toString();
		  assertEquals("City name should only have alphabets",cityErr);
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).sendKeys("c1ty");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  cityErr = driver.findElement(By.id(prop.getProperty("Register_CityError_txt"))).getAttribute("value").toString();
		  assertEquals("City name should only have alphabets",cityErr);
		  //end of city
		  //start of state
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).sendKeys("s");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  stateErr = driver.findElement(By.id(prop.getProperty("Register_StateError_txt"))).getAttribute("value").toString();
		  assertEquals("State name should be between 2 and 20 characters long",stateErr);
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).sendKeys("st@te");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  stateErr = driver.findElement(By.id(prop.getProperty("Register_StateError_txt"))).getAttribute("value").toString();
		  assertEquals("State name should only have alphabets",stateErr);
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).sendKeys("st1te");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  stateErr = driver.findElement(By.id(prop.getProperty("Register_StateError_txt"))).getAttribute("value").toString();
		  assertEquals("State name should only have alphabets",stateErr);
		  //end of state
		  //start of zip
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).sendKeys("7601");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  zipErr = driver.findElement(By.id(prop.getProperty("Register_ZipCodeError_txt"))).getAttribute("value").toString();
		  assertEquals("Zip code should be a 5-digit number",zipErr);
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).sendKeys("7601a");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  zipErr = driver.findElement(By.id(prop.getProperty("Register_ZipCodeError_txt"))).getAttribute("value").toString();
		  assertEquals("Zip code should be a 5-digit number",zipErr);
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).sendKeys("7601@");
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  zipErr = driver.findElement(By.id(prop.getProperty("Register_ZipCodeError_txt"))).getAttribute("value").toString();
		  assertEquals("Zip code should be a 5-digit number",zipErr);
		  //End of Zip
		  //Clearing all fields and giving correct inputs
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Username_txt"))).sendKeys(username);
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Password_txt"))).sendKeys("Test@123");
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Firstname_txt"))).sendKeys("Kennet");
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Lastname_txt"))).sendKeys("Erik");
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Cpassword_txt"))).sendKeys("Test@123");
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_UtaId_txt"))).sendKeys("1001518112");
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_EmailId_txt"))).sendKeys("kenneterik@gmail.com");
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Phone_num"))).sendKeys("9443145074");
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Saddress_txt"))).sendKeys("603 causley");
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_City_txt"))).sendKeys("arlington");
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_State_txt"))).sendKeys("Texas");
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_Zip_num"))).sendKeys("76010");
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PlateNumber_num"))).sendKeys("8112");
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).sendKeys("12345678");
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).clear();
		  driver.findElement(By.id(prop.getProperty("Register_PermitId_num"))).sendKeys("12345678");
		  new Select(driver.findElement(By.id(prop.getProperty("Register_UserRole_txt")))).selectByVisibleText(user_role);
		  driver.findElement(By.id(prop.getProperty("Register_InsertUser_btn"))).sendKeys(Keys.ENTER);
		  assertEquals("Registration Successful",driver.findElement(By.id("successMsg")).getText());
		  driver.findElement(By.id("login_link")).click();
		  
	}

}
