package parkingManagement.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import parkingManagement.data.ParkingspotDao;

public class ParkingArea implements Serializable {
	
	
	public ParkingArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingArea(int parkingarea_id, String parkingarea_name, String parkingtype, int capacity, int floor) {
		this.parkingarea_id = parkingarea_id;
		this.parkingarea_name = parkingarea_name;
		this.parkingtype = parkingtype;
		this.capacity = capacity;
		this.floor = floor;
	}

	private static final long serialVersionUID = 1L;
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

	public void ValidateSearchParkingSpot(SearchParkingspotErrorMsgs errorMsgs,
			String from, String to) {
		errorMsgs.setParkingAreaError(validateParkingArea(getParkingarea_name()));
		errorMsgs.setParkingTypeError(validateParkingType(getParkingtype()));
		errorMsgs.setReservationFromError(validateReservationFrom(from));
		errorMsgs.setReservationToError(validateReservationTo(from, to));
		
		errorMsgs.setErrorMsg();
	}
	
	public String validateParkingArea(String parkingArea) {
		if (parkingArea.equals("Select")) 
			return "Please select a parking area";
		return "";
	}
	
	public String validateParkingType(String parkingType) {
		
		if (parkingType.equals("Select")) 
			return "Please select a parking type";
		return "";
	}
	
	public String validateFromAndToTime(int fromHours, int toHours, int toMinutes) {
				
		if (toHours<fromHours) {
			return "End time cannot be earlier than from_time time. Please correct it";
		} else if (toHours==fromHours && toMinutes==0) {
				return "Reservation cannot be made for less than 15 minutes. Please correct it";
		} else if((toHours-fromHours == 3 && toMinutes!=0) || toHours-fromHours > 3) {
				return "Reservation cannot be made for more than 3 hours. Please correct it";
		}
		return "";
	}
	
	private String validateReservationTo(String from, String to) {
		
		if (to.equals("")) 
			return "Please enter reservation end time";
		if (from.equals("")) 
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
		
		String[] fromTimeArray = from.split(":");
		if(!(fromTimeArray[0].length()==2))
				return "";
		int fromHours;
		try {
			fromHours = Integer.parseInt(fromTimeArray[0]);
		}catch (NumberFormatException e){
			return "";
		}
		
		if(toHours>23){
			return "Please enter time in format HH:mm. HH from_time 00 to_time 23";
		} else if(toHours == 23 && toMinutes > 45)
			return "Please enter time in format HH:mm. Valid values for mm are 00 or 15 or 30 or 45";
		if(!(toMinutes == 0 || toMinutes==15 || toMinutes==30 || toMinutes== 45)){
			return "Please enter time in the format HH:mm. Valid values for mm are 00 or 15 or 30 or 45";
		}
		if (toHours<currentHours || (toHours==currentHours && toMinutes<currentMinutes))
			return "End time cannot be earlier than current time. Please correct it";
		String compare = validateFromAndToTime(fromHours, toHours, toMinutes);
		if(!compare.equals(""))
			return compare;
		
		return "";
	}
	private String validateReservationFrom(String from) {
		
		if (from.equals("")) 
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
			return "Please enter start time in format HH:00. HH from_time 00 to_time 23";
		}
		if(fromMinutes != 0){
			return "Please enter start time in format HH:00. HH from_time 00 to_time 23";
		}
		if (fromHours<currentHours)
			return "Start time cannot be earlier than current time. Please correct it";
		else if (fromHours==currentHours) {
			if (fromMinutes<currentMinutes)
				return "Start time cannot be earlier than current time. Please correct it";
		}
		return "";
	}

	String getCurrentTimeUsingDate() {
	    Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    return dateFormat.format(date);
	}
	
	public void validateNewParkingArea(ParkingArea addpark,ParkingAreaErrorMsgs addparkerror)
	 {
		 addparkerror.setParkingareaNameError(validateParkingName(addpark.getParkingarea_name(),
				 addpark.getParkingtype(), addpark.getFloor()+""));
		 addparkerror.setCapacityError(validateCapacity(addpark.getCapacity()+""));
		 addparkerror.setFloorError(validateFloor(addpark.getFloor()+""));
		 addparkerror.setErrormsg();
	}
	 
	 
	public String validateParkingName(String parkname, String type, String Floor){
		boolean hasChar = false;
		boolean hasNumber = false;
		ParkingspotDao check = new ParkingspotDao();
		String result="";
		char[] array=parkname.toCharArray();
		if (parkname.equals(""))
			result= "Parking Area Name should not be empty";
		else if (check.finduniqueparkname(parkname, type, Floor))
					result="Parking Area Name alredy found";
		else if(!stringSize(parkname,3,16))
			result ="Parking Area name should be between 3 and 16 characters in length";
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		if(hasChar || hasNumber) {
			result = "Parking area name cannot contain special characters or numeric characters";
		}
		return result;
	 }
	public String validateParkingNameforChangename(String parkname,ParkingAreaErrorMsgs addparkerror){
		boolean hasChar = false;
		boolean hasNumber = false;
		ParkingspotDao check = new ParkingspotDao();
		String result="";
		char[] array=parkname.toCharArray();
		if (parkname.equals(""))
			result= "Parking Area Name should not be empty";
		else if (check.finduniqueparknameforchangename(parkname))
					result="Parking Area Name alredy found";
		else if(!stringSize(parkname,3,16))
			result ="Parking Area name should be between 3 and 16 characters in length";
		char[] characters = {'~', '!', '@', '#','$','%','^','&','*','(',')','_','-','+','=','{','}','[',']',':',';','"','<','>','?','/','\\'};
		for(int i=0;i<characters.length;i++) {
			char a = characters[i];
			for(char b: array) {
				if (a == b){
					hasChar = true;
				}
				if(Character.isDigit(b)) {
					hasNumber = true;
				}
			}
		}
		if(hasChar || hasNumber) {
			result = "Parking area name cannot contain special characters or numeric characters";
		}
		if(!(result.equals("")))
			addparkerror.setParkingareaNameError(result);
		return result;
	}
	 private String validateFloor(String Floor) {
			String result="";
			if(isTextAnInteger(Floor) == false) {
				result = "Please Enter a valid Floor number";
			}
			
			return result;
		}
	 
	 public String validateCapacity(String capacity) {
			String result="";
			if(isTextAnInteger(capacity) == false) {
				result = "Please Enter a valid Capacity";
			}
			
			return result;
		}
	 private boolean stringSize(String string, int min, int max) {
			return string.length()>=min && string.length()<=max;
		}
	 private boolean isTextAnInteger (String string) {
	        boolean result;
			try
	        {
	            Long.parseLong(string);
	            result=true;
	        } 
	        catch (NumberFormatException e) 
	        {
	            result=false;
	        }
			return result;
		}
}
