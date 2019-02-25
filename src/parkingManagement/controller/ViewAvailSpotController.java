package parkingManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

import parkingManagement.data.ParkingspotDao;

@WebServlet("/viewAvailSpotController")
public class ViewAvailSpotController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ParkingspotDao parkDao = new ParkingspotDao();
		ArrayList<String> parkingareanames = new ArrayList<String>();
		parkingareanames = parkDao.getparkingareaname();
		HttpSession session = request.getSession();
		session.removeAttribute("avail_spots");
		session.setAttribute("parkingAreaNames", parkingareanames);
		response.sendRedirect("view_avail_spot.jsp");
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String parkingareaname = request.getParameter("parkingareaname");
		String fromTime = request.getParameter("fromtime");
		String toTime = request.getParameter("totime");
		String permit_type=request.getParameter("permit_type");
		ParkingspotDao parkDao = new ParkingspotDao();
		int avilable_spots=parkDao.number_avail_spot(parkingareaname, fromTime, toTime,permit_type);
		HttpSession session= request.getSession();
		session.setAttribute("avail_spots",avilable_spots );
		response.sendRedirect("view_avail_spot.jsp");
	}

}
