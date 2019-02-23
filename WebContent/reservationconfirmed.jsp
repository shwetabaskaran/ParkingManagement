<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table#t01 {
  border: 0.1px solid black;  
  align: "CENTER";
}
table#t01 tr:nth-child(even) {
  border: 0.1px solid black; 
  align: "CENTER";
  background-color: #AED6F1;
}
table#t01 tr:nth-child(odd) {
  border: 0.1px solid black; 
  align: "CENTER";
  background-color: #EBF5FB;
}
table#t01 th {
  color: black;
  align: "CENTER";
  border: 0.1px solid black;
  background-color: #EBF5FB;
}

.tabcontent {
  padding: 6px 12px;
}

input#ip01 {
	border: none;
	border-color: transparent;
 }
 
</style>
</head>
<body>

<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td> 
<td><a href="LogoutController">Logout</a></td>
</tr></table>
<h3>Your parking reservation details : </h3>
	<table border="1" cellpadding="2" style="margin-left: 30px">
		 <tr><td>Parking Area:</td><td><input type="text" id="ip01" name="parkingareaname" value="<c:out value='${parkingareaname}'/>" ></td></tr>
		 <tr><td>Parking type:</td><td><input type="text" id="ip01" name="parkingtype" value="<c:out value='${parkingtype}'/>" ></td></tr>
		 <tr><td>Floor:</td><td><input type="text" id="ip01" name="parkingareafloor" value="<c:out value='${parkingareafloor}'/>" ></td><tr>
		 <tr><td>Reservation start time:</td><td><input type="text" id="ip01" name="reservationfromtime" value="<c:out value='${reservationfromtime}'/>" ></td><tr>
		 <tr><td>Reservation end time:</td><td><input type="text" id="ip01" name="reservationtotime" value="<c:out value='${reservationfromtime}'/>" ></td><tr>
		 <tr><td>Selected options:</td><td><input type="text" id="ip01" name="options" value="<c:out value='${selectedoptions}'/>" ></td><tr>
	</table>
</body>
</html>