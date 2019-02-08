package parkingManagement.model;

import java.util.Date;

public class Reservation {
	
	int reservation_id;
	int parkingarea_id;
	String username;
	Date from;
	Date to;
	int parkingslot_no;
	
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public int getParkingarea_id() {
		return parkingarea_id;
	}
	public void setParkingarea_id(int parkingarea_id) {
		this.parkingarea_id = parkingarea_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public int getParkingslot_no() {
		return parkingslot_no;
	}
	public void setParkingslot_no(int parkingslot_no) {
		this.parkingslot_no = parkingslot_no;
	}
}
