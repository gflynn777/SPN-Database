package Student;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.spdb.*;

/**
 * Servlet implementation class NewRequestServlet
 */
@WebServlet("/NewRequestServlet")
public class NewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public boolean checkPrereq(String netid, String cid) throws SQLException, ClassNotFoundException{
		DBConnection db = new DBConnection();
		Connection conn = (Connection) db.openConnection();
		Statement statement = (Statement) conn.createStatement(); //takes all prereqs of class
		ResultSet resultset = statement.executeQuery("select p.prereq from prereq p where cid=\""+cid+"\"");

		//gets all classes user has taken
		Statement statement2 = (Statement) conn.createStatement();
		ResultSet resultset2 = statement2.executeQuery("select cid from classes where netid=\""+netid+"\"");

		//store the classes taken as an array string
		ArrayList<String> cids = new ArrayList<String>();
		while (resultset2.next()) {
			cids.add(resultset2.getString(1));
		}

		//grabs current prereq from resultset and checks user has taken it in resultset2
		while(resultset.next()) {
			String curr = resultset.getString(1);
			if (!cids.contains(curr)) {
				return false;
			}

			//gets grades from the prereq user has taken
			Statement statement3 = (Statement) conn.createStatement() ;
			ResultSet resultset3 = statement3.executeQuery("select grade from classes where netid=\""+netid+"\" AND cid=\""+curr+"\"");
			resultset3.next();
			String grade = resultset3.getString(1);

			if (grade != null) {
				if (grade.equalsIgnoreCase("f") || grade.equalsIgnoreCase("d")) {
					return false;
				}
			}
		} //end while


		return true;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String netid = (String) request.getSession().getAttribute("netid");
		String course = request.getParameter("cid");
		String[] combine = course.split(" ");
		String cid = combine[combine.length-1];
		String section = request.getParameter("section");
		Date todaysDate = new Date();
		String date = todaysDate.toString();
		int x = 0;

		if (netid == "" || cid == "" || section == "" || netid == null || cid == null || section == null)
		{
			String iMessage = "Please do not leave any fields blank!";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./Student/NewRequest.jsp").forward(request, response);
			System.err.println("Fields left blank!");
		} else {

			boolean eligible = false;

			try {
				eligible = checkPrereq(netid, cid);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}



			if(eligible) {

				System.out.println(date);
				//Set Data
				GetsSets set = new GetsSets();
				set.setId(netid);
				set.setCid(cid);
				set.setSection(section);
				set.setDate(date);

				try {
					DBManager.update(netid, "newRequest", set);
				} catch (ClassNotFoundException | SQLException e) {
					String iMessage = "The entered section does not exist for that class.";
					request.setAttribute("iMessage", iMessage);
					request.getRequestDispatcher("./Student/NewRequest.jsp").forward(request, response);
					x = 1;
				}

				if (x!=1) {
					DBConnection db = new DBConnection();
					Connection connP;
					ResultSet resultsetP;
					try {
						connP = (Connection) db.openConnection();
						Statement statementP = (Statement) connP.createStatement(); //takes all prereqs of class
						resultsetP = statementP.executeQuery("select P.comment from user P, course C where C.cid=\""
								+set.getCid()+"\" AND C.section=\""+set.getSection()+"\" AND C.netid=P.netid");

						String wMessage;
						if(!resultsetP.next()) {
							wMessage = "Your request has been filed!";
						} else {
							do {
								wMessage = resultsetP.getString("comment");
							} while (resultsetP.next());

						}
						if(wMessage == null || wMessage.equals("") || wMessage.equalsIgnoreCase("null")) {
							wMessage = "Your request has been filed!";
						}
						request.setAttribute("wMessage", wMessage);
						request.getRequestDispatcher("./Student/WelcomeStudent.jsp").forward(request, response);

					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
					}//end catch
				} //end if x!=1

			} else {
				String wMessage = "You have not met the necessary prerequisites.";
				request.setAttribute("wMessage", wMessage);
				request.getRequestDispatcher("./Student/NewRequest.jsp").forward(request, response);;
			}//end if eligible else

		}//end if no blanks
	}//end dopost

}
