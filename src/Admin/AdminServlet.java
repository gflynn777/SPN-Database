package Admin;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spdb.DBConnection;
import com.spdb.DBManager;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String table = request.getParameter("table");
		DBConnection db = new DBConnection();
	    try {
			Connection conn = db.openConnection();
		    Statement statement = conn.createStatement();
			statement.executeUpdate("delete from "+table);
		    statement.executeUpdate("delete from makesrequest");
			String nuMessage = "Table: "+table+" has been successfully deleted!";
			request.setAttribute("nuMessage", nuMessage);
			request.getRequestDispatcher("./admin/WelcomeAdmin.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Unable to complete request!");
			String nuMessage = "Unable to process request!";
			request.setAttribute("nuMessage", nuMessage);
			e.printStackTrace();
			request.getRequestDispatcher("./admin/WelcomeAdmin.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("netid");

		try {
			DBManager.update(id, "admin", null);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String wMessage = id+" now has admin priveledges!";
		request.setAttribute("wMessage", wMessage);
		request.getRequestDispatcher("./admin/WelcomeAdmin.jsp").forward(request, response);
		
	}

}
