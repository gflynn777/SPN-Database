package utility;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DBManager;
import utility.GetsSets;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String fname = request.getParameter("fName");
		String lname = request.getParameter("lName");
		String email = request.getParameter("email");
		String ruid = request.getParameter("ruid");
		String password = request.getParameter("password");
		String profBool = request.getParameter("profBool");
		String nuMessage;
		
		if (fname == "" || lname == "" || email == "" || ruid == "" || profBool == null || password == "")
		{
			nuMessage = "There was a problem with the information entered."
					+ " Please try again!";
			request.setAttribute("nuMessage", nuMessage);
			request.getRequestDispatcher("/newuser.jsp").forward(request, response);
		}

		else
		{		
			//Create an instance of the GetSets class
			GetsSets sets = new GetsSets();
			
			//Set Values
			try {
				sets.setId(DBManager.newId(fname, lname));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			sets.setfName(fname);
			sets.setlName(lname);
			sets.setEmail(email);
			sets.setPassword(password);
			sets.setProfBool(profBool);
			sets.setRuid(ruid);

			try {
				//Create a GLOBAL VARIABLE out of netid
				request.getSession().setAttribute("netid", DBManager.newId(fname, lname));//add to session
				sets.setId(DBManager.newId(fname, lname));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
					//Check to make sure email and ruid are unique. If not, reload
					if (DBManager.emailChecker(email) == true || DBManager.ruidChecker(sets) == true)
					{
						nuMessage = "That email and/or RUID is already registered!";
						request.setAttribute("nuMessage", nuMessage);
						request.getRequestDispatcher("/newuser.jsp").forward(request, response);
					}
					else
					{
						//Insert new user into database
						DBManager.insert(sets);
						
						//Print id of user: sets.getId(); and take them to the appropriate page
						String wMessage = "Your new NetId is: " + sets.getId();
						request.setAttribute("wMessage", wMessage);
						if (profBool.equals("0"))
							request.getRequestDispatcher("/Student/StudentRecord.jsp").forward(request, response);
						else
							request.getRequestDispatcher("/Professor/WelcomeProfessor.jsp").forward(request, response);
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}//ENDELSE
	}//ENDMETHOD
}




////Example for checkboxes:
//StringBuilder csvSkills = new StringBuilder(); 
//String[] skill = request.getParameterValues("skills");
//for (String skills : skill){
//	if(csvSkills.length() > 0)
//		csvSkills.append(",");
//	
//	csvSkills.append(skills);
//}
//String csvSkill = csvSkills.toString();
