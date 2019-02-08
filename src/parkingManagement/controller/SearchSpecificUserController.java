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

@WebServlet("/searchSpecificUserController")
public class SearchSpecificUserController extends HttpServlet {
	String url="";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request,response);	
		SearchUserDao searchDb = new SearchUserDao();
		HttpSession session = request.getSession();
		User search_user = new User();
		User dbuser = new User();
		
		if(request.getParameter("search_username").equals(""))
		{
			url ="/index.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	else
		{
			search_user.setUsername(request.getParameter("search_username"));
			dbuser = searchDb.searchSpecificUser(request.getParameter("search_username"));
			if(dbuser.getUsername().equals(search_user.getUsername()))
				{
				session.setAttribute("search_user", dbuser);
				getServletContext().getRequestDispatcher("/searchSpecificUser.jsp").forward(request, response);
				}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}

