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
		UserStatusErrorMsgs userStatusErr = new UserStatusErrorMsgs();
		UserStatusDao userStatusDb = new UserStatusDao();
		UserStatusDao changeUserStatusDb = new UserStatusDao();
		HttpSession session = request.getSession();
		
//		if(action.equals("GetActiveUsers"))
//		{
//			List<User> activeUsers = new ArrayList<User>();
//			activeUsers = userStatusDb.getActiveUsers();
//			session.setAttribute("active_users",activeUsers);
//			session.setAttribute("onloads", 1);
//			response.sendRedirect("UserStatusController?action=GetActiveUsers");
//		}
//		else if(action.equals("ActivateUser"))
//		{
			String username = request.getParameter("username");
			userStatus = userStatusDb.getUserStatus(username);
			if(userStatus.equalsIgnoreCase("Revoked"))
			{
				System.out.println("Here");
				url ="/activate_user.jsp";
				changeUserStatusDb.changeUserStatus("Active", username);
				String successMsg = "User has been activated!";
				session.setAttribute("successmessage",successMsg);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
//		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}