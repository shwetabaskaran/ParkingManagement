package parkingManagement.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ParkingAreaTest {
	
	@Test
	@FileParameters("test/ParkingAreaTestData.csv")
	public void ValidateSearchParkingSpotTest(int testno, String parkingarea_name, String parkingtype, 
			String from, String to, String currentTime, String error, String parkingAreaError, String parkingTypeError, 
			String reservationFromError, String reservationToError) {
		
		ParkingArea mock = EasyMock.createMockBuilder(ParkingArea.class)
		        .withConstructor(-1, parkingarea_name, parkingtype, -1, -1)
		        .addMockedMethod("getCurrentTimeUsingDate")
		        .createMock();
		EasyMock.expect(mock.getCurrentTimeUsingDate()).andStubReturn(currentTime);
		EasyMock.replay(mock);
		SearchParkingspotErrorMsgs errorMsgs = new SearchParkingspotErrorMsgs();
		mock.ValidateSearchParkingSpot(errorMsgs, from, to);
		assertEquals(parkingAreaError,errorMsgs.getParkingAreaError());
		assertEquals(error,errorMsgs.getErrorMsg());
		assertEquals(parkingTypeError,errorMsgs.getParkingTypeError());
		assertEquals(reservationFromError,errorMsgs.getReservationFromError());
		assertEquals(reservationToError,errorMsgs.getReservationToError());
	}
		
	@Test
	@FileParameters("test/AddParkingAreaTestData.csv")
	public void validateAddParkingAreaTest(int testno,String parkingAreaname,String Floor,String Capacity,String type,String newname,String parkingAreaErr,String FloorErr,String CapacityErr,String errMsg) {
		ParkingArea parkingArea = new ParkingArea();
		ParkingAreaErrorMsgs addparkErr = new ParkingAreaErrorMsgs();
		parkingArea.setParkingarea_name(parkingAreaname);
		parkingArea.setCapacity(Integer.parseInt(Capacity));
		parkingArea.setFloor(Integer.parseInt(Floor));
		parkingArea.setParkingtype(type);
		parkingArea.validateNewParkingArea(parkingArea, addparkErr);
		assertEquals(parkingAreaErr,addparkErr.getParkingareaNameError());
		assertEquals(FloorErr,addparkErr.getFloorError());
		assertEquals(CapacityErr,addparkErr.getCapacityError());
		assertEquals(errMsg,addparkErr.getErrormsg());
		assertEquals(parkingAreaErr,parkingArea.validateParkingNameforChangename(parkingAreaname,addparkErr));
	}
	
	@Test
	public void getCurrentTimeUsingDateTest() {
		
		ParkingArea parkingArea = new ParkingArea();
		String currentTime = parkingArea.getCurrentTimeUsingDate();
		String[] currentTimeArray = currentTime.split(":");
		
		Date date = new Date();
	    String strDateFormat = "HH:mm:ss";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String testTime = dateFormat.format(date);
	    
	    String[] testTimeArray = testTime.split(":");
	    
		assertEquals(Integer.parseInt(testTimeArray[0]),Integer.parseInt(currentTimeArray[0]));
	}
}
