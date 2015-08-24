<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Load File</title>
</head>
<body>
<p><font color=red>${iMessage}</font></p>
<h1>Load Outside Database Information</h1>

<form name="file" action="/SPNumberDB/LoadFileServlet" method="post">

File Name:
<input name="filename"/>
<br><br>

Choose Information Type
<br>
<select name="type">
<option>Choose Type</option>
<option>Users</option>
<option>Courses</option>
<option>Prerequisites</option>
<option>Transcripts</option>
<option>Classes Taken</option>
</select>
<br><br>
If this is the first time loading files, load in this order:<br>
Users<br>
Courses<br>
Transcripts<br>
Prereqs<br>
Classes Taken
<br><br>
<input type="submit" value="Submit"/>
</form>
<br>
<a href="/SPNumberDB/admin/WelcomeAdmin.jsp">
<button>Cancel</button>
</a>
</body>
</html>