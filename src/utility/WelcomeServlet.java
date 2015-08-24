package utility;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
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
		String idString = request.getParameter("userId");
		String password = request.getParameter("password");
		String iMessage;
		GetsSets sets = new GetsSets();
		
		//Create a GLOBAL VARIABLE out of netid
		request.getSession().setAttribute("netid", idString);//add to session
		
		//Make sure fields aren't blank
		if (password == "" || request.getParameter("userName") == "")
		{
			iMessage = "Fields must not be left blank! Try again.";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}
		else
		{	
			sets.setId(idString);
			sets.setPassword(password);
			try {
				//Check to see if id/pw combo is in database
				if (DBManager.idPassCheck(sets) == true)
				{
					//Check for admin privileges
					if (DBManager.isAdmin(sets.getId()) == 1)
							response.sendRedirect("./admin/WelcomeAdmin.jsp");
					//Check if user is professor
					else if (DBManager.isProf(sets.getId()) == 1)
						response.sendRedirect("./Professor/WelcomeProfessor.jsp");
					//If not professor or admin, send to student page
					else
						response.sendRedirect("./Student/WelcomeStudent.jsp");
				}
				else
				{
					//Must have been bad id/password combo: reload page
					iMessage = "Incorrect NetId/Password combination!";
					request.setAttribute("iMessage", iMessage);
					request.getRequestDispatcher("./index.jsp").forward(request, response);
				}
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
