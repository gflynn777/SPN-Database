<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.spdb.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Administrator</title>
</head>
<body>
<p><font color=red>${wMessage}</font></p>
<h1>Create a new Administrator</h1>
<br>
<form name="admin" action="/SPNumberDB/AdminServlet" method=post>

<%	//Dropdown list for instructor
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select netid from user") ;
%>
    <select name="netid">
    <option>Choose NetId of Administrator</option>
    <%  while(resultset.next()){ %>
        <option><%= resultset.getString(1)%></option>
    <% } %>
    </select>
	<br><br>
	<input type="submit" value="Submit" name="btnSubmit" />

<%
		if (request.getParameter("btnSubmit") != null)
		{
			String nuMessage = request.getParameter("netid")+ " now has administrator priveledges";
			request.setAttribute("nuMessage", nuMessage);
			request.getRequestDispatcher("./WelcomeAdmin.jsp").forward(request, response);
		}
%>
</form>
</body>
</html>