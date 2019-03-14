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
