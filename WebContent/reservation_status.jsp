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
<title>View my Reservation Status</title>
</head>
<body>
<table>
<tr>
 
<td class="tabcontent"><a href=${home}>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<h1>View my Reservation Status</h1>
<form action="ReservationStatusController" method="get">
<table border=1 cellspacing="0">
<c:if test="${empty reservationstatus}">
<input id="alertMsg" name="AlertMsg" value="<c:out value='Sorry! No future reservations are found.'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
</c:if>
<c:if test="${!empty reservationstatus}">
<tr>
<th>Reservation ID </th>
<th>Parking Area Name </th>
<th>Parking Type </th>
<th>Floor </th>
<th>Reservation Date </th>
<th>From time </th>
<th>To time </th>
<th>Parking Slot Number </th>
<th>Cart </th>
<th>Camera </th>
<th>History </th>
<th>Reservation Status </th>
<th>Payment Confirmation </th>

</tr>
<c:forEach items="${reservationstatus}" var="reservedspot">
        <tr>
            <td id="resID"><c:out value="${reservedspot.getReservation_id()}" /></td>
            <td id="areaName"><c:out value="${reservedspot.getParkingarea_name()}" /></td>
            <td id="type"><c:out value="${reservedspot.getParkingtype()}" /></td>
            <td id="floor"><c:out value="${reservedspot.getFloor()}" /></td>
            <td id="date"><c:out value="${reservedspot.getReservation_date()}" /></td>
            <td id="fromTime"><c:out value="${reservedspot.getFrom_time()}" /></td>
            <td id="toTime"><c:out value="${reservedspot.getTo_time()}" /></td>
            <td id="slot"><c:out value="${reservedspot.getParkingslot_no()}" /></td>
            <td id="cart"><c:out value="${reservedspot.getCart()}" /></td>
            <td id="camera"><c:out value="${reservedspot.getCamera()}" /></td>
            <td id="history"><c:out value="${reservedspot.getHistory()}" /></td>
            <td id="status"><c:out value="${reservedspot.getReservation_status()}" /></td>
            <td id="confirmation"><c:out value="${reservedspot.getPayment_confirmation()}" /></td>
        </tr>
    </c:forEach>
     </c:if>
</table>
</form>
</body>
</html>