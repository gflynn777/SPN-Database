<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Student</title>
</head>
<body>
<div align="right">
<a href="/SPNumberDB/index.jsp"><button>LOG OUT</button></a>
</div>
<p><font color=red>${wMessage}</font></p>
<h1>Welcome Student</h1>
NetID: <% String netid = (String) request.getSession().getAttribute("netid"); out.append(netid);%>
<br><br>
<a href=/SPNumberDB/Student/NewRequest.jsp>&#9702; New SPN Request</a>
<br><br>
<a href=/SPNumberDB/Student/SPNStatus.jsp#>&#9702; Status of Request(s)</a>
<br><br>
<a href=/SPNumberDB/Student/ModifySPN.jsp>&#9702; Modify a Pending Request</a>
<br><br>
<a href=/SPNumberDB/Student/PrereqInfo.jsp>&#9702; View Class Prerequisite Information</a>
<br><br>
<a href=/SPNumberDB/Student/ViewTranscript.jsp>&#9702;View Transcript</a>
<br><br>
<a href=/SPNumberDB/Student/RegisterWithSpn.jsp>&#9702;Register for a Class</a>
</body>
</html>