<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User Registration</title>
</head>
<body>
<p><font color=red> ${nuMessage}</font></p>
<h1>Please Enter Your Information:</h1>
<br><br>
<form name="registration" action = "RegServlet" method="post">
<table cellpadding="5" cellspacing="5">
	<tr>
		<td>First Name</td>	
		<td><input type="text" name="fName" /></td>
	</tr>
	<tr>
		<td>Last Name</td>	
		<td><input type="text" name="lName" /></td>
	</tr>
	<tr>
		<td>Email</td>	
		<td><input type="text" name="email" /></td>
	</tr>
	<tr>
	<tr>
		<td>RUID</td>	
		<td><input type="text" name="ruid" /></td>
	</tr>
	<tr>
		<td>New Password</td>
		<td><input type="text" name="password" /></td>
	</tr>
	<tr>
		<td><input type="radio" name="profBool" id="Student" value=0><label for="Student">Student</label>
			<input type="radio" name="profBool" id="Professor" value=1><label for="Professor">Professor</label></td>
	</tr>

	<tr>
		<td><button type="submit">Submit</button></td>
	</tr>

</table>
</form>
</body>
</html>