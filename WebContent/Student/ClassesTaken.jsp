<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="utility.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter Classes</title>
</head>
<body>
<p><font color=red>${iMessage}</font></p> 

<%	//Access GLOBAL VARIABLE cid
	String netid = (String) request.getSession().getAttribute("netid");
%>

<h1>Enter your Classes</h1>

Please enter the classes you have taken or are taken currently.
<br><br>

<form name="classes" action="/SPNumberDB/EnterClassServlet" method="post">

<%	//Dropdown list for course
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select distinct C.cid, C.cname from course C");
%>

Choose Course ID
<br>
<select name="course">
<option>Choose Course</option>
<% while (resultset.next()) { %>
<option> <% String cid = resultset.getString("C.cid");
			String cname = resultset.getString("C.cname");
			String combine = cname + " " + cid; out.append(combine);%> 
</option>
<% } %>
</select>
<br><br>
Grade: <input type="text" name="grade"/>
<br>
(Leave blank if currently taking class)
<br><br>
<input type="submit" value="Submit"/>
</form>
<br>
<a href="/SPNumberDB/Student/WelcomeStudent.jsp">
<button>Finish</button>
</a>

</body>
</html>