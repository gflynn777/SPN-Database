<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="com.spdb.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Request</title>
</head>
<body>
<p><font color=red>${wMessage}</font></p>
<p><font color=red>${iMessage}</font></p> 
<h1>Enter a New Request</h1>
<form name="Request" action="/SPNumberDB/NewRequestServlet" method="post">
Student Id:
<%	//Access GLOBAL VARIABLE cid
	String netid = (String) request.getSession().getAttribute("netid");
	out.append(netid);
%>
<br><br>
Choose Course ID:
<%	//Dropdown list for course
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select distinct c.cid, c.cname from course c where c.cid not in"
    		+ "( Select s.cid from spn_request s where s.netid=\""+netid+"\") AND c.cid not in"
    		+ "(select x.cid from classes x where x.netid=\""+netid+"\") order by cid");//Does not allow user to make duplicate requests
%>
    <select name="cid">
    <option>Choose Course</option>
    <%  while(resultset.next()){ 
    	String cname = resultset.getString("cname");
    	String cid = resultset.getString("cid");
    	String combine = (cname + " " + cid);
    %>
        <option><%out.append(combine);%></option>
    <% } %>
    </select>
	<br><br>
	Section: <input name="section" size=2 />
	<br><br>
	<input type="submit" value="Submit"/>
</form>
<br>
<a href="/SPNumberDB/Student/WelcomeStudent.jsp"><button>Cancel</button></a>
</body>
</html>