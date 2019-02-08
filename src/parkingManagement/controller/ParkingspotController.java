package parkingManagement.controller;

import parkingManagement.model.*;
import java.util.ArrayList;

import parkingManagement.data.ParkingspotDao;
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
				
				ArrayList<ParkingArea> parkingspotList = new ArrayList<ParkingArea>();
				parkingspotList=parkingSpotDao.getParkingAreaList(parkingarea);
				session.setAttribute("parkingspots", parkingspotList);	
				
				
			}
			getServletContext().getRequestDispatcher("/searchparkingspot.jsp").forward(request, response);
		}
	}
}
