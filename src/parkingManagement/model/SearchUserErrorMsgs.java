package parkingManagement.model;

public class SearchUserErrorMsgs {
	private String lastNameErrMsg;
	private String userNameErrMsg;
	
	public String getUserNameErrMsg() {
		return userNameErrMsg;
	}
	public void setUserNameErrMsg(String userNameErrMsg) {
		this.userNameErrMsg = userNameErrMsg;
	}
	
	public String getLastNameErrMsg() {
		return lastNameErrMsg;
	}
	public void setLastNameErrMsg(String lastNameErrMsg) {
		this.lastNameErrMsg = lastNameErrMsg;
	}
}
