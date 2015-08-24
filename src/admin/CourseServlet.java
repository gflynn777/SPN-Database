package admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.DBManager;
import utility.GetsSets;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
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
		String cname = request.getParameter("cname");
		String cid = request.getParameter("cid");
		String section = request.getParameter("section");
		String tid = request.getParameter("tid");
		int x = 0;
		try {
			DBManager.generateSpns(cid, Integer.parseInt(section));
		} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Create a GLOBAL VARIABLE out of cid
		request.getSession().setAttribute("sharedId", cid);//add to session
		
		if (cname == "" || cid == "" || section == "")
		{
			String nuMessage = "There was a problem with the information entered."
					+ " Please try again!";
			request.setAttribute("nuMessage", nuMessage);
			request.getRequestDispatcher("./admin/NewCourse.jsp").forward(request, response);
			System.out.println("Fields left blank!");
		}
		else
		{
			GetsSets set = new GetsSets();
			set.setCname(request.getParameter("cname"));
			set.setCid(request.getParameter("cid"));
			set.setSection(request.getParameter("section"));
			set.setNetid(tid);
			
			try {
				DBManager.update(null, "addCourse", set);
				DBManager.update(tid, "teachesCourse", set);
			} catch (ClassNotFoundException | SQLException e) {
				String nuMessage = "That course already exists!";
				request.setAttribute("nuMessage", nuMessage);
				request.getRequestDispatcher("./admin/NewCourse.jsp").forward(request, response);
				x = 1;
			}
			if (x == 0)
				request.getRequestDispatcher("/admin/CoursePrereqs.jsp").forward(request, response);
		}//ENDELSE
	}
}
