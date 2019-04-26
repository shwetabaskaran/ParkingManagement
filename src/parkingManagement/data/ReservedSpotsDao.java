package parkingManagement.data;

import parkingManagement.util.SQLConnection;
import parkingManagement.model.ReservedSpots;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservedSpotsDao {
	static SQLConnection sqlconnection = SQLConnection.getInstance();
	Connection conn = SQLConnection.getDBConnection();
	Statement stmt = null;
	
	public List<ReservedSpots> getReservationsForCancellation(String username)
	{
		List<ReservedSpots> ReservationsList = new ArrayList<ReservedSpots>();
		try {
			stmt = conn.createStatement();
			
			java.util.Date utilDate = new java.util.Date();
			Date today = new Date(utilDate.getTime());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(utilDate);
			cal.add(Calendar.HOUR, +1);

			String timeStamp = new SimpleDateFormat("HH:mm").format(cal.getTime());
			
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			Time from = null;
			try {
				from = new Time(formatter.parse(timeStamp).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String queryString = "select * from `reservation` natural join parkingarea where parkingarea_id = parkingarea_id and username = '"+username+"' and reservation_date='"+today+"' and from_time>'"+from+"'";
			System.out.println("Query strin gis : "+queryString);
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null) {
				while(rs.next()){
					ReservedSpots reservedspot = new ReservedSpots();
					reservedspot.setReservation_id(rs.getInt("reservation_id"));
					reservedspot.setUsername(rs.getString("username"));
					reservedspot.setParkingarea_id(rs.getInt("parkingarea_id"));
					reservedspot.setParkingarea_name(rs.getString("parkingarea_name"));
					reservedspot.setParkingtype(rs.getString("parkingtype"));
					reservedspot.setReservation_date(rs.getDate("reservation_date"));
					reservedspot.setFrom_time(rs.getTime("from_time"));
					reservedspot.setTo_time(rs.getTime("to_time"));
					reservedspot.setParkingslot_no(rs.getInt("parkingslot_no"));
					reservedspot.setFloor(rs.getInt("floor"));
					if(rs.getBoolean("cart")==true)
						reservedspot.setCart("Yes");
					else
						reservedspot.setCart("No");	
					if(rs.getBoolean("camera")==true)
						reservedspot.setCamera("Yes");
					else
						reservedspot.setCamera("No");
					if(rs.getBoolean("history")==true)
						reservedspot.setHistory("Yes");
					else
						reservedspot.setHistory("No");
					ReservationsList.add(reservedspot);
				}
			} else {
				ReservationsList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return ReservationsList;
	}
	
	public void deleteReservation(int reservationId)
	{
		try {
			stmt = conn.createStatement();
			
			String queryString = "delete from reservation where reservation_id='"+reservationId+"'";
			stmt.executeUpdate(queryString);
			conn.commit();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
	}
	
	public List<ReservedSpots> viewReservedSpots(String username)
	{
			List<ReservedSpots> ReservationsList = new ArrayList<ReservedSpots>();
		try {
			stmt = conn.createStatement();
			String queryString = "select * from reservation natural join parkingarea where parkingarea_id = parkingarea_id and username = '"+username+"'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				ReservedSpots reservedspot = new ReservedSpots();
				reservedspot.setReservation_id(rs.getInt("reservation_id"));
				reservedspot.setUsername(rs.getString("username"));
				reservedspot.setParkingarea_id(rs.getInt("parkingarea_id"));
				reservedspot.setParkingarea_name(rs.getString("parkingarea_name"));
				reservedspot.setParkingtype(rs.getString("parkingtype"));
				reservedspot.setReservation_date(rs.getDate("reservation_date"));
				reservedspot.setFrom_time(rs.getTime("from_time"));
				reservedspot.setTo_time(rs.getTime("to_time"));
				reservedspot.setParkingslot_no(rs.getInt("parkingslot_no"));
				reservedspot.setFloor(rs.getInt("floor"));
				if(rs.getBoolean("cart")==true)
					reservedspot.setCart("Yes");
				else
					reservedspot.setCart("No");	
				if(rs.getBoolean("camera")==true)
					reservedspot.setCamera("Yes");
				else
					reservedspot.setCamera("No");
				if(rs.getBoolean("history")==true)
					reservedspot.setHistory("Yes");
				else
					reservedspot.setHistory("No");
				ReservationsList.add(reservedspot);
			}
		}
			else
			{
				ReservationsList = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return ReservationsList;
}
	
	public List<ReservedSpots> viewReservationStatus(String username)
	{
			List<ReservedSpots> ReservationStatusList = new ArrayList<ReservedSpots>();
		try {
			stmt = conn.createStatement();
			
			java.util.Date utilDate = new java.util.Date();
			Date today = new Date(utilDate.getTime());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(utilDate);

			String timeStamp = new SimpleDateFormat("HH:mm").format(cal.getTime());
			
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			Time from = null;
			try {
				from = new Time(formatter.parse(timeStamp).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String queryString = "select * from reservation natural join parkingarea where parkingarea_id = parkingarea_id and username = '"+username+"' and reservation_date='"+today+"' and from_time>'"+from+"'";
			ResultSet rs = stmt.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
						ReservedSpots reservedspot = new ReservedSpots();
						reservedspot.setReservation_id(rs.getInt("reservation_id"));
						reservedspot.setParkingarea_id(rs.getInt("parkingarea_id"));
						reservedspot.setParkingarea_name(rs.getString("parkingarea_name"));
						reservedspot.setParkingtype(rs.getString("parkingtype"));
						reservedspot.setReservation_date(rs.getDate("reservation_date"));
						reservedspot.setFrom_time(rs.getTime("from_time"));
						reservedspot.setTo_time(rs.getTime("to_time"));
						reservedspot.setParkingslot_no(rs.getInt("parkingslot_no"));
						reservedspot.setFloor(rs.getInt("floor"));
						if(rs.getBoolean("cart")==true)
							reservedspot.setCart("Yes");
						else
							reservedspot.setCart("No");	
						if(rs.getBoolean("camera")==true)
							reservedspot.setCamera("Yes");
						else
							reservedspot.setCamera("No");
						if(rs.getBoolean("history")==true)
							reservedspot.setHistory("Yes");
						else
							reservedspot.setHistory("No");
//						reservedspot.setReservation_status("Confirmed");
//						reservedspot.setPayment_confirmation("Paid");
						if(getStatus(reservedspot.getParkingslot_no(),reservedspot.getParkingarea_id())=="Revoked")
							reservedspot.setReservation_status("Revoked");
						else
							reservedspot.setReservation_status("Active");
						if(getPaymentConfirm(reservedspot.getParkingslot_no(),reservedspot.getParkingarea_id())=="Refunded")
							reservedspot.setPayment_confirmation("Refunded");
						else
							reservedspot.setPayment_confirmation("Paid");
						ReservationStatusList.add(reservedspot);
			}
		}
			else
			{
				ReservationStatusList = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return ReservationStatusList;
}
	
	public String getStatus(int slot_number,int pid){
		Statement s1;
		String status = "";
		try {
			s1 = conn.createStatement();
			String queryString = "select spot_no from unavailablespots where spot_no = '"+slot_number+"' and parking_id = '"+pid+"'";
			ResultSet rs = s1.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				status = "Revoked";
				}
			}
			else
			{
				status = "Active";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			try {
////				if(conn!=null)
////					conn.close();
//				if(s1!=null)
//					s1.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			};
//		}
		return status;
	}
	
	public String getPaymentConfirm(int slot_number,int pid){
		Statement s2;
		String payment = "";
		try {
			s2 = conn.createStatement();
			String queryString = "select spot_no from unavailablespots where spot_no = '"+slot_number+"' and parking_id = '"+pid+"'";
			ResultSet rs = s2.executeQuery(queryString);
			if(rs != null)
			{
				while(rs.next()){
				payment = "Refunded";
			}
		}
			else
			{
				payment = "Paid";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				if(conn!=null)
//					conn.close();
//				if(stmt!=null)
//					stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			};
//		}
		return payment;
	}
}