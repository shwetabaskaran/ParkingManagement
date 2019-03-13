package parkingManagement.model;

import parkingManagement.data.RegisterUserDao;

import java.util.Objects;
import java.util.regex.*;
public class User {

	RegisterUserDao userDao = new RegisterUserDao();
	
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String confirmPassword;
	private String uta_id;
	private String role;
	private String phone;
	private String email;
	private String street_add;
	private String city;
	private String state;
	private String zip;
	private String plate_number;
	private String permit_id;
	private String permit_type;
	private int noshows;
	private int overdue;
	private String user_status;
	
	public User() {}
	
	@Override
	public String toString() {
		return "User [userDao=" + userDao + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", password=" + password + ", confirmPassword=" + confirmPassword + ", uta_id=" + uta_id
				+ ", role=" + role + ", phone=" + phone + ", email=" + email + ", street_add=" + street_add + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", plate_number=" + plate_number + ", permit_id="
				+ permit_id + ", permit_type=" + permit_type + ", noshows=" + noshows + ", overdue=" + overdue
				+ ", user_status=" + user_status + "]";
	}

	public User(String firstname, String lastname, String username, String password, String confirmPassword, String uta_id, String role,
			String phone, String email, String street_add, String city, String state, String zip, String plate_number,
			String permit_id, String permit_type) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.uta_id = uta_id;
		this.role = role;
		this.phone = phone;
		this.email = email;
		this.street_add = street_add;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.plate_number = plate_number;
		this.permit_id = permit_id;
		this.permit_type = permit_type;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUta_id() {
		return uta_id;
	}
	public void setUta_id(String uta_id) {
		this.uta_id = uta_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStreet_add() {
		return street_add;
	}
	public void setStreet_add(String street_add) {
		this.street_add = street_add;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip_code() {
		return zip;
	}
	public void setZip_code(String zip_code) {
		this.zip = zip_code;
	}
	public String getCar_plate_num() {
		return plate_number;
	}
	public void setCar_plate_num(String car_plate_num) {
		this.plate_number = car_plate_num;
	}
	public String getPermit_id() {
		return permit_id;
	}
	public void setPermit_id(String permit_id) {
		this.permit_id = permit_id;
	}
	public String getPermit_type() {
		return permit_type;
	}
	public void setPermit_type(String permit_type) {
		this.permit_type = permit_type;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public int getNoshows() {
		return noshows;
	}

	public void setNoshows(int noshows) {
		this.noshows = noshows;
	}

	public int getOverdue() {
		return overdue;
	}

	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}
	
	public String getUserStatus() {
		return user_status;
	}

	public void setUserStatus(String user_status) {
		this.user_status = user_status;
	}
	

	public void validateUser (User user, RegisterUserErrorMsgs errorMsgs,String isMyprofile) {
		if(isMyprofile.equals(""))
			errorMsgs.setUsernameError(validateUsername(user.getUsername()));
		errorMsgs.setFirstnameError(validateFirstName(user.getFirstname()));
		errorMsgs.setLastnameError(validateLastName(user.getLastname()));
		errorMsgs.setPasswordError(validatePassword(user.getPassword()));
		errorMsgs.setUtaIdError(validateUtaId(user.getUta_id()));
		errorMsgs.setEmailError(validateEmail(user.getEmail()));
		errorMsgs.setPhoneError(validatePhone(user.getPhone()));
		errorMsgs.setZipCodeError(validateZipCode(user.getZip_code()));
		errorMsgs.setCarNmbrError(validateCarNumber(user.getCar_plate_num()));
		errorMsgs.setPermitIdError(validatePermitId(user.getPermit_id()));
		errorMsgs.setConfirmPwdError(validateConfirmPwd(user.getConfirmPassword(), user.getPassword()));
		errorMsgs.setStreetAddrError(validateStreetAddress(user.getStreet_add()));
		errorMsgs.setCityError(validateCity(user.getCity()));
		errorMsgs.setStateError(validateState(user.getState()));
		errorMsgs.setErrorMsg();
	}
	private String validateUsername (String username) {
		boolean hasChar = false;
		boolean hasNumber = false;
		String result="";
		char[] array=username.toCharArray();
		if (!stringSize(username,3,16))
			result= "Your Username should not be empty";
			else
				if (RegisterUserDao.verifyUniqueUsername(username))
					result="Username is already taken";
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		if(hasChar || hasNumber) {
			result = "Username cannot contain special characters or numeric characters";
		}
		return result;				
	}
	private String validateStreetAddress(String address) {
		String result= "";
		if(address.length() == 0)
			result = "This is a required field";
		else if(!stringSize(address,5,30))
			result = "Street address should be between 5 and 30 characters long";
		return result;
	}
	private String validateCity(String city) {
		String result="";
		if(city.length() == 0)
			result = "This is a required field";
		else if(!stringSize(city,2,20))
			result = "City name should be between 2 and 20 characters long";
		else if(hasChar(city) || isCharAnInteger(city))
			result = "City name should only have alphabets";
		return result;
	}
	private String validateState(String state) {
		String result = "";
		if(state.length() == 0)
			result = "This is a required field";
		else if(!stringSize(state,2,20))
			result = "State name should be between 2 and 20 characters long";
		else if(hasChar(state) || isCharAnInteger(state))
			result = "State name should only have alphabets";
		return result;
	}
	private String validateConfirmPwd(String cPassword, String password) {
		String result="";
		if(!Objects.equals(cPassword, password)) {
			result = "The passwords don't match";
		}
		return result;
	}
	private String validateZipCode(String code) {
		String result = "";
		if(isTextAnInteger(code) == false || code.length()!=5) {
			result = "Zip code should be a 5-digit number";
		}
		return result;
	}
	private String validateCarNumber(String number) {
		String result = "";
		if(isTextAnInteger(number) == false || number.length() != 4) {
			result = "Car plate number should be a 4-digit number";
		}
		return result;
	}
	private String validatePermitId(String id) {
		String result="";
		if(isTextAnInteger(id) == false || id.length() != 8) {
			result = "Your permit Id should be a 8-digit number";
		}
		return result;
	}
	private String validateFirstName (String name) {
		String result="";
		String expression = "^[a-zA-Z]*";
		if (name.length() == 0)
			result= "First Name cannot be empty";
		/*
		 * else if (Character.isLowerCase(name.charAt(0)))
		 * result="Your First Name must start with a capital letter";
		 */
		else if(!stringSize(name,3,30))
			result = "Firstname should be between 3 and 30 characters long";
//		else if(hasChar(name) || isTextAnInteger(name))
		else if(!(name.matches(expression)))
			result = "First name should only have alphabets";
		return result;
	} 
	private String validatePassword(String password) {
		boolean hasChar = false;
		boolean hasNumber = false;
		boolean length = false;
		char[] array=password.toCharArray();
		String result="";
		if (!stringSize(password,8,12)) {
			length = true;
			result= "Password should have atleast 8 characters but not more than 12";
		}
		else{
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		
		if(!hasChar) {
			result = "Password should contain atleast 1 special character";
		}
		if(!hasNumber) {
			result = "Password should include atleast one number character";
		}
		if(!hasChar && !hasNumber) {
			result = "Your password must contain atleast:1 special character & 1 number character";
		}
		}
		return result;
	}
	private String validateUtaId(String id) {
		String result="";
		if(isTextAnInteger(id) == false) {
			result = "UTA Id should be a 10-digit number";
		}
		if(id.length() != 10) {
			result="UTA Id should be a 10-digit number";
		}
		return result;
	}
	private String validateEmail(String email) {
		String result="";
		if(email.equals("")) {
			result = "Email cannot be blank";
		}
		else{
		 String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                 "[a-zA-Z0-9_+&*-]+)*@" + 
                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                 "A-Z]{2,7}$"; 
		 Pattern p = Pattern.compile(regex);
		 if(! p.matcher(email).matches()) {
			 result = "Please enter a valid email address";
		 }
		 }
		 return result;
	}
	private String validatePhone(String phone) {
		String result="";
		if(isTextAnInteger(phone) == false || phone.length() != 10) {
			result = "Please enter a valid 10-digit phone number";
		}
		
		return result;
	}
	private String validateLastName (String surname) { 
		String result="";
		String expression = "^[a-zA-Z]*";
		if (surname.length() == 0)
			result= "Last Name cannot be empty";
		else if(!stringSize(surname,3,30))
			result = "Lastname name should be between 3 and 30 characters long";
		else if(!(surname.matches(expression)))
			result = "Last name should only have alphabets";
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max; 
	}
	private boolean isTextAnInteger (String string) {
        boolean result = false;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
	private boolean isCharAnInteger (String string) {
        boolean result = false;
        char[] array=string.toCharArray();
        for(int i=0;i<array.length;i++) {
			if(Character.isDigit(array[i]))
					result = true;
		}
		return result;
	}

	private boolean hasChar(String input) {
		boolean result = false;
		char[] array=input.toCharArray();
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					result = true;
				}
				
			}
			
		}
		return result;
	}
	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	
}
