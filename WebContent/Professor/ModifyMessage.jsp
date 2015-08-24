<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="com.spdb.*" %>
<%!String cid = "999"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Modify Default Response </title>
</head>
<body>
<p><font color=red>${iMessage}</font></p> 
<H1> Modify Default Response To Students' Requests</H1>
<form name="messager" method="post" action="/SPNumberDB/addMessageServlet">
    Message <input name="message" />
<input type="submit" value="Submit"/>
</form>
<br>
<a href="/SPNumberDB/Professor/WelcomeProfessor.jsp">
<button>Back</button>
</a>
</body>
</html>