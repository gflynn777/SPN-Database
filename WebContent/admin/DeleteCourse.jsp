<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="utility.GetsSets" import="utility.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Course</title>
<h1>Delete a Course</h1>


<%	//Dropdown list for course
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement = conn.createStatement() ;
    ResultSet resultset = statement.executeQuery("select distinct cid from course order by cid") ;
    String cid = null;
%>

<form action="./DeleteSection.jsp" method=GET name="course" method="post">
	Course Id:
    <select type=submit name="cid" onchange="this.form.submit()">
    <option>Choose Course</option>
    <%  while(resultset.next()){ %>
        <option><%= resultset.getString(1)%></option>
    <% } %>
    </select>
    </form>
	<br><br>
</head>
<body>

</body>
</html>