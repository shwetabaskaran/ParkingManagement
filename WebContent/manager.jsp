<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Home page</title>
</head>
<body>
<h1> Manager Home page</h1>
<a href="ProfileController">My profile</a><br>
<a href="viewAvailSpotController?action=numberavailable">View number of available spaces</a><br>
<a href="viewAvailSpotController?action=spotdetails">View details of specific parking spot</a><br>
<a href="search_user.jsp">Search for user</a><br>
<a href="">Modify Reservation</a><br>
<a href="">Delete Reservation</a><br>
<a href="parkingspotController?action=modifyparkingarea">Parking Area Functions</a><br>
<a href="LogoutController">Logout</a><br>
</body>
</html>
