package parkingManagement.model;

public class ParkingAreaErrorMsgs {
	private String errormsg;
	private String parkingareaNameError;
	private String CapacityError;
	private String floorError;
	
	public String getParkingareaNameError() {
		return parkingareaNameError;
	}
	public void setParkingareaNameError(String parkingareaNameError) {
		this.parkingareaNameError = parkingareaNameError;
	}
	public String getCapacityError() {
		return CapacityError;
	}
	public void setCapacityError(String capacityError) {
		CapacityError = capacityError;
	}
	public String getFloorError() {
		return floorError;
	}
	public void setFloorError(String floorError) {
		this.floorError = floorError;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg() {
		if(!parkingareaNameError.equals("") || !CapacityError.equals("") || !floorError.equals("") )
			errormsg = "Please correct the following";
		else
			errormsg = "";
			
	}

}
