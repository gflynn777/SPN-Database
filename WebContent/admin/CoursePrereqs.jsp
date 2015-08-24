<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="utility.*" import="admin.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter Prereqs</title>
</head>
<body>
<p><font color=red> ${nuMessage}</font></p>
<form name="prereqs" action="/SPNumberDB/PrereqServlet" method="post">
<h1>Enter Prerequisites</h1>
<br>
Course Id: 
<jsp:scriptlet>
  out.append(request.getParameter("cid"));
</jsp:scriptlet>
<br><br>

<%	//Dropdown list for prereq1
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select distinct cid from course");
%>

	Prerequisite 1:
    <select name="prereq1">
    <option>Choose Course</option>
    <%  while(resultset.next()){ %>
        <option><%= resultset.getString(1)%></option>
    <% } %>
    </select>
<br><br>
<%ResultSet resultset2 = statement.executeQuery("select distinct cid from course"); %>
	Prerequisite 2:
    <select name="prereq2">
    <option>Choose Course</option>
    <%  while(resultset2.next()){ %>
        <option><%= resultset2.getString(1)%></option>
    <% } %>
    </select>
<br><br>
<%ResultSet resultset3 = statement.executeQuery("select distinct cid from course"); %>
	Prerequisite 3:
    <select name="prereq3">
    <option>Choose Course</option>
    <%  while(resultset3.next()){ %>
        <option><%= resultset3.getString(1)%></option>
    <% } %>
    </select>
    <br><br>
<input type="submit" value="Submit" />
</form>
</body>
</html>