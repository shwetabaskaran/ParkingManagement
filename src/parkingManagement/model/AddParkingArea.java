package parkingManagement.model;
import parkingManagement.data.*;
public class AddParkingArea {
	
	private String ParkingName;
	private String Floor;
	private String Capacity;
	private String type;
	
	public String getParkingName() {
		return ParkingName;
	}
	public void setParkingName(String parkingName) {
		ParkingName = parkingName;
	}
	public String getFloor() {
		return Floor;
	}
	public void setFloor(String floor) {
		Floor = floor;
	}
	public String getCapacity() {
		return Capacity;
	}
	public void setCapacity(String capacity) {
		Capacity = capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void validateNewParkingArea(AddParkingArea addpark,ParkingAreaErrorMsgs addparkerror)
	 {
		 addparkerror.setParkingareaNameError(validateParkingName(addpark.getParkingName(),addpark.getType(),addpark.getFloor()));
		 addparkerror.setCapacityError(validateCapacity(addpark.getCapacity()));
		 addparkerror.setFloorError(validateFloor(addpark.getFloor()));
		 addparkerror.setErrormsg();
	}
	 
	 
	public String validateParkingName(String parkname,String type,String Floor){
		boolean hasChar = false;
		boolean hasNumber = false;
		ParkingspotDao check = new ParkingspotDao();
		String result="";
		char[] array=parkname.toCharArray();
		if (parkname.equals(""))
			result= "Parking Area Name should not be empty";
		else if (check.finduniqueparkname(parkname,type,Floor))
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
