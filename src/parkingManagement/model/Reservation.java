package parkingManagement.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parkingManagement.data.ReservationDao;

import java.sql.Date;

public class Reservation {
	
	int reservation_id;
	int parkingarea_id;
	String username;
	Date reservation_date;	
	Time from_time;
	Time to_time;
	int parkingslot_no;
	
	@Override
	public String toString() {
		return "Reservation [reservation_id=" + reservation_id + ", parkingarea_id=" + parkingarea_id + ", username="
				+ username + ", reservation_date=" + reservation_date + ", from_time=" + from_time + ", to_time=" + to_time
				+ ", parkingslot_no=" + parkingslot_no + ", cart=" + cart + ", camera=" + camera + ", history="
				+ history + "]";
	}
	boolean cart;
	boolean camera;
	boolean history;
	
	public Reservation(){}
	
	public Reservation(int reservation_id, int parkingarea_id, String username, Date reservation_date, Time from_time,
			Time to_time, int parkingslot_no, boolean cart, boolean camera, boolean history) {
		super();
		this.reservation_id = reservation_id;
		this.parkingarea_id = parkingarea_id;
		this.username = username;
		this.reservation_date = reservation_date;
		this.from_time = from_time;
		this.to_time = to_time;
		this.parkingslot_no = parkingslot_no;
		this.cart = cart;
		this.camera = camera;
		this.history = history;
	}
	
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public void setFrom_time(Time from_time) {
		this.from_time = from_time;
	}
	public void setTo_time(Time to_time) {
		this.to_time = to_time;
	}
	public Time getFrom_time() {
		return from_time;
	}
	public Time getTo_time() {
		return to_time;
	}	
	public boolean isCart() {
		return cart;
	}
	public void setCart(boolean cart) {
		this.cart = cart;
	}
	public boolean isCamera() {
		return camera;
	}
	public void setCamera(boolean camera) {
		this.camera = camera;
	}
	public boolean isHistory() {
		return history;
	}
	public void setHistory(boolean history) {
		this.history = history;
	}
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
	public int getParkingslot_no() {
		return parkingslot_no;
	}
	public void setParkingslot_no(int parkingslot_no) {
		this.parkingslot_no = parkingslot_no;
	}

	public void validateReservedCount(ReservationErrorMsgs errorMsgs,
			String username) {
		errorMsgs.setReservedCountErrorMsg(validate(username));
		errorMsgs.setReservationErrormsg();
	}
	public String validate(String username) {
		ReservationDao reservationDao = new ReservationDao();
		int reservationsCount = reservationDao.getReservationCount(username);
		if(reservationsCount>=3)
			return "You cannot have more than three slots reserved. Delete one of your reserved slots to reserve a new slot.";
		return "";
	}
}
