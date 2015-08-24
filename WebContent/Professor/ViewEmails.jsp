<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*" import="com.spdb.*"
	import="java.util.ArrayList" import="java.util.HashSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Emails</title>
</head>
<body>
	<h1>View Email Addresses of SPN Requesters</h1>
	<table border="1">
		<tr>
		    <th>Name</th>
		    <th>NetID</th>
			<th>Email</th>
		</tr>
		<%
		try{
		//select distinct netid, email from user U, spnrequest S, course C, where U.netid=S.netid and
		//S.cid=C.cid AND S.section = C.section AND C.netid=netid
			String netid = (String) request.getSession().getAttribute("netid");
			DBConnection db = new DBConnection();
			Connection conn = db.openConnection();
			Statement statement = conn.createStatement(); //selects the courses that the teacher taught and that course is in the spnrequest
			ArrayList<String> SPNIDs = new ArrayList<String>();
			ResultSet resultset = statement.executeQuery("select C.cid from course C where C.netid=\"" +netid+"\"");
			Statement statement4 = conn.createStatement();
			ResultSet resultset4 = statement4.executeQuery("select distinct S.netid from spn_request S where S.cid= any(select C.cid from course C where C.netid=\"" +netid+"\")");
			while (resultset4.next()) {
				String ID = resultset4.getString("netid");
				boolean exists = false;
				for (int x = 0; x < SPNIDs.size(); x++) {
					if (SPNIDs.get(x).equalsIgnoreCase(ID)) {
						exists = true; 
					}
				}
				if(!exists) {
					SPNIDs.add(ID);
					//System.out.println(ID);
				}
			}//end while result2 to get all netids
			
			for(int i = 0; i < SPNIDs.size(); i++) { //for each netid
				String currID = SPNIDs.get(i);
				Statement statement3 = conn.createStatement();
				ResultSet resultset3 = statement3.executeQuery("Select U.netid,U.fname,U.lname,U.email from user U where U.netid=\""+currID+"\"");
				resultset3.next();
				String first = resultset3.getString("fname");
				String last = resultset3.getString("lname");
				String name = (first + " " + last);
	            String email = resultset3.getString("email");
				%>
			<tr>
					<td><%out.append(first+" "+last); %>	</td>
					<td><%out.append(currID); %>	</td>
					<td><%out.append(email); %>	</td>			
				 </tr>
					
				<%}//end for 
				}catch (SQLException e){
				//e.printStackTrace();
				}%>
				
	</table>

	<br>
	<br>
	<a href="/SPNumberDB/Professor/WelcomeProfessor.jsp">
		<button>Back</button>
	</a>

</body>
</html>
