<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="utility.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Table Contents</title>
</head>
<body>
<h1>Delete the Contents of a Table</h1>
</body>

<%	//Dropdown list for  tables
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES"
    		+ " WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA=\"spdb\"");
    String cid = null;
%>
<p><font color=red>WARNING! This action cannot be undone!</font></p>
<form action="/SPNumberDB/AdminServlet" method=get name="table">
	Choose Table:
    <select type=submit name="table">
    <%  while(resultset.next()){ %>
        <option><%= resultset.getString(1)%></option>
    <% } %>
    </select>
    <br><br>
    <input type="submit" value="Submit" />
    </form>
    <br>
    Note: For this to work Safe Update must not be selected in: 
    <br> MySql -> Preferences -> SQL Queries
	<br><br>
	If deleting all tables, delete in this order:<br>
	Classes<br>
	Teaches<br>
	Transcript<br>
	HasA<br>
	MakesRequest<br>
	Enrolled<br>
	Professor<br>
	Student<br>
	SpnRequest<br>
	Spn<br>
	Prereq<br>
	Course<br>
	User<br><br>
	
</html>