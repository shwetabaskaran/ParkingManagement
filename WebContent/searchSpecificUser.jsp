<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.tabcontent {
  padding: 6px 12px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Details</title>
<script>
function onbodyload()
{
	var user = '${user_role}';
	document.getElementById("changeuserrole").style.display='none';
	document.getElementById("changeviolations").style.display='none';
	if(user === "Man"){
		document.getElementById("edituserrole").style.display='none';}
	if(user === "admin"){
	document.getElementById("setnoshow").style.display='none';
	document.getElementById("setoverdue").style.display='none';	
	document.getElementById("editviolations").style.display='none';
	}
}
function viewUserrole()
{
document.getElementById("userinfotable").style.display='none';	
document.getElementById("changeuserrole").style.display='block';
}
function editViolations()
{
document.getElementById("changeviolations").style.display='block';
document.getElementById("userinfotable").style.display='none';
}
</script>
</head>
<body onload="onbodyload();">
<table>
<tr>
<td class="tabcontent"><a href='${home}'>Home</a></td> 
<td><a href="LogoutController">Logout</a></td>
				 </tr></table>
<div id="changeviolations">
<form action="searchSpecificUserController?action=editUserviolations" method="post">
<table>
<tr><td>No Shows:</td><td><input name="noshows" type="text" value='${search_user.getNoshows()}'></td></tr>
<tr><td>Overdue:</td><td><input name="overdue" type="text" value='${search_user.getOverdue()}'></td></tr>
<tr><td><input type="submit" value="Update"></td></tr>
</table>
</form>

</div>

<div id="changeuserrole" ><form action="searchSpecificUserController?action=editUserRole" method="post"><table  title="Change User Role">
<tr><td>Username:</td><td>${search_user.username}</td></tr>
<tr><td>User Role:</td><td><select name="user_role" ><option>Student/Faculty<option>Manager<option>Admin</select></td></td></tr>
<tr><td><input type="Submit" value="Change"></td></tr>
</table></form></div>
<div  id="userinfotable">
<h1>User Details of ${search_user.firstname} ${search_user.lastname}</h1>

<table>
				 <tr><td>Username:</td><td>${search_user.username}</td></tr>
				 <tr><td>User Role:</td><td>${search_user.role}</td><tr>
				 <tr><td>Firstname:</td><td>${search_user.firstname}</td></tr>
				 <tr><td>Lastname:</td><td>${search_user.lastname}</td></tr>
				 <tr><td>UTA ID:</td><td>${search_user.uta_id}</td></tr>
				 <tr><td>Email:</td><td>${search_user.email}</td></tr>
				 <tr><td>Phone:</td><td>${search_user.phone}</td></tr>
				 <tr><td>Street Address:</td><td>${search_user.street_add}</td></tr>
				 <tr><td>City:</td><td>${search_user.city}</td></tr>
				 <tr><td>State:</td><td>${search_user.state}</td></tr>
				 <tr><td>Zip:</td><td>${search_user.getZip_code()}</td></tr>
				 <tr><td>Permit ID:</td><td>${search_user.permit_id}</td></tr>
				 <tr><td>Permit type:</td><td>${search_user.permit_type}</td></tr>
				 <tr><td>Car Number Plate:</td><td>${search_user.getCar_plate_num()}</td></tr>
				 <tr><td>No Shows:</td><td>${search_user.getNoshows()}</td></tr>
				 <tr><td>Overdue:</td><td>${search_user.getOverdue()}</td></tr>
				 </table>
	<table><tr><td><button id ="edituserrole" onclick="viewUserrole();">Edit User role</button></td><td>
	<form action="searchSpecificUserController?action=setNoshow" method="post" onsubmit="return confirm('Confirmation required');"><input id="setnoshow" type="submit" value="Set No Show"></form></td>
	<td><form action="searchSpecificUserController?action=setOverdue" method="post" onsubmit="return confirm('Confirmation required');"><input id="setoverdue" type="submit" value="Set Overdue"></form></td>
<td><button id="editviolations"  onclick="editViolations();">Edit Violations</button></tr>
</table></div></body>
</html>