package admin;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.*;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class PrereqServlet
 */
@WebServlet("/PrereqServlet")
public class PrereqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrereqServlet() {
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
		GetsSets set = new GetsSets();
		String prereq1 = request.getParameter("prereq1");
		String prereq2 = request.getParameter("prereq2");
		String prereq3 = request.getParameter("prereq3");
		System.out.println("1: "+prereq1 +" 2: "+prereq2 + " 3: "+prereq3);
		
		//Access GLOBAL VARIABLE cid
		String cid = (String) request.getSession().getAttribute("sharedId");
		String netid = (String) request.getSession().getAttribute("netid");
		set.setCid(cid);
		
		//Nullify fields that were not chosen
		if (prereq1.equals("Choose Course"))
			prereq1 = null;
		if (prereq2.equals("Choose Course"))
			prereq2 = null;
		if (prereq3.equals("Choose Course"))
			prereq3 = null;
		
		//If no prereqs were entered
		if (prereq1 == null && prereq2 == null && prereq3 == null)
			request.getRequestDispatcher("/Professor/WelcomeProfessor.jsp").forward(request, response);
		
		else
		{
			//Perform Updates
			try{
				if (prereq1 != null){
					set.setPrereq(prereq1);
					System.out.println("Cid in DBM: "+cid);
					DBManager.update(cid, "addPrereq", set);
				}
				if (prereq2 != null){
					set.setPrereq(prereq2);
					DBManager.update(cid, "addPrereq", set);
				}
				if (prereq3 != null){
					set.setPrereq(prereq3);
					DBManager.update(cid, "addPrereq", set);
				}
			} catch (Exception e){
				System.err.println("Error with addPrereqs in PrereqServlet Cid: "+cid);
				String nuMessage = "Error inputting prereqs!";
				request.setAttribute("nuMessage", nuMessage);
				
			}
			try {
				if (DBManager.isAdmin(netid) == 1)
					request.getRequestDispatcher("/admin/WelcomeAdmin.jsp").forward(request, response);
				else
					request.getRequestDispatcher("/Professor/WelcomeProfessor.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//ENDELSE
	}
}

