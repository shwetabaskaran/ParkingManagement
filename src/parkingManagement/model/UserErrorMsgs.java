package parkingManagement.model;

public class UserErrorMsgs {
	private String usernameError;
	private String errorMsg;
	private String firstnameError;
	private String lastnameError;
	private String passwordError;
	private String utaIdError;
	private String emailError;
	private String phoneError;
	private String zipCodeError;
	private String carNmbrError;
	private String permitIdError;
	private String confirmPwdError;
	private String streetAddrError;
	private String cityError;
	private String stateError;
	
	private String loginErrMsg;
	
	public String getLoginErrMsg() {
		return loginErrMsg;
	}
	public void setLoginErrMsg(String loginErrMsg) {
		this.loginErrMsg = loginErrMsg;
	}
	
	public UserErrorMsgs () {
		this.errorMsg="";
		this.usernameError="";
		this.firstnameError="";
		this.lastnameError="";
		this.passwordError="";
		this.utaIdError="";
		this.emailError="";
		this.phoneError="";
		this.zipCodeError="";
		this.carNmbrError="";
		this.permitIdError="";
		this.confirmPwdError="";
		this.streetAddrError="";
		this.cityError="";
		this.stateError="";
		this.loginErrMsg="";
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
		if (!streetAddrError.equals("") || !cityError.equals("") || !stateError.equals("") ||!usernameError.equals("") || !firstnameError.equals("") || !lastnameError.equals("") || !passwordError.equals("")|| !utaIdError.equals("") || !emailError.equals("") || !phoneError.equals("") || !zipCodeError.equals("") || !carNmbrError.equals("") || !permitIdError.equals("") || !confirmPwdError.equals("") )
			errorMsg="Please correct the following errors";
	}

	public void setUsernameError(String usernameErr) {
		this.usernameError = usernameErr;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getUtaIdError() {
		return utaIdError;
	}

	public void setUtaIdError(String utaIdError) {
		this.utaIdError = utaIdError;
	}

	public String getEmailError() {
		return emailError;
	}

	public void setEmailError(String emailError) {
		this.emailError = emailError;
	}

	public String getPhoneError() {
		return phoneError;
	}

	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}

	public String getZipCodeError() {
		return zipCodeError;
	}

	public void setZipCodeError(String zipCodeError) {
		this.zipCodeError = zipCodeError;
	}

	public String getCarNmbrError() {
		return carNmbrError;
	}

	public void setCarNmbrError(String carNmbrError) {
		this.carNmbrError = carNmbrError;
	}

	public String getPermitIdError() {
		return permitIdError;
	}

	public void setPermitIdError(String permitIdError) {
		this.permitIdError = permitIdError;
	}

	public String getConfirmPwdError() {
		return confirmPwdError;
	}

	public void setConfirmPwdError(String confirmPwdError) {
		this.confirmPwdError = confirmPwdError;
	}

	public String getStreetAddrError() {
		return streetAddrError;
	}

	public void setStreetAddrError(String streetAddrError) {
		this.streetAddrError = streetAddrError;
	}

	public String getCityError() {
		return cityError;
	}

	public void setCityError(String cityError) {
		this.cityError = cityError;
	}

	public String getStateError() {
		return stateError;
	}

	public void setStateError(String stateError) {
		this.stateError = stateError;
	}
	

}
