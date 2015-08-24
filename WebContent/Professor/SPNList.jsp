<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="com.spdb.*" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SPN List</title>
</head>
<body>

<h1>List of SPN Numbers</h1>
<br><br>

<table border="1">
		<tr>
		    <th>SPN</th>
		    <th>NetID</th>
			<th>Used/Free</th>
		</tr>
		<%
		String netid = (String) request.getSession().getAttribute("netid");
		DBConnection db = new DBConnection();
		Connection conn = db.openConnection();
		Statement statement = conn.createStatement(); //selects the courses that the teacher taught and that course is in the spnrequest
		ArrayList<String> SPNIDs = new ArrayList<String>();
		ResultSet resultset = statement.executeQuery("select s.spnNumber, used from spn s, course c where c.cid=s.cid and c.section=s.section and c.netid=\""+
		netid+"\"");
		
		while(resultset.next()) {
			String spn=resultset.getString("spnNumber");
			int used=resultset.getInt("used");
			String assignedID = "";
			
			if(used==1) {
				Statement statement2 = conn.createStatement();
				ResultSet resultset2 = statement2.executeQuery("Select s.netid from spn s where s.spnNumber=\""+spn+"\"");
				
				if(resultset2.next()){
					assignedID = resultset2.getString("netid");
				}
			}
			
			%>
			<tr>
				<td><%out.append(spn); %></td>
				<td><%if(!assignedID.equals("")){out.append(assignedID);} %></td>
				<td><%if(used==0) {%>free<%}else {%>assigned<%} %></td>
			</tr>
		<%}
		%>
</table>
<br><br>
<a href="/SPNumberDB/Professor/WelcomeProfessor.jsp">
<button>Back</button></a>
</body>
</html>