<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="utility.*" %>
<%!String cid = "999"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Course</title>
</head>
<body>
<p><font color=red>${nuMessage}</font></p>
<h1>Add a Course</h1>
<form name="course" action="/SPNumberDB/CourseServlet" method="post">
	Course Name: <input name="cname" />
	<br><br>
	Course Id: <input name="cid"/>
	<br><br>
	Section: <input name="section" />
	<br><br>
	Room Size: <input name="rmsize" />
	<br><br>
<%	//Dropdown list for instructor
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select netid from user where isProf=1") ;
%>
	<input type="submit" value="Submit"/>
	</form>
	<br><br>
<br>
<a href="/SPNumberDB/Professor/WelcomeProfessor.jsp">
<button>Back</button>
</a>
</body>
</html>