<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html><head>
				 <script text="text/javascript">
				 function editMode(){
				 document.getElementById('firstname').disabled=false;
				 document.getElementById('lastname').disabled=false;
				 document.getElementById('uta_id').disabled=false;
				 document.getElementById('email').disabled=false;
				 document.getElementById('phone').disabled=false;
				 document.getElementById('saddress').disabled=false;
				 document.getElementById('city').disabled=false;
				 document.getElementById('state').disabled=false;
				 document.getElementById('zip').disabled=false;
				 document.getElementById('permit_id').disabled=false;
				 document.getElementById('permit_type').disabled=false;
				 document.getElementById('num_plate').disabled=false;
				 document.getElementById('password').disabled=false;
				 document.getElementById('update').disabled=false;
				 document.getElementById('edit').style.display='none';
				 }
				 </script></head>
				 <body><form action='UpdateProfileController' method='post'><table>
				 <tr><td>Username:</td><td><input id='username'name='username' type='text' value= '${my_profile.username}' READONLY></td></tr>
				 <tr><td>Password:</td><td><input id='password'name='password' type='text' value=${my_profile.password} disabled></td></tr>
				 <tr><td>User Role:</td><td><input id='user_role' type='text'name='user_role' value=${my_profile.role} READONLY></td><tr>
				 <tr><td>Firstname:</td><td><input id='firstname'name='firstname' type='text' value=${my_profile.firstname} disabled></td></tr>
				 <tr><td>Lastname:</td><td><input type='text' id='lastname' name='lastname' value=${my_profile.lastname} disabled></td></tr>
				 <tr><td>UTA ID:</td><td><input type='text' id='uta_id' name='uta_id' value=${my_profile.uta_id} disabled></td></tr>
				 <tr><td>Email:</td><td><input type='text' id='email' name='email' value=${my_profile.email}  disabled></td></tr>
				 <tr><td>Phone:</td><td><input type='text' id='phone' name='phone' value=${my_profile.phone}  disabled></td></tr>
				 <tr><td>Street Address:</td><td><input type='text' id='saddress' name='saddress' value=${my_profile.street_add}  disabled></td></tr>
				 <tr><td>City:</td><td><input type='text' id='city' name='city' value=${my_profile.city}  disabled></td></tr>
				 <tr><td>State:</td><td><input type='text' id='state' name='state' value=${my_profile.state}  disabled></td></tr>
				 <tr><td>Zip:</td><td><input type='text' id='zip' name='zip'  disabled></td></tr>
				 <tr><td>Permit ID:</td><td><input id='permit_id' type='text' name=${my_profile.permit_id}  value='user.getPermit_id()' disabled></td></tr>
				 <tr><td>Permit type:</td><td><input id='permit_type' type='text' name=${my_profile.permit_type}  value='user.getPermit_type()' disabled></td></tr>
				 <tr><td>Car Number Plate:</td><td><input id='num_plate' type='text' name='car_num_plate' disabled></td></tr>
				 <tr><td><input id='update' type='submit' value='Update' disabled></td></tr>
				 </table></form>
				 <button id='edit' onclick='editMode();'>Edit</button></body></html>
