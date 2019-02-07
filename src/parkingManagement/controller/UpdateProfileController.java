package parkingManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parkingManagement.model.*;
import parkingManagement.data.*;

@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User(request.getParameter("firstname"), request.getParameter("lastname"),
				request.getParameter("username"), request.getParameter("password"), request.getParameter("uta_id"),
				request.getParameter("user_role"), request.getParameter("phone"), request.getParameter("email"),
				request.getParameter("saddress"), request.getParameter("city"), request.getParameter("state"),
				request.getParameter("zip"), request.getParameter("car_num_plate"), request.getParameter("permit_id"),
				request.getParameter("permit_type"));
		user.setFirstname(request.getParameter("firstname"));
		user.setUsername(request.getParameter("username"));
		ProfileDao profileDao = new ProfileDao();
		profileDao.updateUser(user);
		response.sendRedirect("ProfileController");

	}

}
