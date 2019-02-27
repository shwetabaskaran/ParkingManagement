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
<title>View my Reserved Spots</title>
</head>
<body>
<table>
<tr>
 
<td class="tabcontent"><a href=stuFacHomePageController>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>Cancel my reservation</h1>
<form action="ReservedSpotsController?action=cancelreservation" method="post">
<table border=1 cellspacing="1">
<c:if test="${empty reservationsforcancellationlist}">
<input name="AlertMsg" value="<c:out value='Sorry! None of your reservations are eligible for cancellation.'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
</c:if>
<c:if test="${!empty reservationsforcancellationlist}">
	<tr>
		<th>User name </th>
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
		<tr>
            
            <td><c:out value="${reservedspot.getUsername()}" /></td>
            <td><c:out value="${reservedspot.getReservation_date()}" /></td>
            <td><c:out value="${reservedspot.getFrom_time()}" /></td>
            <td><c:out value="${reservedspot.getTo_time()}" /></td>
            <td><c:out value="${reservedspot.getParkingslot_no()}" /></td>
            <td><c:out value="${reservedspot.isCart()}" /></td>
            <td><c:out value="${reservedspot.isCamera()}" /></td>
            <td><c:out value="${reservedspot.isHistory()}" /></td>
            <td style="border:none" ><input type="submit" value="Delete"/></td>
        </tr>
    </c:forEach>
    </c:if>
</table>
</form>
</body>
</html>