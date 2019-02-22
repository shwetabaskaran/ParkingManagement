<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

input#ip01 {
	border: none;
	border-color: transparent;
 }
</style>
</head>
<body>

<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td> 
<td class="tabcontent"><a href=searchparkingspot.jsp>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
</tr></table>
<h3>Parking Details : </h3>
<c:set var="rowno" value="${param.radioButton-1}"></c:set>
<form action='reserveParkingspotController' method='post'>
	
	<input id="parkingareaid" name="parkingareaid" style="display:none" value="${parkingspots[rowno].parkingarea_id}"/>
	<table border="1" cellpadding="2" style="margin-left: 30px">
	     
		 <tr><td>Parking Area:</td><td><input type="text" id="ip01" name="parkingareaname" value="<c:out value='${parkingspots[rowno].parkingarea_name}'/>" ></td></tr>
		 <tr><td>Parking type:</td><td><input border="0" type="text" id="ip01" name="parkingtype" value="<c:out value='${parkingspots[rowno].parkingtype}'/>" ></td></tr>
		 <tr><td>Floor:</td><td><input border="0" type="text" id="ip01" name="parkingareafloor" value="<c:out value='${parkingspots[rowno].floor}'/>" ></td><tr>
		 <tr><td>Reservation start time:</td><td><input border="0" type="text" id="ip01" name="reservationfromtime" value="<c:out value='${reservationfromtime}'/>" ></td><tr>
		 <tr><td>Reservation end time:</td><td><input border="0" type="text" id="ip01" name="reservationtotime" value="<c:out value='${reservationtotime}'/>" ></td><tr>
	</table>
		<br/>
		Select options:
		<br/>
 		<input type="checkbox" id="selectedcart" name="selectedcart" style="margin-left: 30px; margin-top: 8px; " onclick="myFunction()" >Cart
 		<input type="checkbox" id="selectedcamera" name="selectedcamera" style="margin-left: 30px; margin-top: 8px; " onclick="myFunction()">Camera
 		<input type="checkbox" id="selectedhistory" name="selectedhistory" style="margin-left: 30px; margin-top: 8px; " onclick="myFunction()">History
<br/>
		<p id="cart" style="display:none">Cart: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $15.95</p>
		<p id="camera" style="display:none">Camera: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $2.95</p>
		<p id="history" style="display:none">History: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $1.95</p>
		<p id="tax" style="display:none"></p>
		
		<p id="totalPrice" style="display:block">Total Price: &nbsp;&nbsp; $0</p>
	<input name="action" value="reserveparkingspot" type="hidden" style="width: 100px; margin-left: 30px; margin-top: 10px; ">
	<input name="reserveparkingspot" type="submit" value="Reserve" style="width: 100px; margin-left: 30px; margin-top: 10px; ">
	<input name="reset" type="reset" value="Back" style="width: 100px; margin-left: 30px; margin-top: 10px; ">
</form>
<script>
		function myFunction() {
		  var cartCheckBox = document.getElementById("selectedcart");
		  var cameraCheckBox = document.getElementById("selectedcamera");
		  var historyCheckBox = document.getElementById("selectedhistory");
		  var cart = document.getElementById("cart");
		  var camera = document.getElementById("camera");
		  var history = document.getElementById("history");
		  var tax = document.getElementById("tax");
		  var totalprice = document.getElementById("totalPrice");
		  
		  var taxValueCart = 0;
		  var taxValueCamera = 0;
		  var taxValueHistory = 0;
		  var taxValue = 0;
		  
		  var cartValue = 0;
		  var cameraValue = 0;
		  var historyValue = 0;
		  
		  if (cartCheckBox.checked == true){
		      cart.style.display = "block";
		      cartValue=15.95;
		      taxValueCart = 1.32;
		  } else {
			  cart.style.display = "none";
		  }
		  
		  if (cameraCheckBox.checked == true){
			  camera.style.display = "block";
			  cameraValue = 2.95;
			  taxValueCamera = 0.24;
		  } else {
			  camera.style.display = "none";
		  }
		  
		  if (historyCheckBox.checked == true){
			  history.style.display = "block";
			  historyValue = 1.95;
			  taxValueHistory = 0.16;
		  } else {
			  history.style.display = "none";
		  }
		  if(cartCheckBox.checked == true || cameraCheckBox.checked == true || historyCheckBox.checked == true) {
			  tax.style.display = "block";
			  tax.innerHTML = "Tax(8.25%): &nbsp;&nbsp; $" +(taxValueCart+taxValueCamera+taxValueHistory);
			}
		  else
			  tax.style.display="none";
		  totalprice.innerHTML = "Total Price: &nbsp;&nbsp;&nbsp; $"+(taxValueCart+taxValueCamera+taxValueHistory+historyValue+cameraValue+cartValue);
		}
		</script>
<br/>
</body>
</html>