package parkingManagement.model;

public class UnavailableSpot {
 private String parkingName;
 private int spot_no;
 private String type;
public String getParkingName() {
	return parkingName;
}
public void setParkingName(String parkingName) {
	this.parkingName = parkingName;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getSpot_no() {
	return spot_no;
}
public void setSpot_no(int spot_no) {
	this.spot_no = spot_no;
}
}
