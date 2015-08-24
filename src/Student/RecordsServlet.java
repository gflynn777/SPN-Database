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
 * Servlet implementation class RecordsServlet
 */
@WebServlet("/RecordsServlet")
public class RecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecordsServlet() {
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
		String iMessage;
		String major = request.getParameter("major");
		String minor = request.getParameter("minor");
		String gpa = request.getParameter("gpa");
		String numCredits = request.getParameter("numCredits");
		String numCSCredits = request.getParameter("CSCredits");
		String netid = (String) request.getSession().getAttribute("netid");


		if (major == null || major.equals("") || numCredits == null || numCredits.equals("") || numCSCredits == null || numCSCredits.equals("")) {
			iMessage = "There was a problem with the information you entered.";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./Student/StudentRecord.jsp").forward(request, response);
		} else {
			GetsSets set = new GetsSets();
			set.setNetid(netid);
			set.setMajor(major);
			set.setMinor(minor);
			try {
				set.setGpa(Float.parseFloat(gpa));
				set.setNumCreds(Integer.parseInt(numCredits));
				set.setCscreds(Integer.parseInt(numCSCredits));
				int rank = DBManager.computeRank(set.getMajor(), set.getMinor(), set.getNumCreds(), set.getGpa(), set.getCscreds());
				set.setRank(rank);
			} catch(NullPointerException | NumberFormatException e) {
				iMessage = "You did not enter a number for the numeric fields.";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./Student/StudentRecord.jsp").forward(request, response);
			}
			
			//cannot have more CS credits than total credits or a gpa below 0 or above 4
			if(set.getNumCreds() < set.getCscreds() || set.getGpa() > 4.00 || set.getGpa() < 0.00) {
				iMessage = "You entered invalid numerical information.";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./Student/StudentRecord.jsp").forward(request, response);
			} else {

				try {
					DBManager.update(netid, "studentRecords", set);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iMessage = "Your transcript details have been added!";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./Student/ClassesTaken.jsp").forward(request, response);
			}
		}
	}

}
