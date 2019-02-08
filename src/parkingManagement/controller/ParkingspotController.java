package parkingManagement.controller;

import parkingManagement.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parkingManagement.data.ParkingspotDao;
import parkingManagement.data.ReservationDao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/parkingspotController")
public class ParkingspotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		session.setAttribute("parkingspots", null);	
		String action = request.getParameter("action");
		
		ParkingspotDao parkingSpotDao = new ParkingspotDao();
		ReservationDao reservationDao = new ReservationDao();
		
		ParkingArea parkingarea = new ParkingArea();
		parkingarea.setParkingarea_name(request.getParameter("parkingarea"));
		parkingarea.setParkingtype(request.getParameter("parkingtype"));
		String from = request.getParameter("reservationfrom");
		String to = request.getParameter("reservationto");
//		List parkingspots
		if (action.equalsIgnoreCase("searchparkingspot")) {
			SearchParkingspotErrorMsgs errorMsgs = new SearchParkingspotErrorMsgs();
			parkingarea.ValidateSearchParkingSpot(parkingarea, errorMsgs, from, to);
			
			session.setAttribute("parkingArea",parkingarea);
			session.setAttribute("reservationfromtime",from);
			session.setAttribute("reservationtotime",to);
			session.setAttribute("errorMsgs",errorMsgs);
			
			if (errorMsgs.getErrorMsg().equals("")) {
					 //save user if no errors
				session.removeAttribute("parkingArea");
				session.removeAttribute("reservationStatus");
				session.removeAttribute("reservationfromtime");
				session.removeAttribute("reservationtotime");
				
				ArrayList<ParkingArea> parkingAreaList = new ArrayList<ParkingArea>();
				ArrayList<Integer> parkingAreaIdList = new ArrayList<Integer>();
				parkingAreaList=parkingSpotDao.getParkingAreaList(parkingarea);
				for(ParkingArea pa : parkingAreaList){
					parkingAreaIdList.add(pa.getParkingarea_id());
				}
				Map<Integer, Integer> parkingcountMap = new HashMap<Integer, Integer>();
				parkingcountMap = reservationDao.getParkingAreaCountList(parkingAreaIdList);
				
				Map<Integer, Integer> availabilitycountMap = new HashMap<Integer, Integer>();
				int reserved = 0;
				for(ParkingArea pa : parkingAreaList){
					reserved = parkingcountMap.get(pa.getParkingarea_id());
					availabilitycountMap.put(pa.getParkingarea_id(), (pa.getCapacity()-reserved));
				}
				session.setAttribute("parkingspots", parkingAreaList);
				session.setAttribute("availabilitymap", availabilitycountMap);			
				
			}
			getServletContext().getRequestDispatcher("/searchparkingspot.jsp").forward(request, response);
		}
	}
}
