package parkingManagement.model;

import static org.junit.Assert.*;

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
}
