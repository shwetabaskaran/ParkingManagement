package parkingManagement.controller;

import parkingManagement.model.*;
import java.io.PrintWriter;
import parkingManagement.data.RegisterUserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/registerUserController")
public class RegisterUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action=request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		
		RegisterUserDao userDao = new RegisterUserDao();
		
		User user = new User(request.getParameter("firstname"), request.getParameter("lastname"), 
				request.getParameter("username"), request.getParameter("password"),request.getParameter("cpassword"),
				request.getParameter("utaid"), request.getParameter("user_role"),
				request.getParameter("phone"), request.getParameter("emailid"),
				request.getParameter("saddress"), request.getParameter("city"), 
				request.getParameter("state"), request.getParameter("zip"),
				request.getParameter("plate_number"), request.getParameter("permit_id"), 
				request.getParameter("permit_type"));
		
		//insert user
		if (action.equals("insertUser")) {
			UserErrorMsgs ErrorMsgs = new UserErrorMsgs();
			user.validateUser(user, ErrorMsgs,"");
			
			session.setAttribute("user",user);
			session.setAttribute("errorMsgs",ErrorMsgs);
			if (ErrorMsgs.getErrorMsg().equals("")) {
				userDao.registerUser(user);	 //save user if no errors
				session.removeAttribute("user");
				PrintWriter out = response.getWriter();
				 String htmlRespone = "<html>";
			        htmlRespone += "<h2>Registration Successful<br/>";      
			        htmlRespone += "<a href = 'index.jsp'> Login</a>";    
			        htmlRespone += "</html>";
			         
			        // return response
			        out.println(htmlRespone);
				
		        
			}
			else
			{
			url = "/register_user.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
			}
			}
	}
}
