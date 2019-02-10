<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Register</h1>
<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<form name = "reg_form" action="registerUserController" method ="post">
<table>
<tr>
<td>First Name :</td><td><input type="text" name ="firstname" value="<c:out value='${user.firstname}'/>" /></td>
<td> <input name="firstNameError" value="<c:out value='${errorMsgs.firstnameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Last Name :</td><td><input type="text" name ="lastname" value="<c:out value='${user.lastname}'/>" /></td>
<td> <input name="lastNameError" value="<c:out value='${errorMsgs.lastnameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>User Name :</td><td><input type="text" name ="username" value="<c:out value='${user.username}'/>" ></td>
<td> <input name="userNameError" value="<c:out value='${errorMsgs.usernameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Password:</td><td><input type="password" name ="password" ></td>
<td> <input name="passwordError" value="<c:out value='${errorMsgs.passwordError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Confirm Password :</td><td><input type="password" name ="cpassword"></td>
<td> <input name="confirmPasswordError" value="<c:out value='${errorMsgs.confirmPwdError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>UTA ID:</td><td><input type="text" name ="utaid" value="<c:out value='${user.uta_id}'/>" ></td>
<td> <input name="utaIdError" value="<c:out value='${errorMsgs.utaIdError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>

</tr>
<tr>
<td>Email:</td><td><input type="text" name ="emailid" value="<c:out value='${user.email}'/>" ></td>
<td> <input name="emailError" value="<c:out value='${errorMsgs.emailError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Phone:</td><td><input type="text" name ="phone" value="<c:out value='${user.phone}'/>" ></td>
<td> <input name="phoneError" value="<c:out value='${errorMsgs.phoneError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Role :</td><td><select name="user_role" ><option>Student/Faculty<option>Manager<option>Admin</select></td>
</tr>
<tr>
<td>Street Address:</td><td><input type="text" name ="saddress" value="<c:out value='${user.street_add}'/>" ></td>
<td> <input name="streetAddrError" value="<c:out value='${errorMsgs.streetAddrError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>City:</td><td><input type="text" name ="city" value="<c:out value='${user.city}'/>" ></td>
<td> <input name="cityError" value="<c:out value='${errorMsgs.cityError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>State:</td><td><input type="text" name ="state" value="<c:out value='${user.state}'/>" ></td>
<td> <input name="stateError" value="<c:out value='${errorMsgs.stateError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Zip Code:</td><td><input type="text" name ="zip" value="<c:out value='${user.zip_code}'/>" ></td>
<td> <input name="zipCodeError" value="<c:out value='${errorMsgs.zipCodeError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Car Plate Number:</td><td><input type="text" name="plate_number" value="<c:out value='${user.car_plate_num}'/>" ></td>
<td> <input name="carNmbrError" value="<c:out value='${errorMsgs.carNmbrError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td>Permit ID:</td><td><input type="text" name="permit_id" value="<c:out value='${user.permit_id}'/>" ></td>
<td> <input name="permitIdError" value="<c:out value='${errorMsgs.permitIdError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<%-- <td>Permit type:</td><td><input type="text" name="permit_type" value="<c:out value='${user.permit_type}'/>" ></td>
 --%>
 <td>Permit type:</td><td><select name="user_role" ><option>Basic<option>Premium<option>Midrange<option>Access</select></td>
 </tr>
<!-- <tr>
<td><input type ="submit" value="register"></td><td><input type="reset" value="Cancel"></td>
</tr> -->
</table>
	<input name="action" value="insertUser" type="hidden">
	<input name="insertUser" type="submit" value="Register User">
	<input name="reset" type="reset" value="Reset">
</form>

</body>
</html>
