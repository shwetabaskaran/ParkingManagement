package parkingManagement.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingArea {
	String parkingarea_name;
	String parkingtype;
	int capacity;
	int availability;
	int floor;
	
	public String getParkingarea_name() {
		return parkingarea_name;
	}

	public void setParkingarea_name(String parkingarea_name) {
		this.parkingarea_name = parkingarea_name;
	}

	public String getParkingtype() {
		return parkingtype;
	}

	public void setParkingtype(String parkingtype) {
		this.parkingtype = parkingtype;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void ValidateSearchParkingSpot(ParkingArea parkingspot, SearchParkingspotErrorMsgs errorMsgs,
			String from, String to) {
		errorMsgs.setParkingAreaError(validateParkingArea(parkingspot.getParkingarea_name()));
		errorMsgs.setParkingTypeError(validateParkingType(parkingspot.getParkingtype()));
		errorMsgs.setReservationFromError(validateReservationFrom(from));
		errorMsgs.setReservationToError(validateReservationTo(from, to));
		
		errorMsgs.setErrorMsg();
	}
	
	public String validateParkingArea(String parkingArea) {
		System.out.println("Parking area is : "+parkingArea);
		if (parkingArea == null || parkingArea.equals("Select")) 
			return "Please select a parking area";
		return "";
	}
	
	public String validateParkingType(String parkingType) {
		
		if (parkingType == null || parkingType.equals("Select")) 
			return "Please select a parking type";
		return "";
	}
	
	public String validateFromAndToTime(String fromTime, String toTime) {
		
		String[] fromTimeArray = fromTime.split(":");
		int fromHours = Integer.parseInt(fromTimeArray[0]);
		int fromMinutes = Integer.parseInt(fromTimeArray[1]);
		
		String[] toArray = toTime.split(":");
		int toHours = Integer.parseInt(toArray[0]);
		int toMinutes = Integer.parseInt(toArray[1]);
		
		System.out.println("toHours is " +toHours+ " from Hours is : "+fromHours + " is to less tha from? " +(toHours<fromHours));
		System.out.println("tominutes is " +toMinutes+ " from minutes is : "+fromMinutes+ " is to less tha from? " +(toMinutes<fromMinutes));
		if (toHours<fromHours)
			return "To time cannot be earlier than from time, please correct";
		else if (toHours==fromHours) {
			if (toMinutes<fromMinutes)
				return "To time cannot be earlier than from time, please correct";
		}
		return "";
	}
	
	private String validateReservationTo(String from, String to) {
		
		if (to.equals("") || to.equals(null)) 
			return "Please enter reservation end time";
		if (from.equals("") || from.equals(null)) 
			return "Please enter reservation end time";
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		int currentMinutes = Integer.parseInt(currentTimeArray[1]);
		
		String[] toArray = to.split(":");
		int toHours = Integer.parseInt(toArray[0]);
		int toMinutes = Integer.parseInt(toArray[1]);
		if(toMinutes != 00 || toMinutes!=15 || toMinutes!=30 || toMinutes!= 45){
			return "Please correct enter time in the format HH:mm, valid values for mm are 00 or 15 or 30 or 45";
		}
		if (toHours<currentHours)
			return "Please enter valid reseration end time";
		else if (toHours==currentHours) {
			if (toMinutes<currentMinutes)
				return "Please enter valid reseration end time";
		}
		String compare = validateFromAndToTime(from, to);
		if(!compare.equals(""))
			return compare;
		
		return "";
	}
	private String validateReservationFrom(String from) {
		
		if (from.equals("") || from.equals(null)) 
			return "Please enter reservation start time";
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		int currentMinutes = Integer.parseInt(currentTimeArray[1]);
		
		String[] fromArray = from.split(":");
		int fromHours = Integer.parseInt(fromArray[0]);
		int fromMinutes = Integer.parseInt(fromArray[1]);
		
		if(fromMinutes != 00 || fromMinutes!=15 || fromMinutes!=30 || fromMinutes!= 45){
			return "Please correct enter time in the format HH:mm, valid values for mm are 00 or 15 or 30 or 45";
		}
		
		if (fromHours<currentHours)
			return "Please enter valid reseration start time";
		else if (fromHours==currentHours) {
			if (fromMinutes<currentMinutes)
				return "Please enter valid reseration start time";
		}
		
		return "";
	}
	

	public String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    return dateFormat.format(date);
	}

	

	
}
