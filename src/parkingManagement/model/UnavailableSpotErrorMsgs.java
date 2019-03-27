package parkingManagement.model;

public class UnavailableSpotErrorMsgs {

private String spotNumErrMsg;
private String fromErrMsg;
private String toErrMsg;
private String errorMsg;

public UnavailableSpotErrorMsgs() {
	this.spotNumErrMsg="";
	this.fromErrMsg="";
	this.toErrMsg="";
	this.errorMsg="";
}
	
	public String getspotNumErrMsg() {
		return spotNumErrMsg;
	}

	public void setspotNumErrMsg(String spotNumErrMsg) {
		this.spotNumErrMsg = spotNumErrMsg;
	}
	
	public String getfromErrMsg() {
		return fromErrMsg;
	}

	public void setfromErrMsg(String fromErrMsg) {
		this.fromErrMsg = fromErrMsg;
	}

	public String gettoErrMsg() {
		return toErrMsg;
	}

	public void settoErrMsg(String toErrMsg) {
		this.toErrMsg = toErrMsg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
			if (!fromErrMsg.equals("") || 
					!toErrMsg.equals(""))
				this.errorMsg="Please correct the following errors";
	}

}
