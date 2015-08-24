package Professor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FillReqServlet
 */
@WebServlet("/FillReqServlet")
public class FillReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillReqServlet() {
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
		String requester[] = (request.getParameter("student")).split(" ");
		String fname = requester[1];
		String lname = requester[2];
		String sid = requester[4];

		
		//Create Global Vars
		request.getSession().setAttribute("sid", sid);
		request.getSession().setAttribute("fname", fname);
		request.getSession().setAttribute("lname", lname);
		
		//Forward user on to next page
		request.getRequestDispatcher("/Professor/RequesterPage.jsp").forward(request, response);
	}

}
