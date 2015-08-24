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
<form name="course" action="/SPNumberDB/ProfCourseServlet" method="post">
	
	Course Name: <input name="cname" />
	<br><br>
	Course Id: <input name="cid"/>
	<br><br>
	Section: <input name="section" />
	<br><br>
<%	//Dropdown list for instructor
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select netid, fname, lname from user where isProf=1") ;
%>
	Instructor Id:
<!-- 	<table> -->
<!-- 	<select name="tid"> -->
<%--     <%  while(resultset.next()){ %> --%>
<%--         <option><%= resultset.getString(1)%></option> --%>
<%--     <% } %> --%>
<!--     </select> -->
    
    <select name="tid">
    <table>
    <%  while(resultset.next()){ %>
        <option><tr><td><%=resultset.getString("fname")%> </td><td><%=resultset.getString("lname")%> </td>
        <td><%=resultset.getString("netid")%> </td></tr></option>
    <% } %>
    </table>
    </select>
    
    
    
    <br><br>
	<input type="submit" value="Submit"/>
	</form>
	<br><br>
	
</form>
</body>
</html>