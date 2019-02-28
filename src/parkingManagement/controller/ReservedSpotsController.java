package parkingManagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parkingManagement.data.*;
import parkingManagement.model.*;
import javax.servlet.http.HttpSession;
@WebServlet("/ReservedSpotsController")
public class ReservedSpotsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		ReservedSpotsDao reservedspotsdb = new ReservedSpotsDao();
		List<ReservedSpots> reservedspots = new ArrayList<ReservedSpots>();
		List<Reservation> reserved = new ArrayList<Reservation>();
		User temp = (User)session.getAttribute("user_info");
		
		
		if(action.equals("getreservationsforcancellation")){
			reserved = reservedspotsdb.getReservationsForCancellation(temp.getUsername());
			session.setAttribute("reservationsforcancellationlist", reserved);
			getServletContext().getRequestDispatcher("/cancelmyreservation.jsp").forward(request, response);
			
		} if(action.equals("getreservationsforview")) {
			reservedspots = reservedspotsdb.viewReservedSpots(temp.getUsername());
			
			if(reservedspots.size()>0)
			{
				session.setAttribute("reservedspotlist", reservedspots);
			}
			getServletContext().getRequestDispatcher("/view_my_reservedspots.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action");
		ReservedSpotsDao reservedspotsdb = new ReservedSpotsDao();
		
		if(action.equals("cancelreservation")) {
			int reservationId = Integer.parseInt(request.getParameter("reservationid"));
			reservedspotsdb.deleteReservation(reservationId);
			response.sendRedirect("ReservedSpotsController?action=getreservationsforcancellation");
			
		}
	}
}