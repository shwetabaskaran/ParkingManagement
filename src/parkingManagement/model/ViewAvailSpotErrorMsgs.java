package parkingManagement.model;

public class ViewAvailSpotErrorMsgs {
	private String errorMsg;
	private String reservationFromError;
	private String reservationToError;
	private String spotNoError;
	
	public ViewAvailSpotErrorMsgs () {
		this.errorMsg="";
		this.reservationFromError="";
		this.reservationToError="";
		this.spotNoError="";
	}
	

	public String getspotNoError() {
		return spotNoError;
	}

	public void setspotNoError(String spotNoError) {
		this.spotNoError = spotNoError;
		this.errorMsg="error";
	}
	
	public String getReservationFromError() {
		return reservationFromError;
	}

	public void setReservationFromError(String reservationFromError) {
		this.reservationFromError = reservationFromError;
		this.errorMsg="error";
	}

	public String getReservationToError() {
		return reservationToError;
	}

	public void setReservationToError(String reservationToError) {
		this.reservationToError = reservationToError;
		this.errorMsg="error";
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
			if (!reservationFromError.equals("") || 
					!reservationToError.equals("") || 
					!spotNoError.equals(""))
				this.errorMsg="Please correct the following errors";
	}
}
