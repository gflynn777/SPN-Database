<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="utility.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Professor</title>
</head>
<body>
<p><font color=red>${nuMessage}</font></p>
<%System.out.println("Signed in as: "+(String)request.getSession().getAttribute("netid")); %>
<div align="right">
<a href="/SPNumberDB/index.jsp"><button>LOG OUT</button></a>
</div>
<h1>Welcome Professor</h1>

<%	//Dropdown list for instructor
	DBConnection db = new DBConnection();
	Connection conn = db.openConnection();
    Statement statement2 = conn.createStatement();
    String professor = (String)request.getSession().getAttribute("netid");
     
    ResultSet resultset2 = statement2.executeQuery("select u.fname, u.lname, u.netid, x.rank from user u, student x where x.netid=u.netid and u.netid in" 
    		+ "( select s.netid from spn_request s where s.filed=0 and s.netid=u.netid and s.cid in"
    		+ "( select c.cid from course c where c.netid=\""+professor+"\")) order by x.rank DESC");
  
%>
<form name=studentRequest action=/SPNumberDB/FillReqServlet method=post>
<a href=/SPNumberDB/Professor/ProfNewCourse.jsp>&#9702;Add Course</a>
<br><br>
<a href=/SPNumberDB/Professor/ModifyMessage.jsp >&#9702;Modify Initial Reply Status Message to Student</a>
<br><br>
<a href=/SPNumberDB/Professor/SPNList.jsp>&#9702;Get List of SP#s (indicating to whom assigned or free)</a>
<br><br>
<a href=/SPNumberDB/Professor/ViewEmails.jsp>&#9702;Get List of Email Addresses of Applicants</a>
<br><br>
&#9702;Students with Open Requests:
    <select name="student" onchange="this.form.submit()">
    <table><option>Choose a Student</option>
    <%  while(resultset2.next()){ %>
        <option><tr><td>Name: <%=resultset2.getString("fname")%> </td><td><%=resultset2.getString("lname")%> </td><td> NetId: <%=resultset2.getString("netid")%></td><td> Rank: <%=resultset2.getString("rank")%></tr></option>
    <% } %>
    </table>
    </select>
    </form>
</body>
</html>