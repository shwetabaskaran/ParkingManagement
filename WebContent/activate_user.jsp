<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Activate User</title>
</head>
<body>
<h1>Activate User</h1>
<form action="userStatusController" method="get">
<table>
<tr>
<td>User name:</td><td><input type="text" name="username"></td>

<tr><td><input type="submit" value="Activate"></td></tr>
<tr><td>${successmessage}</td></tr>
</table>
</form>
</body>
</html>