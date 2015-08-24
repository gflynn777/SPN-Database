package professor;

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
 * Servlet implementation class RequestorChooseServlet
 */
@WebServlet("/RequestorChooseServlet")
public class RequestorChooseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestorChooseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private String findCid(String s) {
		String cid;
		for (int i = 0; i< s.length();i++) {
			if (s.charAt(i)==':' && s.charAt(i-1)=='D') {
				for (int j = i; j<s.length(); j++) {
					if(s.charAt(j)==':' && s.charAt(j-1)=='n') {
						int endIndex = j-4;
						cid = s.substring(i+2, j-8);
						System.out.println(cid);
						return cid;
					}
				}//end for
			}
		}
		return null;
	}

	private String findSection(String s) {
		
		for(int i = 0; i<s.length();i++) {
			if(s.charAt(i)==':' && s.charAt(i-1)=='n') {
				String section = s.substring(i+2);
				System.out.println(section);
				return section;
			}
			
		}
		
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Assign Student an spn
		String newSpn = null;
		String selection = request.getParameter("selection");
		String comment = request.getParameter("comment");
		String cid = findCid(selection);
		String section = findSection(selection);
		//Get global student netid
		String sid = (String) request.getSession().getAttribute("sid");	
		
		GetsSets set = new GetsSets();
		set.setNetid(sid);
		set.setCid(cid);
		set.setSection(section);
		set.setComment(comment);
		
		
		if (request.getParameter("gen") != null){			
			
			try {
				newSpn = DBManager.getNextSpn(cid, section);
				System.out.println("NewSpn: "+newSpn);
				set.setSpn(newSpn);

			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Assign spn to student
			try {
				DBManager.update(set.getNetid(), "assignSpn", set);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(sid+" has been assigned SPN: "+newSpn);
			request.getRequestDispatcher("/Professor/WelcomeProfessor.jsp").forward(request, response);
		}
			
		if (request.getParameter("deny") != null){
			//deny request
			try {
				set.setComment(comment);
				DBManager.update(set.getNetid(), "denySpn", set);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/Professor/WelcomeProfessor.jsp").forward(request, response);
		}


	}

}
