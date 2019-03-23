<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Available spots and make spot unavailables</title>
<script type="text/javascript">
function display_make()
{
document.getElementById("makespotunavailbut").style.display = 'none';
document.getElementById("makespotunavailable").style.display = 'block';
document.getElementById("viewavailspot").style.display = 'none';
}

function avail()
{
	var mode = '${modes}';
	if(mode === 'makeunavail' )
		display_make();
	
	/*var avail =${avail_spots};
	if(avail>0){
		document.getElementById("avail_spots").style.display='block';
	}*/
	
}
//function for displaying making spot unavailable

</script>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
</head>
<body onload="avail();">
<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<div id="viewavailspot">
<h1>View Available Spots</h1>
<form action="viewAvailSpotController?action=noavailspots" method="post">
<table>
<tr><td>Parking area name:</td><td><select name="parkingareaname">
<c:forEach items="${parkingAreaNames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td></tr>
<tr><td>From time:</td><td><input type="text" name="fromtime"></td>
<td> <input name="reservationFromError" value="<c:out value='${noavailerror.fromErrMsg}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr><td>To time:</td><td><input type="text" name="totime"></td><td> <input name="reservationToError" value="<c:out value='${noavailerror.toErrMsg}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr><td>Permit type:</td><td><select name="permit_type" ><option>Basic<option>Premium<option>Midrange<option>Access</select></td></tr>
<tr><td><input type="submit"  value="Search"></td></tr>
</table>
</form>
<div id="avail_spots">
<p> The availability is : ${avail_spots}</p>
<button id="makespotunavailbut" onclick="display_make()">Make a spot unavailable</button>
<br/>
<br/>
</div>
</div>

<div id="makespotunavailable" style="display:none">
<h1>Make Spot Unavailable</h1>
<form action="viewAvailSpotController?action=makeunavailable" method="post" onsubmit="return confirm('Confirmation required')">

<table>
<tr><td>Parking area name:</td><td><select name="parkingareaname">
<c:forEach items="${parkingAreaNames}" var="parkingareaname">
<option value='${parkingareaname }'>${parkingareaname }</option>
</c:forEach>
</select></td><td>Permit type:</td><td><select name="type" ><option>Basic<option>Premium<option>Midrange<option>Access</select></td>
<td>Spot No:<td><input type="text" name="spotno"></td>
<td> <input name="UspotErrMsg" value="<c:out value='${makespotunavailerror.getspotNumErrMsg()}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td></tr>
<tr><td><input type="submit" value="Make Spot Unavailable"></td></tr>
</table>

</form>
</div>
<form action="viewAvailSpotController?action=listavailable" method="post"><input type="submit" value="Unavailable List"></form>

<div id="listunavailable">
<c:if test="${!empty unavailable_list}" >
<table><tr><th>Parking Area Name</th><th>Type</th><th>Spot No</th></tr>
<c:forEach items="${unavailable_list}" var="unavailspot">
<form action ="viewAvailSpotController?action=removeunavail" method ="post" >
<tr>
<td>
<input type="text" value='${unavailspot.parkingName}' name="parking_name" READONLY>
</td>
<td>
<input type="text" name="parking_type" value='${unavailspot.type}' READONLY>
</td><td><input type="text" value='${unavailspot.getSpot_no()}' name="spot_num" READONLY></td>
<td><input type="submit" value="Make Available"></td></tr></form>
</c:forEach>
</table>
</c:if>
</div>
</body>
</html>
