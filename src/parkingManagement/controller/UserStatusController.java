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
		String userStatus = "";
		String successMsg = "";
		UserStatusErrorMsgs userStatusErr = new UserStatusErrorMsgs();
		UserStatusDao userStatusDb = new UserStatusDao();
		UserStatusDao changeUserStatusDb = new UserStatusDao();
		HttpSession session = request.getSession();
		session.removeAttribute("successmessage");
		session.removeAttribute("modess");
		session.removeAttribute("UserStatuserrorMessage");
			String username = request.getParameter("search_username");
			if(username.equals(""))
			{
				url ="/activate_user.jsp";
				errMsg = "Please enter the Username";
				userStatusErr.setUserNameErrMsg(errMsg);
				session.setAttribute("modess","error");
				session.removeAttribute("successmessage");
				session.setAttribute("UserStatuserrorMessage", userStatusErr);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else
			{
				userStatus = userStatusDb.getUserStatus(username);
				if(!(userStatus.equals("")))
				{
					if(userStatus.equalsIgnoreCase("Revoked"))
					{
						url ="/activate_user.jsp";
						changeUserStatusDb.changeUserStatus("Active", username);
						session.setAttribute("successmessage","User has been activated!");
						session.removeAttribute("UserStatuserrorMessage");
						session.setAttribute("modess", "success");
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}
					else if(userStatus.equalsIgnoreCase("Active"))
					{
						url ="/activate_user.jsp";
						errMsg = "User is already Active";
						successMsg = "";
						session.setAttribute("successmessage",successMsg);
						session.removeAttribute("successmessage");
						session.setAttribute("modess", "error");
						userStatusErr.setUserNameErrMsg(errMsg);
						session.setAttribute("UserStatuserrorMessage", userStatusErr);
						getServletContext().getRequestDispatcher(url).forward(request, response);	
					}
				}
				else
				{
					url ="/activate_user.jsp";
					errMsg = "User not found";
					successMsg = "";
					session.setAttribute("successmessage",successMsg);
					session.removeAttribute("successmessage");
					session.setAttribute("modess", "error");
					userStatusErr.setUserNameErrMsg(errMsg);
					session.setAttribute("UserStatuserrorMessage", userStatusErr);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userStatus = "";
		String successMsg = "";
		UserStatusErrorMsgs userStatusErr = new UserStatusErrorMsgs();
		UserStatusDao userStatusDb = new UserStatusDao();
		UserStatusDao changeUserStatusDb = new UserStatusDao();
		HttpSession session = request.getSession();
		session.removeAttribute("successmessage");
		session.removeAttribute("modess");
		session.removeAttribute("UserStatuserrorMessage");
			String username = request.getParameter("search_username");
			if(username.equals(""))
			{
				url ="/revoke_user.jsp";
				errMsg = "Please enter the Username";
				userStatusErr.setUserNameErrMsg(errMsg);
				session.setAttribute("modess","error");
				session.removeAttribute("successmessage");
				session.setAttribute("UserStatuserrorMessage", userStatusErr);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else
			{
				userStatus = userStatusDb.getUserStatus(username);
				if(!(userStatus.equals("")))
				{
					if(userStatus.equalsIgnoreCase("Active"))
					{
						url ="/revoke_user.jsp";
						changeUserStatusDb.changeUserStatus("Revoked", username);
						changeUserStatusDb.resetViolations(username);
						session.setAttribute("successmessage","User has been activated!");
						session.removeAttribute("UserStatuserrorMessage");
						session.setAttribute("modess", "success");
						getServletContext().getRequestDispatcher(url).forward(request, response);
					}
					else if(userStatus.equalsIgnoreCase("Revoke"))
					{
						url ="/revoke_user.jsp";
						errMsg = "User is already Revoked";
						successMsg = "";
						session.setAttribute("successmessage",successMsg);
						session.removeAttribute("successmessage");
						session.setAttribute("modess", "error");
						userStatusErr.setUserNameErrMsg(errMsg);
						session.setAttribute("UserStatuserrorMessage", userStatusErr);
						getServletContext().getRequestDispatcher(url).forward(request, response);	
					}
				}
				else
				{
					url ="/revoke_user.jsp";
					errMsg = "User not found";
					successMsg = "";
					session.setAttribute("successmessage",successMsg);
					session.removeAttribute("successmessage");
					session.setAttribute("modess", "error");
					userStatusErr.setUserNameErrMsg(errMsg);
					session.setAttribute("UserStatuserrorMessage", userStatusErr);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			}
	}
}