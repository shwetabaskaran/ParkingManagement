<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search User</title>
</head>
<body>
<table>
<tr>
<td class="tabcontent"><a href='${home}'>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>Search for user</h1>
<form action="searchUserController" method="get">
<table>
<tr>
<td>Last name:</td><td><input type="text" name="search_lastname"></td>
<td style="color:red;">${errorMessage.lastNameErrMsg}</td></tr>
<tr><td><input type="submit" value="Search"></td></tr>
</table>
</form>
</body>
</html>