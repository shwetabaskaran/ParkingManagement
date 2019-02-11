<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
<h1>Login page</h1>
<form action="loginUserController" method="post">
<table>
<tr>
<td>Username:</td><td><input type="text" name="login_username"></td><td><p style="color:red;">${incorrectpass.userNameErrMsg}</p></td></tr>
<tr>
<td>Password:</td><td><input type="password" name="login_password"></td></tr>
<tr><td><input type="submit" value="Login"></td></tr>
</table>
<p style="color:red;">${errorMessage.userNameErrMsg}</p>
</form>

<a href="register_user.jsp">Register here !!</a>

</body>
</html>