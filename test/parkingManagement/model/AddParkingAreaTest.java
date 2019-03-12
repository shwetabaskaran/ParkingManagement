package parkingManagement.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
@RunWith(JUnitParamsRunner.class)

public class AddParkingAreaTest {
	@FileParameters("test/AddParkingAreaTestData.csv")
	@Test
	public void validateAddParkingAreaTest(int testno,String parkingAreaname,String Floor,String Capacity,String type,String newname,String parkingAreaErr,String FloorErr,String CapacityErr,String errMsg) {
	AddParkingArea addpark = new AddParkingArea();
	ParkingAreaErrorMsgs addparkErr = new ParkingAreaErrorMsgs();
	addpark.setParkingName(parkingAreaname);
	addpark.setCapacity(Capacity);
	addpark.setFloor(Floor);
	addpark.setType(type);
	addpark.validateNewParkingArea(addpark, addparkErr);
	assertEquals(parkingAreaErr,addparkErr.getParkingareaNameError());
	assertEquals(FloorErr,addparkErr.getFloorError());
	assertEquals(CapacityErr,addparkErr.getCapacityError());
	assertEquals(errMsg,addparkErr.getErrormsg());
	assertEquals(parkingAreaErr,addpark.validateParkingNameforChangename(parkingAreaname,addparkErr));
	}

}
