<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>
<h1>User Details of ${search_user.firstname} ${search_user.lastname}</h1>
<form action='searchSpecificUserController' metdod='get'><table>
				 <tr><td>Username:</td><td>${search_user.username}</td></tr>
				 <tr><td>User Role:</td><td>${search_user.role}</td><tr>
				 <tr><td>Firstname:</td><td>${search_user.firstname}</td></tr>
				 <tr><td>Lastname:</td><td>${search_user.lastname}</td></tr>
				 <tr><td>UTA ID:</td><td>${search_user.uta_id}</td></tr>
				 <tr><td>Email:</td><td>${search_user.email}</td></tr>
				 <tr><td>Phone:</td><td>${search_user.phone}</td></tr>
				 <tr><td>Street Address:</td><td>${search_user.street_add}</td></tr>
				 <tr><td>City:</td><td>${search_user.city}</td></tr>
				 <tr><td>State:</td><td>${search_user.state}</td></tr>
				 <tr><td>Zip:</td><td>${search_user.getZip_code()}</td></tr>
				 <tr><td>Permit ID:</td><td>${search_user.permit_id}</td></tr>
				 <tr><td>Permit type:</td><td>${search_user.permit_type}</td></tr>
				 <tr><td>Car Number Plate:</td><td>${search_user.getCar_plate_num()}</td></tr>
				 </table></form>
</body>
</html>