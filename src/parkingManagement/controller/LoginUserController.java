package parkingManagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parkingManagement.model.User;
import parkingManagement.data.LoginUserDao;
import parkingManagement.model.*;
import javax.servlet.http.HttpSession;

@WebServlet("/loginUserController")
public class LoginUserController extends HttpServlet {
	String url="";
	String errMsg="";
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginErrorMsg loginErr = new LoginErrorMsg();
		LoginUserDao regDb = new LoginUserDao();
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		session.removeAttribute("incorrectpass");
		User login_user = new User();
		User dbuser = new User();
		if((request.getParameter("login_username").equals("")) 
				|| (request.getParameter("login_password").equals("")))
		{
			url ="/index.jsp";
			errMsg = "Please enter the Username or Password";
			loginErr.setUserNameErrMsg(errMsg);
			session.setAttribute("errorMessage", loginErr);
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} else	{
			login_user.setUsername(request.getParameter("login_username"));
			login_user.setPassword(request.getParameter("login_password"));
			dbuser = regDb.searchUser(request.getParameter("login_username"));
			if(dbuser.getPassword().equals(login_user.getPassword())) {
				if(dbuser.getRole().equals("Student/Faculty"))
				response.sendRedirect("student_faculty.jsp");
				else if(dbuser.getRole().equals("Manager"))
				response.sendRedirect("manager.jsp");
				else if(dbuser.getRole().equals("Admin"))
				response.sendRedirect("admin.jsp");
			} else {	
				url ="/index.jsp";
				errMsg="Incorrect Username or Password";
				loginErr.setUserNameErrMsg(errMsg);
				session.setAttribute("incorrectpass", loginErr);
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}

		}
	}
}
