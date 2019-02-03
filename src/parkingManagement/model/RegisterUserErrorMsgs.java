package parkingManagement.model;

public class RegisterUserErrorMsgs {
	private String usernameError;
	private String errorMsg;
	private String firstnameError;
	private String lastnameError;
	
	public RegisterUserErrorMsgs () {
		this.errorMsg="";
		this.usernameError="";
		this.firstnameError="";
		this.lastnameError="";
	}
	
	public String getFirstnameError() {
		return firstnameError;
	}

	public void setFirstnameError(String firstnameError) {
		this.firstnameError = firstnameError;
	}

	public String getLastnameError() {
		return lastnameError;
	}

	public void setLastnameError(String lastnameError) {
		this.lastnameError = lastnameError;
	}

	public String getUsernameError() {
		return usernameError;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg() {
		if (!usernameError.equals("") || !firstnameError.equals("") || !lastnameError.equals("") )
			errorMsg="Please correct the following errors";
	}

	public void setUsernameError(String usernameErr) {
		this.usernameError = usernameErr;
	}

}
