<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function avail()
{
	var avail =${avail_spots};
	if(avail>0){
		document.getElementById("avail_spots").style.display='block';
	}
	
}
</script>
</head>
<body onload="avail();">
<form action="ViewAvailSpotController" method="post">
<table>
<tr><td>Type:</td><td><select name="parkingareaname">
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
<div id="avail_spots" style="display:none;">
<p> The availability is : ${avail_spots}</p>
</div>
</body>
</html>