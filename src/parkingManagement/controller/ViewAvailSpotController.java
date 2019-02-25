package parkingManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import parkingManagement.model.UnavailableSpot;

import parkingManagement.data.ParkingspotDao;

@WebServlet("/viewAvailSpotController")
public class ViewAvailSpotController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		ParkingspotDao parkDao = new ParkingspotDao();
		HttpSession session = request.getSession();
		if(action.equals("numberavailable")){
		ArrayList<String> parkingareanames = new ArrayList<String>();
		parkingareanames = parkDao.getparkingareaname();
		
		session.removeAttribute("avail_spots");
		session.removeAttribute("unavailable_list");
		session.setAttribute("parkingAreaNames", parkingareanames);
		response.sendRedirect("view_avail_spot.jsp");
		}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action");
		ParkingspotDao parkDao = new ParkingspotDao();
		HttpSession session= request.getSession();
		
		if(action.equals("noavailspots")){
		String parkingareaname = request.getParameter("parkingareaname");
		String fromTime = request.getParameter("fromtime");
		String toTime = request.getParameter("totime");
		String permit_type=request.getParameter("permit_type");
		int avilable_spots=parkDao.number_avail_spot(parkingareaname, fromTime, toTime,permit_type);
		session.setAttribute("avail_spots",avilable_spots );
		response.sendRedirect("view_avail_spot.jsp");
		}
		
		if(action.equals("makeunavailable"))
		{
			UnavailableSpot unavailspot = new UnavailableSpot();
			unavailspot.setParkingName(request.getParameter("parkingareaname"));
			unavailspot.setType(request.getParameter("type"));
			unavailspot.setSpot_no(Integer.parseInt(request.getParameter("spotno")));
			parkDao.setParkspotunavail(unavailspot);
			response.sendRedirect("viewAvailSpotController?action=numberavailable");
			
		}
		if(action.equals("listavailable"))
		{
		ArrayList<UnavailableSpot> unlist = new ArrayList<UnavailableSpot>();
		unlist = parkDao.fetch_unavail_spots();
		session.setAttribute("unavailable_list", unlist);
		response.sendRedirect("view_avail_spot.jsp");
			}
		if(action.equals("removeunavail"))
		{
		UnavailableSpot unavail = new UnavailableSpot();
		unavail.setParkingName(request.getParameter("parking_name"));
		unavail.setType(request.getParameter("parking_type"));
		unavail.setSpot_no(Integer.parseInt(request.getParameter("spot_num")));
		parkDao.remove_unavailable(unavail);
		response.sendRedirect("viewAvailSpotController?action=numberavailable");
		}
	}

}
