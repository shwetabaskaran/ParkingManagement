package parkingManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parkingManagement.data.ReservationDao;
import parkingManagement.data.SearchUserDao;
import parkingManagement.model.ParkingArea;
import parkingManagement.model.Reservation;
import parkingManagement.model.User;

@WebServlet("/searchSpecificUserController")
public class ReserveParkingspotController extends HttpServlet {

	String url="";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReservationDao reservationDao = new ReservationDao();
		ParkingArea parkingArea = new ParkingArea();
		HttpSession session = request.getSession();
		Reservation reservation = new Reservation();
		User sessionUser = new User();
		boolean cart = false;
		boolean camera = false; 
		boolean history = false;
		
		if( ! request.getParameter("parkingareaName").equals("") ) {
			parkingArea.setParkingarea_name(request.getParameter("parkingareaName"));
			parkingArea.setParkingtype(request.getParameter("parkingtype"));
			parkingArea.setParkingarea_id(Integer.parseInt(request.getParameter("parkingareaId")));
			parkingArea.setFloor(Integer.parseInt(request.getParameter("floor")));
			
			/*cart = request.getParameter("cart");
			
			dbuser = searchDb.searchSpecificUser(request.getParameter("search_username"));
			if(dbuser.getUsername().equals(search_user.getUsername())) {
				session.setAttribute("search_user", dbuser);
				getServletContext().getRequestDispatcher("/searchSpecificUser.jsp").forward(request, response);
			}*/
		}
	}

}
