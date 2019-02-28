package parkingManagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parkingManagement.data.UserStatusDao;
import parkingManagement.model.LoginErrorMsg;
import parkingManagement.model.ParkingArea;
import parkingManagement.model.RegisterUserErrorMsgs;
import parkingManagement.model.UserStatusErrorMsgs;
import parkingManagement.model.User;

@WebServlet("/userStatusController")
public class UserStatusController extends HttpServlet {
	String url="";
	String errMsg="";
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String action = request.getParameter("action");
		String userStatus = null;
		String successMsg = "";
		UserStatusErrorMsgs userStatusErr = new UserStatusErrorMsgs();
		UserStatusDao userStatusDb = new UserStatusDao();
		UserStatusDao changeUserStatusDb = new UserStatusDao();
		HttpSession session = request.getSession();
			String username = request.getParameter("username");
			userStatus = userStatusDb.getUserStatus(username);
			if(userStatus.equalsIgnoreCase("Revoked"))
			{
				url ="/activate_user.jsp";
				changeUserStatusDb.changeUserStatus("Active", username);
				successMsg = "User has been activated!";
				session.setAttribute("successmessage",successMsg);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userStatus = null;
		UserStatusErrorMsgs userStatusErr = new UserStatusErrorMsgs();
		UserStatusDao userStatusDb = new UserStatusDao();
		UserStatusDao changeUserStatusDb = new UserStatusDao();
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		userStatus = userStatusDb.getUserStatus(username);
		if(userStatus.equalsIgnoreCase("Active"))
		{
			url ="/revoke_user.jsp";
			changeUserStatusDb.changeUserStatus("Revoked", username);
			String successMsg = "User has been revoked!";
			session.setAttribute("successmessage",successMsg);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	}
}