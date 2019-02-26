package parkingManagement.controller;

import parkingManagement.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parkingManagement.data.ParkingspotDao;
import parkingManagement.data.ReservationDao;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		
//		List parkingspots
		if (action.equalsIgnoreCase("searchparkingspot")) {
			String[] cart = request.getParameterValues("selectedcart");
			String[] camera = request.getParameterValues("selectedcamera"); 
			String[] history = request.getParameterValues("selectedhistory");	

			String selectedCart ="unchecked";
			String selectedoptions = "";
		    if(cart !=null && cart.length > 0){//If checkbox is checked then assign it with true or 1       
		    	selectedCart="checked";  
		    	selectedoptions = selectedoptions + "Cart, ";
		    }

		    String selectedCamera ="unchecked";
		    if(camera !=null && camera.length > 0){//If checkbox is checked then assign it with true or 1       
		    	selectedCamera="checked";  
		    	selectedoptions = selectedoptions + "Camera, ";
		    }

		    String selectedHistory ="unchecked";
		    if(history !=null && history.length > 0){//If checkbox is checked then assign it with true or 1       
		    	selectedHistory="checked";  
		    	selectedoptions = selectedoptions + "History";
		    }
		    System.out.println("Selected options before request  are : "+selectedoptions);
		    if(request.getParameter(selectedoptions)!=null){
		    	selectedoptions = request.getParameter(selectedoptions);
		    }
		    session.setAttribute("selectedoptions", selectedoptions);
			parkingarea.setParkingarea_name(request.getParameter("parkingarea"));
			parkingarea.setParkingtype(request.getParameter("parkingtype"));
			java.util.Date utilDate = new java.util.Date();
			Date today = new Date(utilDate.getTime());
			
			Time from = null;
			Time to = null;
			DateFormat formatter = new SimpleDateFormat("HH:mm");
			try {
				from = new Time(formatter.parse(request.getParameter("reservationfrom")).getTime());
				to = new Time(formatter.parse(request.getParameter("reservationto")).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String rawFrom = request.getParameter("reservationfrom");
			String rawTo = request.getParameter("reservationto");
			
			SearchParkingspotErrorMsgs errorMsgs = new SearchParkingspotErrorMsgs();
			parkingarea.ValidateSearchParkingSpot(parkingarea, errorMsgs, rawFrom, rawTo);
			
			session.setAttribute("parkingArea",parkingarea);
			session.setAttribute("reservationfromtime",rawFrom);
			session.setAttribute("reservationtotime",rawTo);
			session.setAttribute("errorMsgs",errorMsgs);
			
			if (errorMsgs.getErrorMsg().equals("")) {
				
				ArrayList<ParkingArea> parkingAreaList = new ArrayList<ParkingArea>();
				ArrayList<Integer> parkingAreaIdList = new ArrayList<Integer>();
				parkingAreaList=parkingSpotDao.getParkingAreaList(parkingarea);
				for(ParkingArea pa : parkingAreaList){
					parkingAreaIdList.add(pa.getParkingarea_id());
				}
				Map<Integer, Integer> parkingsReservedcountMap = new HashMap<Integer, Integer>();
				Map<Integer, Integer> parkingsUnavailablecountMap = new HashMap<Integer, Integer>();
				parkingsReservedcountMap = reservationDao.getParkingAreaCountList(parkingAreaIdList, from, to, today);
				parkingsUnavailablecountMap = parkingSpotDao.getUnAvailableParkingsCountList(parkingAreaIdList);
				
				Map<Integer, Integer> availabilitycountMap = new HashMap<Integer, Integer>();
				int reserved = 0;
				int unavailable = 0;
				for(ParkingArea pa : parkingAreaList){
					reserved = parkingsReservedcountMap.get(pa.getParkingarea_id());
					unavailable = parkingsUnavailablecountMap.get(pa.getParkingarea_id());
					availabilitycountMap.put(pa.getParkingarea_id(), (pa.getCapacity()-reserved-unavailable));
				}
				session.setAttribute("parkingspots", parkingAreaList);
				session.setAttribute("totalcost", request.getParameter("totalcost"));
				System.out.println("total cost : "+request.getParameter("totalcost"));
				session.setAttribute("selectedcart", selectedCart);
				session.setAttribute("selectedcamera", selectedCamera);
				session.setAttribute("selectedhistory", selectedHistory);
				session.setAttribute("availabilitymap", availabilitycountMap);			
				
			}
			getServletContext().getRequestDispatcher("/searchparkingspot.jsp").forward(request, response);
		}
		
		if(action.equals("modifyparkingarea")){
			ParkingspotDao parkDao = new ParkingspotDao();
			ArrayList<String> parkingareanames = new ArrayList<String>();
			parkingareanames = parkDao.getparkingareaname();
			session.setAttribute("modifyparkingAreaNames", parkingareanames);
			session.setAttribute("onloads", 0);
			session.removeAttribute("parkinfo");
			response.sendRedirect("parkingarea.jsp");
			
		}
		if(action.equals("searchparkingspotload")){
			ParkingspotDao parkDao = new ParkingspotDao();
			ArrayList<String> parkingareanames = new ArrayList<String>();
			parkingareanames = parkDao.getparkingareaname();
			session.setAttribute("modifyparkingAreaNames", parkingareanames);
			session.setAttribute("onloads", 0);
			session.removeAttribute("parkinfo");
			response.sendRedirect("searchparkingspot.jsp");
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ParkingArea newParking = new ParkingArea();
		ParkingspotDao parkDao = new ParkingspotDao();
		HttpSession session = request.getSession();
		if(action.equals("addparking"))
		{
			newParking.setParkingarea_name(request.getParameter("park_name"));
			newParking.setFloor(Integer.parseInt(request.getParameter("park_floor")));
			newParking.setCapacity(Integer.parseInt(request.getParameter("park_cap")));
			newParking.setParkingtype(request.getParameter("park_type"));
			parkDao.addParkingArea(newParking);
			response.sendRedirect("parkingspotController?action=modifyparkingarea");
			
		}
		if(action.equals("modifyparkingareas"))
		{
			String parkname = request.getParameter("parkingareaname");
			ArrayList<ParkingArea> parklist = new ArrayList<ParkingArea>();
			parklist = parkDao.fetch_parking_details(parkname);
			session.setAttribute("parkinfo",parklist);
			session.setAttribute("onloads", 1);
			response.sendRedirect("parkingarea.jsp");
		}
		if(action.equals("updateparkingarea"))
		{
			ParkingArea parkarea = new ParkingArea();
			parkarea.setParkingarea_name(request.getParameter("parkingname"));
			parkarea.setFloor(Integer.parseInt(request.getParameter("floor")));
			parkarea.setCapacity(Integer.parseInt(request.getParameter("capacity")));
			parkarea.setParkingtype(request.getParameter("type"));
			parkDao.updateParkarea(parkarea);
			response.sendRedirect("parkingspotController?action=modifyparkingarea");
		}
		if(action.equals("changename"))
		{
			String oldname = request.getParameter("oldname");
			String newname = request.getParameter("newname");
			parkDao.updateName(oldname, newname);
			response.sendRedirect("parkingspotController?action=modifyparkingarea");
			
		}
	}
	
}
