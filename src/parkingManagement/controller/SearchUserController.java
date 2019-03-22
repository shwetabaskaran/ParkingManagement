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

import parkingManagement.data.SearchUserDao;
import parkingManagement.model.User;
import parkingManagement.model.UserErrorMsgs;

@WebServlet("/searchUserController")
public class SearchUserController extends HttpServlet {
	String url="";
	String errMsg="";
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserErrorMsgs searchUserErr = new UserErrorMsgs();
		SearchUserDao searchDb = new SearchUserDao();
		HttpSession session = request.getSession();
		User search_user = new User();
		List<User> userList = new ArrayList<User>();
		String action = request.getParameter("action");
		
		if(action.equals("search")) {
			if(request.getParameter("search_lastname").equals(""))
			{
				url ="/search_user.jsp";
				errMsg = "Please enter the Lastname";
				searchUserErr.setLastnameError(errMsg);
				session.setAttribute("errorMessage", searchUserErr);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			else
			{
			//search_user.setFirstname(request.getParameter("search_lastname"));
				userList = searchDb.searchUser(request.getParameter("search_lastname"));
				if(userList.size()>0)
				{
					errMsg=" ";
					searchUserErr.setLastnameError(errMsg);
					session.setAttribute("userList", userList);
					session.setAttribute("errorMessage", searchUserErr);
					getServletContext().getRequestDispatcher("/searchUser_results.jsp").forward(request, response);
				}	
				else
				{
					url ="/search_user.jsp";
					errMsg = "User not found";
					searchUserErr.setLastnameError(errMsg);
					session.setAttribute("errorMessage", searchUserErr);
					getServletContext().getRequestDispatcher(url).forward(request, response);
				}
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
