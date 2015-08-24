<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="utility.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prerequisite Information</title>
</head>
<body>
<h1>Prerequisite Information</h1>

<%	//Get Prereqs
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select distinct P.cid, P.prereq, C1.cid, C1.cname, C2.cid, C2.cname from prereq P, course C1, course C2 where P.cid=C1.cid AND P.prereq=C2.cid ORDER BY P.cid");
    %>

<table border="1">
<tr>
	<th align="center">Course Name</th>
	<th align="center">Course ID</th>
	<th align="center">Prerequisite</th>
	<th align="center">Prereq ID</th>
</tr>
<%while (resultset.next()) { %>
<tr>
	<td align="center"><% String cname = resultset.getString("C1.cname"); out.append(cname); %></td>
	<td align="center"><% String cid = resultset.getString("P.cid"); out.append(cid); %></td>
	<td align="center"><% String pname = resultset.getString("C2.cname"); out.append(pname); %></td>
	<td align="center"><% String pid = resultset.getString("P.prereq"); out.append(pid); %></td>
</tr>
<%} %>
</table>
<br>
<a href="/SPNumberDB/Student/WelcomeStudent.jsp">
<button>Back</button>
</a>

</body>
</html>