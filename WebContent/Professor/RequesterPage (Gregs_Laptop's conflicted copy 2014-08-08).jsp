<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.spdb.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Requester Page</title>
</head>
<body>
<%
	String sid = (String)request.getSession().getAttribute("sid");
	String professor = (String)request.getSession().getAttribute("netid");
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement();
    ResultSet resultset = statement.executeQuery("select s.major from student s where s.netid=\""+sid+"\"");
    Statement statement2 = conn.createStatement();
    ResultSet resultset2 = statement2.executeQuery("select s.minor from student s where s.netid=\""+sid+"\"");
    Statement statement3 = conn.createStatement();
    ResultSet resultset3 = statement3.executeQuery("select s.gpa from student s where s.netid=\""+sid+"\"");
    Statement statement4 = conn.createStatement();
    ResultSet resultset4 = statement4.executeQuery("select s.numCredits from student s where s.netid=\""+sid+"\"");
%>
<h1>Requester Page</h1>
Student Name: <%out.append((String)request.getSession().getAttribute("sname")); %>
<br><br>
Student Major: <% if (resultset.next())
					out.append(resultset.getString(1)); %>
<br><br>
Student Minor: <%if (resultset2.next())
					out.append(resultset2.getString(1));%>
<br><br>
Student GPA: <%if (resultset3.next())
					out.append(resultset3.getString(1));%>
<br><br>
Total Number of Credits: <%if (resultset4.next())
							out.append(resultset4.getString(1));%>

<br><br>
<%
		Statement statement5 = conn.createStatement();
		Statement statement6 = conn.createStatement();
		
		ResultSet resultset5 = statement5.executeQuery("select s.cid from spn_request s where s.netid=\""+sid+"\" and s.cid in"
				+ "(select t.cid from teaches t where s.cid=t.cid and t.netid=\""+professor+"\")");
		ResultSet resultset6 = statement6.executeQuery("select s.section from spn_request s where s.netid=\""+sid+"\" and s.cid in"
				+ "(select t.cid from teaches t where s.cid=t.cid and t.netid=\""+professor+"\")");
%>
<form>
<table>
		<%while (resultset5.next() && resultset6.next()) {%>
		<tr><td>Requested Course CID: </td><td><%=resultset5.getString(1)%></td><td>Section:</td><td><%=resultset6.getString(1)%></td></tr>
		<tr><td><input type="submit" value="Generate SPN#" name="gen" /> </td><td><input type="submit" value="Deny Request" name="deny" /></td></tr>
		<%}%>
</table>
</form>

<br><br>
SPN Numbers Left for this course:
<br><br>
<a href=/SPNumberDB/Professor/WelcomeProfessor.jsp>Go Back to Welcome Page</a>

<%	if (request.getParameter("gen") != null){
		//Assign Student an spn
		;
	}
		
	if (request.getParameter("deny") != null)
		//Deny Student's request
		;


%>



</body>
</html>