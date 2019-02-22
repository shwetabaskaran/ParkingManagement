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
 
#element1 {display:inline-block;margin-right:100px; } 
#element2 {display: none;margin-left:100px; vertical-align: top;} 
</style>
</head>
<body>

<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td> 
<td class="tabcontent"><a href=searchparkingspot.jsp>Back</a></td> 
<td><a href="LogoutController">Logout</a></td>
</tr></table>

<c:set var="rowno" value="${param.radioButton-1}"></c:set>
<div id="element1">
<h3>Parking Details : </h3>
	
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
	<button name="confirm" style="width: 100px; margin-left: 30px; margin-top: 10px;" onclick="showpayment()">Reserve</button>
	<input name="reset" type="reset" value="Back" style="width: 100px; margin-left: 30px; margin-top: 10px; ">
	
</div>
<div id="element2">
<h3>Payment : </h3>
 <form action='reserveParkingspotController' method='post'>
	
	<table border="0" cellpadding="2" style="margin-left: 30px">	     
	     
	     <tr>
          <td align="right">First Name*&nbsp;</td>
          <td><input name="firstname" type="text" id="firstname" value="" />
          &nbsp;<label id="firstNameError" ></label></td>
        </tr>
		<tr>
          <td align="right">Last Name*&nbsp;</td>
          <td><input name="lastname" type="text" id="lastname" value="" />
          &nbsp;<label id="lastNameError" ></label></td>
        </tr>
		<tr>
          <td align="right" valign="top">Billing Address*&nbsp;</td>
          <td><textarea name="address" rows="4" id="address"></textarea>            
            &nbsp;<label id="address_span" ></label></td>
        </tr>
		<tr>
          <td align="right">Credit Card No.*&nbsp;</td>
          <td><input name="cc_num" type="text"  id="cc_num" value="" />
          &nbsp;<label id="cc_num_tip" >Use 16 digit Dummy Data</label> <label id="cc_num_span" ></label></td>
        </tr>
		<tr>
          <td align="right">Credit Card Type*&nbsp;</td>
          <td><select name="cc_type" id="cc_type">
            <option value="">- Select Credit Card Type -</option>
            <option value="AMEX">American Express</option>
            <option value="VISA">VISA</option>
            <option value="MAST">Master Card</option>
            <option value="OTHR">Other</option>
          </select>
          &nbsp;<label id="cc_type_span" ></label></td>
        </tr>
		<tr>
          <td align="right">Expiry Date*&nbsp;</td>
          <td><select name="exp_month" id="exp_month">
            <option value="">- Select Month -</option>
            <option value="1">January</option>
            <option value="2">February</option>
            <option value="3">March</option>
			<option value="4">April</option>
			<option value="5">May</option>
			<option value="6">June</option>
			<option value="7">July</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">October</option>
			<option value="11">November</option>
            <option value="12">December</option>
          </select>
		  <select name="cc_exp_year" id="cc_exp_year">
            <option value="">- Select Year -</option>
            <option value="2011">2011</option>
			<option value="2012">2012</option>
            <option value="2013">2013</option>
            <option value="2014">2014</option>
            <option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
			<option value="2019">2019</option>
			<option value="2020">2020</option>
			<option value="2021">2021</option>
			<option value="2022">2022</option>
          </select>
          &nbsp;<label id="cc_expiry_span" ></label></td>
        </tr>
		<tr>
          <td align="right">CVV Number*&nbsp;</td>
          <td><input name="cc_cvv" type="text"  id="cc_cvv" value="" maxlength="4" onkeypress="return OnlyNumbersonly(event)" />
          &nbsp;<label id="cc_cvv_span" ></label></td>
        </tr>
        <tr>
          <td><p id="totalPrice" style="display:block">Total Price: &nbsp;&nbsp; $0</p></td>
        </tr>
	     
	</table>
		<br/>
	
		
		
	<input name="action" value="reserveparkingspot" type="hidden" style="width: 100px; margin-left: 30px; margin-top: 10px; ">
	<input name="reserveparkingspot" type="submit" value="Pay" style="width: 100px; margin-left: 30px; margin-top: 10px; " >
	<input name="paymentreset" type="reset" value="Back" style="width: 100px; margin-left: 30px; margin-top: 10px; ">
</form>
</div>
<script>
		function showpayment(){
		    document.getElementById("element2").style.display = "inline-block";
		    document.getElementByName("confirm").style.display = "none";
		    document.getElementByName("reset").style.display = "none";
		};


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