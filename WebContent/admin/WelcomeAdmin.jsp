<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<p><font color=red>${nuMessage}</font></p> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Admin</title>
</head>
<body>
<p><font color=red>${wMessage}</font></p>
<div align="right">
<a href="/SPNumberDB/index.jsp"><button>LOG OUT</button></a>
</div>
<h1>Welcome Administrator</h1>

<a href=/SPNumberDB/admin/NewAdmin.jsp>&#9702;Create new administrator</a>
<br><br>
<a href=/SPNumberDB/admin/NewCourse.jsp>&#9702;Add new course</a>
<br><br>
<a href=/SPNumberDB/admin/DeleteCourse.jsp>&#9702;Delete a course</a>
<br><br>
<a href=/SPNumberDB/admin/LoadFile.jsp>&#9702;Load a file into the Database</a>
<br><br>
<a href=/SPNumberDB/admin/DeleteTable.jsp>&#9702;Delete the Contents of a Table</a>

</body>
</html>