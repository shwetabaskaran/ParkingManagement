package parkingManagement.model;

public class SearchParkingspotErrorMsgs {

	private String errorMsg;
	private String parkingAreaError;
	private String parkingTypeError;
	
	public SearchParkingspotErrorMsgs () {
		this.errorMsg="";
		this.parkingAreaError="";
		this.parkingTypeError="";
	}
	
	public String getParkingAreaError() {
		return parkingAreaError;
	}

	public void setParkingAreaError(String parkingAreaError) {
		this.parkingAreaError = parkingAreaError;
	}

	public String getParkingTypeError() {
		return parkingTypeError;
	}

	public void setParkingTypeError(String parkingTypeError) {
		this.parkingTypeError = parkingTypeError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
			if (!parkingAreaError.equals("") || !parkingTypeError.equals(""))
				this.errorMsg="Please correct the following errors";
	}
}