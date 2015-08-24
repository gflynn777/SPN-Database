package student;
//ANGELO
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DBConnection;
import utility.DBManager;
import utility.GetsSets;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class RegisterClassServlet
 */
@WebServlet("/RegisterClassServlet")
public class RegisterClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String netid =(String) request.getSession().getAttribute("netid");
		String course = request.getParameter("cid");
		String[] courses = course.split(" ");
		String cid = courses[courses.length-1];
		String section = request.getParameter("section");
		String spn = request.getParameter("spn");

		if(cid == null || section == null || spn == null || cid.equals("") || section.equals("") || spn.equals("")) {
			String iMessage = "Please do not leave any fields blank";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./Student/RegisterWithSpn.jsp").forward(request, response);
		} else {

			DBConnection db = new DBConnection();
			Connection conn;
			try {
				conn = (Connection) db.openConnection();
				Statement statement = (Statement) conn.createStatement() ;
				ResultSet resultset = statement.executeQuery("SELECT S.cid, S.section, S.netid, S.spnNumber, S.used FROM spn S WHERE "
						+ "S.netid=\"" +netid+"\" AND S.spnNumber=\""+spn+"\"");

				if(!resultset.next()) {
					String iMessage = "You have not entered a valid SPN.";
					request.setAttribute("iMessage", iMessage);
					request.getRequestDispatcher("./Student/RegisterWithSpn.jsp").forward(request, response);

				} else {
					if(resultset.getInt("used")==1){
						String iMessage = "You have already registered for this class!";
						request.setAttribute("iMessage", iMessage);
						request.getRequestDispatcher("./Student/RegisterWithSpn.jsp").forward(request, response);

					} else {
						GetsSets set = new GetsSets();
						set.setNetid(netid);
						set.setCid(cid);
						set.setSection(section);
						set.setSpn(spn);
						set.setGrade(null);
						
						DBManager.update(netid, "insertEnrolled", set);
						DBManager.update(netid, "insertClass", set);
						
						DBConnection db1 = new DBConnection();
						Connection conn1=(Connection) db1.openConnection();
						String sql = ("UPDATE spn SET used=? where spnNumber=\""+set.getSpn()+"\"");
						PreparedStatement ps = (PreparedStatement) conn1.prepareStatement(sql);
						ps.setInt(1,1);
						ps.executeUpdate();
						String iMessage = "You have successfully registered!";
						request.setAttribute("iMessage", iMessage);
						request.getRequestDispatcher("./Student/ViewTranscript.jsp").forward(request, response);

					} //end if already registered
				
				}//end if result set is not null
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try

		}//end if null else
	}

}
