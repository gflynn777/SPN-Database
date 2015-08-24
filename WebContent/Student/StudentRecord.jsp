<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Records</title>
</head>
<body>
<p><font color=red>${iMessage}</font></p> 
<h1>Student Records</h1>
<form name="records" action="/SPNumberDB/RecordsServlet" method="Post">
Enter Major: <input name="major"/>
<br><br>
Enter Minor: <input name="minor" />
<br><br>
Enter GPA: <input name="gpa" />
<br><br>
Enter Total Credits: <input name="numCredits" />
<br><br>
Enter Total CS Credits: <input name="CSCredits" />
<br><br>
<input type="submit" value="Submit"/>
</form>
</body>
</html>