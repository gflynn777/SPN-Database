package Student;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spdb.DBManager;
import com.spdb.GetsSets;

/**
 * Servlet implementation class SPNDelete
 */
@WebServlet("/SPNDelete")
public class SPNDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SPNDelete() {
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
		String netid = (String) request.getSession().getAttribute("netid");
		String course = request.getParameter("SPN");

		if (course.equalsIgnoreCase("Choose SPN")) {

			String iMessage = "You did not select an SPN!";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./Student/ModifySPN.jsp").forward(request, response);
		} else {

			String[] courses = course.split(" ");
			GetsSets set = new GetsSets();
			set.setCid(courses[2]);
			set.setSection(courses[courses.length-1]);
			set.setId(netid);
			try {
				DBManager.update(netid, "deleteSPN", set);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String iMessage = "Your SPN number has been deleted!";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./Student/SPNStatus.jsp").forward(request, response);
		}
	}

}
