<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*" import="com.spdb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SPN Status</title>
</head>
<body>

	<%
		//Access GLOBAL VARIABLE netid
		String netid = (String) request.getSession().getAttribute("netid");
	%>
	<p>
		<font color=red>${iMessage}</font>
	</p>
	<p>
		<font color=red>${wMessage}</font>
	</p>
	<h1>Status of SPN Requests</h1>
	<table border="1">
		<tr>
			<th>SPN Number</th>
			<th>Course ID</th>
			<th>Section</th>
			<th>Time Requested</th>
			<th>Status</th>
			<th>Comments</th>
		</tr>

		<%
			//Get SPN Numbers
			String comment = ""; //for use later
			DBConnection db = new DBConnection();
			Connection conn = db.openConnection();
			Statement statement = conn.createStatement();
			ResultSet resultset = statement //get the all the student's spn request
					.executeQuery("select cid, section, date_stamp, filed, denied, comment from spn_request where netid=\""
							+ netid + "\"");
		
			while (resultset.next()) { //for each request
			String cid = resultset.getString("cid");
				String section = resultset.getString("section");
				String date = resultset.getString("date_stamp");
				int filed = resultset.getInt("filed");
				int denied = resultset.getInt("denied");
				String SPN;
				int used = 0;
				comment = resultset.getString("comment");
		%>
		<tr>
			<td>
				<%
					Statement statement2 = conn.createStatement(); //gets spnNumber, used, denied 
						ResultSet resultset2 = statement2.
								executeQuery("select spnNumber, used from spn where netid=\""
										+ netid
										+ "\" AND cid=\""
										+ cid
										+ "\" AND section=\"" + section + "\"");

						if (!resultset2.next()) { //if no spnnumber
							out.append("N/A");
						} else {
							SPN = resultset2.getString("spnNumber");
							used = resultset2.getInt("used");
							out.append(SPN);
						}
				%>
			</td>
			<td>
				<%
					out.append(cid);
				%>
			</td>
			<td>
				<%
					out.append(section);
				%>
			</td>
			<td>
				<%
					out.append(date);
				%>
			</td>
			<td>
			
			<%
			if(filed == 0) {
				%>Pending<% //if not used, must be pending
			} else {
				if (denied==1) {
					%>Denied<%
				} else {
					%>Approved<%
				}
			} //end if pending or filed
			%>
			</td>
			<td><%if (!comment.equals("null")) out.append(comment); %></td>
		</tr>
		<%
			}//end while
		%>
	</table>
	<br>
	<br>

	<a href="/SPNumberDB/Student/NewRequest.jsp">Request an SPN</a>
	<br>
	<a href="/SPNumberDB/Student/ModifySPN.jsp">Delete an SPN request</a>
	<br>
	<br>
	<a href="/SPNumberDB/Student/WelcomeStudent.jsp">
		<button>Back</button>
	</a>

</body>
</html>