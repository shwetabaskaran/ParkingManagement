<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Reservation</title>
</head>
<body>
<table>
<tr>
 
<td class="tabcontent"><a href=manager.jsp>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>Delete Reservation</h1>
<form action="ReservedSpotsController" method="get">
<table border=0 cellspacing="0"><tr>
<td>User name:</td><td><input type="text" name="search_username" value="<c:out value='${search_username}'/>"></td>
<td style="color:red;">${errorMessage.userNameErrMsg}</td></tr>
<tr></tr>

</table>
<br/>
<input name="action" value="SearchByUserName" type="hidden" style="width: 100px; margin-left: 30px;">
	<input name="SearchByUserName" type="submit" value="Search" style="width: 100px; margin-left: 30px;" >
	<input type="reset" value="Reset" style="width: 100px; margin-left: 30px;">
</form>
<br/>
<form action="ReservedSpotsController?action=cancelreservation" method="post">
<table border=1 cellspacing="1">
<c:choose>
<c:when test="${reservationsforcancellationlist=='none'}">
<input name="AlertMsg" value="<c:out value='Sorry! None of the reservations for this user are eligible for cancellation.'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
</c:when>
<c:otherwise>
<c:if test="${!empty reservationsforcancellationlist}">
	<tr>
		<th>Reservation id </th>
		<th>Parking Area </th>
		<th>Parking Type </th>
		<th>Floor </th>
		<th>Reservation Date </th>
		<th>From time </th>
		<th>To time </th>
		<th>Parking Slot Number </th>
		<th>Cart </th>
		<th>Camera </th>
		<th>History </th>
		<th style="border:none"></th>
		</tr>
		
       
<c:forEach items="${reservationsforcancellationlist}" var="reservedspot">
<input type="text" name="reservationid" style="display:none" value="<c:out value="${reservedspot.getReservation_id()}" />" >
<input type="text" name="parkingid" style="display:none" value="<c:out value="${reservedspot.getParkingarea_id()}" />" >
<input type="text" name="search_username" style="display:none" value="<c:out value='${reservedspot.getUsername()}'/>">
		<tr>
             <td><c:out value="${reservedspot.getReservation_id()}" /></td>
            <td><c:out value="${reservedspot.getParkingarea_name()}" /></td>
            <td><c:out value="${reservedspot.getParkingtype()}" /></td>
            <td><c:out value="${reservedspot.getParkingtype()}" /></td>
            <td><c:out value="${reservedspot.getReservation_date()}" /></td>
            <td><c:out value="${reservedspot.getFrom_time()}" /></td>
            <td><c:out value="${reservedspot.getTo_time()}" /></td>
            <td><c:out value="${reservedspot.getParkingslot_no()}" /></td>
            <td><c:out value="${reservedspot.getCart()}" /></td>
            <td><c:out value="${reservedspot.getCamera()}" /></td>
            <td><c:out value="${reservedspot.getHistory()}" /></td>
            <td style="border:none" ><input type="submit" value="Delete"/></td>
        </tr>
    </c:forEach>
    </c:if>
    </c:otherwise>
    </c:choose>
</table>
</form>
</body>
</html>