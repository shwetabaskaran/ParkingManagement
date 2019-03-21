package parkingManagement.model;

import static org.junit.Assert.*;
import parkingManagement.model.User;
import parkingManagement.model.UserErrorMsgs;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)

public class UserTest {
	@FileParameters("test/UserTestData.csv")
	@Test
	public void validateUserTest(int testno, String username, String password, String confirmPass, String firstname,
			String lastname, String email, String phone, String utaid, String num_plate, String permit_id,
			String permit_type, String address, String city, String state, String user_role, String zip, String errMsg,
			String usernameErr, String firstnameErr, String lastnameErr, String passwordErr, String cpasswordErr,
			String utaidErr, String emailErr, String phoneErr, String zipErr, String num_plateErr, String permitIdErr,
			String addErr, String cityErr, String stateErr) {
			UserErrorMsgs regerrMsg  = new UserErrorMsgs();
			User userTest = new User(firstname,lastname,username,password,confirmPass,utaid,user_role,phone,email,address,city,state,zip,num_plate,permit_id,permit_type);
			userTest.validateUser(userTest, regerrMsg,"");
			assertEquals(firstnameErr,regerrMsg.getFirstnameError());
			assertEquals(lastnameErr,regerrMsg.getLastnameError());
			assertEquals(passwordErr,regerrMsg.getPasswordError());
			assertEquals(cpasswordErr,regerrMsg.getConfirmPwdError());
			assertEquals(usernameErr,regerrMsg.getUsernameError());
			assertEquals(phoneErr,regerrMsg.getPhoneError());
			assertEquals(emailErr,regerrMsg.getEmailError());
			assertEquals(cityErr,regerrMsg.getCityError());
			assertEquals(num_plateErr,regerrMsg.getCarNmbrError());
			assertEquals(permitIdErr,regerrMsg.getPermitIdError());
			assertEquals(stateErr,regerrMsg.getStateError());
			assertEquals(zipErr,regerrMsg.getZipCodeError());
			assertEquals(utaidErr,regerrMsg.getUtaIdError());
			assertEquals(addErr,regerrMsg.getStreetAddrError());
			assertEquals(errMsg,regerrMsg.getErrorMsg());
			userTest.validateUser(userTest, regerrMsg,"fortest");
	}
}
