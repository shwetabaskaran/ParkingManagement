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
@WebServlet("/ReservationStatusController")
public class ReservationStatusController extends HttpServlet {
	String url="";
	String errMsg="";
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ReservedSpotsDao reservationstatusdb = new ReservedSpotsDao();
		List<ReservedSpots> reservedspotswithstatus = new ArrayList<ReservedSpots>();
		
		User temp = (User)session.getAttribute("user_info");
			reservedspotswithstatus = reservationstatusdb.viewReservationStatus(temp.getUsername());
			session.setAttribute("reservationstatus", reservedspotswithstatus);
			getServletContext().getRequestDispatcher("/reservation_status.jsp").forward(request, response);
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
}
