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

<h1>User Details</h1>
<form action="searchUserController" method="get">
<table border=1 cellspacing="0">
<tr>
<th>First Name </th>
<th>Last Name </th>
<th>User Name </th>
<th>Password </th>
<th>UTA ID </th>
<th>Email </th>
<th>Phone </th>
<th>Role </th>
<th>Street Address </th>
<th>City </th>
<th>State </th>
<th>Zip Code </th>
<th>Car Plate Number </th>
<th>Permit ID </th>
<th>Permit type </th>
</tr>

<c:forEach items="${userList}" var="search_user">
        <tr>
            <td><c:out value="${search_user.getFirstname()}" /></td>
            <td><c:out value="${search_user.getLastname()}" /></td>
            <td><c:out value="${search_user.getUsername()}" /></td>
            <td><c:out value="${search_user.getPassword()}" /></td>
            <td><c:out value="${search_user.getUta_id()}" /></td>
            <td><c:out value="${search_user.getEmail()}" /></td>
            <td><c:out value="${search_user.getPhone()}" /></td>
            <td><c:out value="${search_user.getRole()}" /></td>
            <td><c:out value="${search_user.getStreet_add()}" /></td>
            <td><c:out value="${search_user.getCity()}" /></td>
            <td><c:out value="${search_user.getState()}" /></td>
            <td><c:out value="${search_user.getZip_code()}" /></td>
            <td><c:out value="${search_user.getCar_plate_num()}" /></td>
            <td><c:out value="${search_user.getPermit_id()}" /></td>
            <td><c:out value="${search_user.getPermit_type()}" /></td>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>