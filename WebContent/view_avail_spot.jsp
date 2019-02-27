<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<button id="makespotunavail" onclick="display_make();">Make spot unavailable</button>
<p id="demo"></p>
<div id="viewavailspot">
<form action="viewAvailSpotController?action=noavailspots" method="post">
<table>
<tr><td>Parking area name:</td><td><select name="parkingareaname">
<c:forEach items="${parkingAreaNames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td></tr>
<tr><td>From time:</td><td><input type="text" name="fromtime"></td></tr>
<tr><td>To time:</td><td><input type="text" name="totime"></td></tr>
<tr><td>Permit type:</td><td><select name="permit_type" ><option>Basic<option>Premium<option>Midrange<option>Access</select></td></tr>
<tr><td><input type="submit"  value="Search"></td></tr>
</table>
</form>
<c:choose>
<c:when test="${ avail_spots ne 0}">
	<div id="avail_spots" style="display:block;">
		<p> The availability is : ${avail_spots}</p>
	</div>
</c:when>
<c:otherwise>
	<div id="avail_spots" style="display:none;">
		<p> The availability is : ${avail_spots}</p>
	</div>
</c:otherwise>
</c:choose>
</div>
<div id="makespotunavailable" style="display:none;">
<form action="viewAvailSpotController?action=makeunavailable" method="post" onsubmit="return confirm('Confirmation Required')">
<table>
<tr><td>Parking area name:</td><td><select name="parkingareaname">
<c:forEach items="${parkingAreaNames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td><td>Permit type:</td><td><select name="type" ><option>Basic<option>Premium<option>Midrange<option>Access</select></td>
<td><input type="text" name="spotno"></td></tr>
<tr><td><input type="submit" value="Save"></td></tr>
</table>
</form>
</div>
<form action="viewAvailSpotController?action=listavailable" method="post"><input type="submit" value="Unavailable List"></form>
<div id="listunavailable">
<form action ="viewAvailSpotController?action=removeunavail" method ="post" onsubmit="return confirm('Confirmation Required')">
<table>
<tr><th>Parking Area Name</th><th>Type</th><th>Spot No</th></tr>
<c:forEach items="${unavailable_list}" var="unavailspot">

	<tr>
	<td><input type="text" value='${unavailspot.parkingName}' name="parking_name" READONLY style="border:none;text-align:center;"></td>
	<td><input type="text" name="parking_type" value='${unavailspot.type}' READONLY style="border:none;text-align:center;"></td>
	<td><input type="text" value='${unavailspot.getSpot_no()}' name="spot_num" READONLY style="border:none;text-align:center;"></td>
	<td><input type="submit" value="Make Spot Available"></td>
	</tr>
</c:forEach>
</table>
</form>

</div>
</body>
<script>
function display_make() {
	document.getElementById("makespotunavailable").style.display = 'block';
	document.getElementById("viewavailspot").style.display = 'none';
	document.getElementById("makespotunavail").style.display= 'none';
	document.getElementById("listunavailable").style.display= 'none';
}

function avail()
{
	var avail =4;
	if(avail>0){
		document.getElementById("avail_spots").style.display='block';
	}
	
}
</script>
</html>

