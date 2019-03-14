package parkingManagement.model;

public class ReservationErrorMsgs {
	String reservedCountErrorMsg ="";
	String reservationErrormsg="";
	
	public String getReservedCountErrorMsg() {
		return reservedCountErrorMsg;
	}
	public void setReservedCountErrorMsg(String reservedCountErrorMsg) {
		this.reservedCountErrorMsg = reservedCountErrorMsg;
	}
	public String getReservationErrormsg() {
		return reservationErrormsg;
	}
	public void setReservationErrormsg() {
		if(!reservedCountErrorMsg.equals(""))
			reservationErrormsg = "STOP!!";
		else
			reservationErrormsg = "";
	}
}
