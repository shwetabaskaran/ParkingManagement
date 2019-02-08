<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search User Results</title>
</head>
<body>

<h1>User List</h1>
<p>Click on user name to view user details</p>
<form action="searchUserController" method="get">
<table border=1 cellspacing="0">
<tr>
<th>First Name </th>
<th>Last Name </th>
<th>User Name </th>
</tr>

<c:forEach items="${userList}" var="search_user">
        <tr>
            <td><c:out value="${search_user.getFirstname()}" /></td>
            <td><c:out value="${search_user.getLastname()}" /></td>
            <td><a href="searchSpecificUserController?search_username=${search_user.getUsername()}"/><c:out value="${search_user.getUsername()}" /></td>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>