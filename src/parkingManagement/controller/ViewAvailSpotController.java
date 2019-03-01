package parkingManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import parkingManagement.model.*;

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
		if(action.equals("spotdetails"))
		{
			session.removeAttribute("spotdetailserror");
			ArrayList<String> parkingareanames = new ArrayList<String>();
			parkingareanames = parkDao.getparkingareaname();
			session.setAttribute("parkingAreaNames", parkingareanames);
			session.removeAttribute("spotdetailslist");
			response.sendRedirect("viewspotdetails.jsp");	
			
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
		ViewAvailSpotErrorMsgs noavailableerror = new ViewAvailSpotErrorMsgs();
		UnavailableSpot unavail = new UnavailableSpot();
		unavail.ValidateAvailSpot(fromTime, toTime, noavailableerror);
		if(noavailableerror.getErrorMsg().equals(""))
		{
			int avilable_spots=parkDao.number_avail_spot(parkingareaname, fromTime, toTime,permit_type);
			session.setAttribute("avail_spots",avilable_spots );
			response.sendRedirect("view_avail_spot.jsp");
		}
		else
		{
			session.setAttribute("modes", "noavail");
			session.setAttribute("noavailerror", noavailableerror);
			response.sendRedirect("view_avail_spot.jsp");
		}
		
		}
		
		if(action.equals("makeunavailable"))
		{
			UnavailableSpotErrorMsgs unavailspotError = new UnavailableSpotErrorMsgs();
			UnavailableSpot unavailspot = new UnavailableSpot();
			unavailspot.setParkingName(request.getParameter("parkingareaname"));
			unavailspot.setType(request.getParameter("type"));
			unavailspot.setSpot_no(request.getParameter("spotno"));
			unavailspot.ValidateSpot(unavailspot,unavailspotError);
			if(unavailspotError.getUspotErrMsg().equals(""))
			{
				parkDao.setParkspotunavail(unavailspot);
				session.removeAttribute("makespotunavailerror");
				response.sendRedirect("viewAvailSpotController?action=numberavailable");
			}
			else
			{
				session.setAttribute("modes", "makeunavail");
				session.setAttribute("makespotunavailerror", unavailspotError);
				response.sendRedirect("view_avail_spot.jsp");
			}
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
		unavail.setSpot_no(request.getParameter("spot_num"));
		parkDao.remove_unavailable(unavail);
		response.sendRedirect("viewAvailSpotController?action=numberavailable");
		}
		
		if(action.equals("searchspotdetails"))
		{
		UnavailableSpot unavail = new UnavailableSpot();
		ArrayList<Reservation> reservlist = new ArrayList<Reservation>();
		String parkname = request.getParameter("parkingareaname");
		String type = request.getParameter("type");
		String spotdetailError = unavail.validateSpotnofordetails(request.getParameter("spotno"));
		if(spotdetailError.equals(""))
		{
			int spotno = Integer.parseInt(request.getParameter("spotno"));
			if((parkDao.check_spot_avail(parkname, type, spotno)) == 1)
				{
				session.removeAttribute("spotdetailserror");
				session.setAttribute("isavailable", 1);
				response.sendRedirect("viewspotdetails.jsp");
				}
				else
				{
					reservlist = parkDao.fetch_reservation_details(parkname, type, spotno);
					session.setAttribute("spotdetailslist", reservlist);
					session.setAttribute("isavailable", 0);
					response.sendRedirect("viewspotdetails.jsp");
				}
		}
		else{
			session.setAttribute("spotdetailserror", spotdetailError);
			response.sendRedirect("viewspotdetails.jsp");
			
		}
	}
}

}
