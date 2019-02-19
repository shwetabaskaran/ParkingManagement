<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
 
</style>
</head>
<body>

<table>
<tr>
<td class="tabcontent"><a href=search_user.jsp>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
</tr></table>
<h1>Parking Details : </h1>
<form action='searchSpecificUserController' method='post'>
	<table>
		 <tr><td>Parking type:</td><td>${search_user.username}</td></tr>
		 <tr><td>Floor:</td><td>${search_user.role}</td><tr>
		 <tr><td>Price:</td><td>${search_user.email}</td></tr>
		 <tr>
		 	<td>
		 		<input type="checkbox" name="selected" value="${parkingspot.cart}">Cart:
		 	</td>
		 </tr>
		 <tr>
		 	<td>
		 		<input type="checkbox" name="selected" value="${parkingspot.camera}">Camera:
		 	</td>
		 </tr>
		 <tr>
		 	<td>
		 		<input type="checkbox" name="selected" value="${parkingspot.history}">History:
		 	</td>
		 </tr>
		 <tr><td>Total:</td><td>${search_user.email}</td></tr>
	</table>
	<br/>
	<input name="action" value="reserveoarkingspot" type="hidden" style="width: 100px; margin-left: 30px;">
	<input name="reserveoarkingspot" type="submit" value="Reserve" style="width: 100px; margin-left: 30px;">
	<input name="reset" type="reset" value="Reset" style="width: 100px; margin-left: 30px;">
</form>
<br/>
</body>
</html>