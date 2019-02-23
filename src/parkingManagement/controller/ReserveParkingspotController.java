package parkingManagement.controller;

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

import parkingManagement.data.ReservationDao;
import parkingManagement.model.PaymentDetails;
import parkingManagement.model.PaymentErrorMsgs;
import parkingManagement.model.Reservation;
import parkingManagement.model.User;

@WebServlet("/reserveParkingspotController")
public class ReserveParkingspotController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	String url="";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReservationDao reservationDao = new ReservationDao();
		Reservation reservation = new Reservation();
		PaymentDetails paymentDetails = new PaymentDetails(request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("address"), request.getParameter("cardnum"),
				request.getParameter("type"), request.getParameter("exp_month"), request.getParameter("exp_year"), request.getParameter("cvv"));
		HttpSession session = request.getSession();
		User sessionUser =  (User) session.getAttribute("user_info");
		String[] cart = request.getParameterValues("selectedcart");
		String[] camera = request.getParameterValues("selectedcamera"); 
		String[] history = request.getParameterValues("selectedhistory");
		
		
		java.util.Date utilDate = new java.util.Date();
		Date today = new Date(utilDate.getTime());
		
		Time from = null;
		Time to = null;
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		try {
			from = new Time(formatter.parse(request.getParameter("reservationfromtime")).getTime());
			to = new Time(formatter.parse(request.getParameter("reservationtotime")).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean selectedCart =false;
		String selectedOptions = "";
	    if(cart !=null && cart.length > 0){//If checkbox is checked than assign it with true or 1       
	    	selectedCart=true;  
	    	selectedOptions = selectedOptions + "Cart, ";
	    }
	    
	    boolean selectedCamera =false;
	    if(camera !=null && camera.length > 0){//If checkbox is checked than assign it with true or 1       
	    	selectedCamera=true;  
	    	selectedOptions = selectedOptions + "Camera, ";
	    }
	    
	    boolean selectedHistory =false;
	    if(history !=null && history.length > 0){//If checkbox is checked than assign it with true or 1       
	    	selectedHistory=true;  
	    	selectedOptions = selectedOptions + "History";
	    }
	    
	    if(!selectedOptions.equals("")) {
			PaymentErrorMsgs errorMsgs = new PaymentErrorMsgs();
			
			paymentDetails.validateUser(paymentDetails, errorMsgs);
			session.setAttribute("parkingErrorMsgs", errorMsgs);
	    }
			
		
		if( ! request.getParameter("parkingareaname").equals("") ) {
			reservation.setParkingarea_id(Integer.parseInt(request.getParameter("parkingareaid")));
			reservation.setUsername(sessionUser.getUsername());
			reservation.setCart(selectedCart);
			reservation.setCamera(selectedCamera);
			reservation.setHistory(selectedHistory);
			reservation.setFrom_time(from);
			reservation.setTo_time(to);
			reservation.setReservation_date(today);
			System.out.println("reservation is time is : " +reservation.toString());
			
			reservationDao.reserveParkingSlot(reservation);
			session.setAttribute("reservation", reservation);	
			session.setAttribute("parkingareaname", request.getParameter("parkingareaname"));
			
			session.setAttribute("selectedoptions", selectedOptions);
			session.setAttribute("parkingtype", request.getParameter("parkingtype"));
			session.setAttribute("parkingareafloor", request.getParameter("parkingareafloor"));
		}
		getServletContext().getRequestDispatcher("/reservationconfirmed.jsp").forward(request, response);
	}

}
