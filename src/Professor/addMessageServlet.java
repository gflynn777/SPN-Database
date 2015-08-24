package Professor;

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
 * Servlet implementation class addMessageServelet
 */
@WebServlet("/addMessageServlet")
public class addMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addMessageServlet() {
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
		String netid = (String) request.getSession().getAttribute("netid");
		String message = request.getParameter("message");
		String iMessage;
		
		if (message == null || message.equals(""))
		{
			iMessage = ("There was a problem with the information entered."
					+ " Please try again!");
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("/Professor/ModifyMessage.jsp").forward(request, response);
		}

		else
		{		
			//Create an instance of the GetSets class
			GetsSets sets = new GetsSets();
			sets.setMessage(message);
			sets.setId(netid);
			//Set Values
			try {
				DBManager.update(sets.getNetid(),"modifyMessage", sets);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			String wMessage = "You have successfully added your message!";
			request.setAttribute("wMessage", wMessage);
			request.getRequestDispatcher("/Professor/WelcomeProfessor.jsp").forward(request, response);	
/*
 * hima
			try {
				//Create a GLOBAL VARIABLE out of netid
				request.getSession().setAttribute("netid", DBManager.newId(fname, lname));//add to session
				sets.setId(DBManager.newId(fname, lname));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
hima
*/
			
		}//ENDELSE
		
	}

}