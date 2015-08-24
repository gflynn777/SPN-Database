package Student;
//ANGELO
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;
import com.spdb.DBConnection;
import com.spdb.DBManager;
import com.spdb.GetsSets;

/**
 * Servlet implementation class EnterClassServlet
 */
@WebServlet("/EnterClassServlet")
public class EnterClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnterClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	//angelo
	void enterGrade(String netid, String course, String grade, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		String[] courses = course.split(" ");
		String cid = courses[courses.length-1];
		GetsSets set = new GetsSets();
		set.setNetid(netid);
		set.setCid(cid);
		set.setGrade(grade);

			try {
				DBManager.update(netid, "insertClass", set);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String iMessage = ("Course ID " + cid+ " has been added to your record!");
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./Student/ClassesTaken.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//angelo
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String netid = (String) request.getSession().getAttribute("netid");
		String course = request.getParameter("course");
		String grade = request.getParameter("grade");

		if (course.equals("Choose Course")) {
			String iMessage = "You did not select an course!";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./Student/ClassesTaken.jsp").forward(request, response);
		} else {
			if (grade == null || grade.equals("")) {
				String iMessage = "You did not enter a grade!";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./Student/ClassesTaken.jsp").forward(request, response);
			
			}else { //if grade is not null and is invalid, else enter class
				if (!grade.equalsIgnoreCase("a") && !grade.equalsIgnoreCase("b+") && !grade.equalsIgnoreCase("b") && !grade.equalsIgnoreCase("c+") &&
						!grade.equalsIgnoreCase("c") && !grade.equalsIgnoreCase("d") && !grade.equalsIgnoreCase("f")) {
					String iMessage = "You entered an invalid grade. Please enter a letter grade.";
					request.setAttribute("iMessage", iMessage);
					request.getRequestDispatcher("./Student/ClassesTaken.jsp").forward(request, response);

				} else {
					try {
						enterGrade(netid, course, grade, request, response);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}//end if grade null otherwise
		}//end else
	}//end doPost

}
