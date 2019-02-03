package parkingManagement.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parkingManagement.model.NameForm;

@WebServlet("/NameController")
public class NameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		NameForm nameForm = new NameForm();
		nameForm.setName(request.getParameter("name"));
		session.setAttribute("nameForm",nameForm);
		nameForm.setGreetingText("Hello "+nameForm.getName());
		nameForm.setId(request.getParameter("id"));
		nameForm.setIdText("Student Id number : "+nameForm.getId());
		String currentTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		nameForm.setCurrentTime(currentTime);
		nameForm.setCurrentTimeText("Current time is : "+nameForm.getCurrentTime());
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);		
	}

}
