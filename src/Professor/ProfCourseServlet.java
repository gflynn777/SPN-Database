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
 * Servlet implementation class CourseServlet
 */
@WebServlet("/ProfCourseServlet")
public class ProfCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfCourseServlet() {
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
		String rmsize= request.getParameter("rmsize");
		boolean addedCourse = false;
		String netid = (String)request.getSession().getAttribute("netid");
		
		//Create a GLOBAL VARIABLE out of cid
		request.getSession().setAttribute("sharedId", cid);//add to session
		
		if (cname == "" || cid == "" || section == "" || rmsize=="")
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
			try {
				if(DBManager.isAdmin(netid) == 1){
					String tidString[] = request.getParameter("tid").split(" ");
					String tid = tidString[2];
					set.setNetid(tid);
				}
				else
					set.setNetid(netid);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			set.setRoomSize(request.getParameter("rmsize"));
			System.out.println("TID: "+set.getNetid());
			
			try {
				addedCourse = DBManager.update(null, "addCourse", set);
				//DBManager.update(set.getNetid(), "teachesCourse", set);
			} catch (ClassNotFoundException | SQLException e) {
				String nuMessage = "That course already exists!";
				request.setAttribute("nuMessage", nuMessage);
				request.getRequestDispatcher("./Professor/ProfNewCourse.jsp").forward(request, response);
				e.printStackTrace();
			}
			if (addedCourse){
				try {
					DBManager.generateSpns(cid, Integer.parseInt(section));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/admin/CoursePrereqs.jsp").forward(request, response);
		}//ENDELSE
	}
}
