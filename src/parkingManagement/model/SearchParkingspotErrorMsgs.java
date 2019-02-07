package parkingManagement.model;

public class SearchParkingspotErrorMsgs {

	private String errorMsg;
	private String parkingAreaError;
	private String parkingTypeError;
	private String reservationStatusError;
	private String reservationFromError;
	private String reservationToError;
	
	public SearchParkingspotErrorMsgs () {
		this.errorMsg="";
		this.parkingAreaError="";
		this.parkingTypeError="";
		this.reservationStatusError="";
		this.reservationFromError="";
		this.reservationToError="";
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

	public String getReservationStatusError() {
		return reservationStatusError;
	}

	public void setReservationStatusError(String reservationStatusError) {
		this.reservationStatusError = reservationStatusError;
	}

	public String getReservationFromError() {
		return reservationFromError;
	}

	public void setReservationFromError(String reservationFromError) {
		this.reservationFromError = reservationFromError;
	}

	public String getReservationToError() {
		return reservationToError;
	}

	public void setReservationToError(String reservationToError) {
		this.reservationToError = reservationToError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
			if (!parkingAreaError.equals("") || !parkingTypeError.equals("") || 
					!reservationStatusError.equals("") || !reservationFromError.equals("") || 
					!reservationToError.equals(""))
				this.errorMsg="Please correct the following errors";
	}
}