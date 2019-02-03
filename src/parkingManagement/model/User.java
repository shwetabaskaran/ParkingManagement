package parkingManagement.model;

import parkingManagement.data.RegisterUserDao;

public class User {
	
	RegisterUserDao userDao = new RegisterUserDao();
	
	private String firstname;
	private String lastname;
	private String username;
	private String password;
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
	
	public User() {}
	
	public User(String firstname, String lastname, String username, String password, String uta_id, String role,
			String phone, String email, String street_add, String city, String state, String zip, String plate_number,
			String permit_id, String permit_type) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
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
	
	public void validateUser (User user, RegisterUserErrorMsgs errorMsgs) {
		errorMsgs.setUsernameError(validateUsername(user.getUsername()));
		errorMsgs.setFirstnameError(validateFirstName(user.getFirstname()));
		errorMsgs.setLastnameError(validateLastName(user.getLastname()));
		errorMsgs.setErrorMsg();
	}
	private String validateUsername (String username) {
		String result="";
		if (!stringSize(username,3,16))
			result= "Your Username must between 3 and 16 digits";
			else
				if (RegisterUserDao.verifyUniqueUsername(username))
					result="Username is already taken";
		return result;				
	}
	private String validateFirstName (String name) {
		String result="";
		if (!stringSize(name,1,45))
			result= "Your First Name must between 1 and 45 digits";
		else
			if (Character.isLowerCase(name.charAt(0)))
				result="Your First Name must start with a capital letter";
		return result;
	}
	
	private String validateLastName (String surname) {
		String result="";
		if (!stringSize(surname,1,45))
			result= "Your Last Name must between 1 and 45 digits";
		else
			if (Character.isLowerCase(surname.charAt(0)))
				result="Your Last Name must start with a capital letter";
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	private boolean isTextAnInteger (String string) {
        boolean result;
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
}
