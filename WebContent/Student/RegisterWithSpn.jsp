<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="com.spdb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register for a Closed Class</title>
</head>
<body>
<p>
		<font color=red>${iMessage}</font>
	</p>
<h1>Register for a Closed Class</h1>
<%	//Dropdown list for course
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select distinct cid, cname from course order by cid") ;
 
%>

<form  name="course" action="/SPNumberDB/RegisterClassServlet" method="post">
	Course Id:
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
	SPN: <input name="spn" />
	<br><br>
	<input type="submit" value="Submit"/>

</form>
<br>
<a href="/SPNumberDB/Student/WelcomeStudent.jsp">
<button>Cancel</button>
</a>

</body>
</html>