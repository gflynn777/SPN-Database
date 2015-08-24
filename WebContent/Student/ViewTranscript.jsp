<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="utility.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transcript</title>
</head>
<body>
<h1>Transcript Record</h1>

<%	//Access GLOBAL VARIABLE netid
	String netid = (String) request.getSession().getAttribute("netid");
%>
<h3>Transcript Information</h3>
<table border="1">

<tr>
<th>NetId</th>
<th>Major</th>
<th>Minor</th>
<th>GPA</th>
<th># Credits</th>
<th># CS Credits</th>
</tr>

<tr>
<td><%out.append(netid);%></td>

<%	//Get SPN Numbers
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select netid, Major, Minor, gpa, numCreds, csCreds from transcript where netid=\""+netid+"\"");
    resultset.next();
    %>
    
<td><%String major = resultset.getString("Major"); out.append(major);%></td>
<td><%String minor = resultset.getString("Minor"); out.append(minor);%></td>
<td><%String gpa = resultset.getString("gpa"); out.append(gpa);%></td>
<td><%String numCreds = resultset.getString("numCreds"); out.append(numCreds);%></td>
<td><%String csCreds = resultset.getString("csCreds"); out.append(csCreds);%></td>
</tr>
</table>

<%	//Get SPN Numbers
	DBConnection db2 = new DBConnection();
	Connection conn2 = db.openConnection();
    Statement statement2 = conn.createStatement() ;
    ResultSet resultset2 = statement2.executeQuery("select cid, grade from classes where netid=\""+netid+"\"");
    %>


<br>
<h3>Classes Taken</h3>
<table border="1">
<tr>
<th>Course ID</th>
<th>Grade</th>
</tr>
<%while(resultset2.next()) { %>
<tr>
<td><%String courseID = resultset2.getString("cid"); out.append(courseID); %></td>
<td><%String grade = resultset2.getString("grade"); if(grade == null || grade.equals("")) {out.append("N/A");}else {out.append(grade);} %></td>
</tr>
<%} %>
</table>
<br>
<a href="/SPNumberDB/Student/WelcomeStudent.jsp">
<button>Back</button>
</a>
</body>
</html>