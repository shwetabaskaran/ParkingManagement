package parkingManagement.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingArea {
	int parkingarea_id;
	String parkingarea_name;
	String parkingtype;
	int capacity;
	int floor;
	
	public int getParkingarea_id() {
		return parkingarea_id;
	}

	public void setParkingarea_id(int parkingarea_id) {
		this.parkingarea_id = parkingarea_id;
	}

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
		if(!(fromTimeArray[0].length()==2))
				return "";
		if(!(fromTimeArray[1].length()==2))
			return "";
		int fromHours;
		int fromMinutes;
		try {
			fromHours = Integer.parseInt(fromTimeArray[0]);
			fromMinutes = Integer.parseInt(fromTimeArray[1]);
		}catch (NumberFormatException e){
			return "";
		}
		
		
		String[] toArray = toTime.split(":");
		if(!(toArray[0].length()==2))
			return "Please enter time in the format HH:mm";
		if(!(toArray[1].length()==2))
			return "Please enter time in the format HH:mm";
		int toHours;
		int toMinutes;
		try {
			toHours = Integer.parseInt(toArray[0]);
			toMinutes = Integer.parseInt(toArray[1]);
		}catch (NumberFormatException e){
			return "Please enter date in the format HH:mm";
		}
				
		if (toHours<fromHours) {
			return "End time cannot be earlier than from_time time, please correct it";
		} else if (toHours==fromHours && toMinutes==0) {
				return "Reservation cannot be made for less than 15 minutes, please correct it";
		} else if (fromHours<toHours) {
			if((toHours-fromHours == 3 && toMinutes!=0) || toHours-fromHours > 3) {
					return "Reservation cannot be made for more than 3 hours, please correct it";
			}
		}
		return "";
	}
	
	private String validateReservationTo(String from, String to) {
		
		if (to.equals("") || to.equals(null)) 
			return "Please enter reservation end time";
		if (from.equals("") || from.equals(null)) 
			return "";
		String currentTime = getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		int currentHours = Integer.parseInt(currentTimeArray[0]);
		int currentMinutes = Integer.parseInt(currentTimeArray[1]);
		
		int toHours;
		int toMinutes;
	
		String[] toArray = to.split(":");
		if(!(toArray[0].length()==2))
			return "Please enter time in the format HH:mm";
		if(!(toArray[1].length()==2))
			return "Please enter time in the format HH:mm";
		try {
			toHours = Integer.parseInt(toArray[0]);
			toMinutes = Integer.parseInt(toArray[1]);
		}catch (NumberFormatException e){
			return "Please enter time in format HH:mm";
		}
		if(toHours>23){
			return "Please enter time in format HH:mm, HH from_time 00 to_time 23";
		} else if(toHours == 23 && toMinutes > 45)
			return "Please enter time in format HH:mm, valid values for mm are 00 or 15 or 30 or 45";
		if(!(toMinutes == 0 || toMinutes==15 || toMinutes==30 || toMinutes== 45)){
			return "Please enter time in the format HH:mm, valid values for mm are 00 or 15 or 30 or 45";
		}
		if (toHours<currentHours || (toHours==currentHours && toMinutes<currentMinutes))
			return "End time cannot be earlier than current time, please correct it";
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
		
		if(!(fromArray[0].length()==2))
			return "Please enter time in the format HH:mm";
		if(!(fromArray[1].length()==2))
			return "Please enter time in the format HH:mm";
		int fromHours;
		int fromMinutes;
		
		try {
			fromHours = Integer.parseInt(fromArray[0]);
			fromMinutes = Integer.parseInt(fromArray[1]);
		}catch (NumberFormatException e){
			return "Please enter time in format HH:mm";
		}
		
		if(fromHours>23){
			return "Please enter start time in format HH:00, HH from_time 00 to_time 23";
		}
		if(fromMinutes != 0){
			return "Please enter start time in format HH:00, HH from_time 00 to_time 23";
		}
		if (fromHours<currentHours)
			return "Start time cannot be earlier than current time, please correct it";
		else if (fromHours==currentHours) {
			if (fromMinutes<currentMinutes)
				return "Start time cannot be earlier than current time, please correct it";
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
