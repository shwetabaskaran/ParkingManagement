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

@WebServlet("/searchUserController")
public class SearchUserController extends HttpServlet {
	String url="";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request,response);	
		System.out.println("In controller");
		SearchUserDao searchDb = new SearchUserDao();
		HttpSession session = request.getSession();
		User search_user = new User();
		List<User> userList = new ArrayList<User>();
		
		search_user.setFirstname(request.getParameter("search_firstname"));
		search_user.setLastname(request.getParameter("search_lastname"));
		
		userList = searchDb.searchUser(request.getParameter("search_firstname"),request.getParameter("search_lastname"));
		if(userList.size()>0)
		{
				session.setAttribute("userList", userList);
				getServletContext().getRequestDispatcher("/searchUser_results.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
