<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.spdb.*" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose Section</title>
</head>
<body>
<h1>Choose Section to Delete</h1>

<%String cid = request.getParameter("cid");
request.setAttribute("cid", cid);%>
Course ID: ${cid}

<%	//Dropdown list for course
	if (request.getParameter("section") == null)
	{
		DBConnection db = new DBConnection();
		Connection conn = db.openConnection();
	    Statement statement = conn.createStatement() ;
	    ResultSet resultset = statement.executeQuery("select section from course where cid=\""+ cid+ "\"");
	    String section = null;
	    session.setAttribute("cid", request.getParameter("cid"));
%>
		<form name="section">
			Course Id:
		    <select name="section">
		    <option>Choose Section</option>
		    <%  while(resultset.next()){ %>
		        <option><%= resultset.getString(1)%></option>
		    <% } %>
		    </select>
			<br><br>
			<%session.setAttribute("cid", cid);%>
			<input type=submit value="Submit" name=btnSubmit />
			</form>
	<% }//ENDIF %>
<%
	if (request.getParameter("btnSubmit") != null)
	{
		GetsSets set = new GetsSets();
		set.setCid((String)session.getAttribute("cid"));
		set.setSection(request.getParameter("section"));
		System.out.println("CID: "+set.getCid()+" Section: "+set.getSection());
		try{
		DBManager.update(null, "deleteCourse", set);
		request.getRequestDispatcher("./WelcomeAdmin.jsp").forward(request, response);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
%>


</body>
</html>