<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<tITle>Special Permission Numbers</title>
</head> 
<body>
<div id=header><img src=images/rutgerslogo.jpg height=15% width=45% ></div>
<p><font color=red>${iMessage}</font></p> 
<h1>Welcome to the Special Permission Number Database</h1>
<form name="user" action="WelcomeServlet " method="post">
	NetId: <input name="userId" />
	<br><br>
	Password: <input name="password" />
	<br><br>
	<input type="submit" value="Submit" />
</form>
<a href="./newuser.jsp">Create a new account</a>
<br><br><br>
Notes to Grader:<br>
To access admin page - Enter Netid: gf1	Password: super<br>
To access Professor Page - Create a New Account or Enter Netid: gf2 Password: super<br>
To access Student Page - Create a New Account or Enter Netid: gf4 Password: super
</body>
</html>