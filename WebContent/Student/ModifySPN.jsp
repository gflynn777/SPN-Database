<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="com.spdb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify SPN</title>
</head>
<body>
<p><font color=red>${iMessage}</font></p> 
<h1>Delete SPN</h1>

<%	//Access GLOBAL VARIABLE netid
	String netid = (String) request.getSession().getAttribute("netid");
%>

<%	//Get SPN Numbers
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select cid, section from spn_request where netid=\""+netid+"\"");
    %>
    
<form name="delete" action="/SPNumberDB/SPNDelete" method="post">
Choose SPN
<select name="SPN">
<option>Choose SPN</option>
<% while (resultset.next()){
	String cid = resultset.getString("cid");
	String section = resultset.getString("section");
	%>
	<option>Course ID: <%out.append(cid);%> Section: <%out.append(section); %></option>

<%} %>
</select>
<br><br>
	<input type="submit" value="Submit"/>
</form>
<br>
  <a href="/SPNumberDB/Student/WelcomeStudent.jsp">
	<button>Cancel</button>
</a>  

</body>
</html>