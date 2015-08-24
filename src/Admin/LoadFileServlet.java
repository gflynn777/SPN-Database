package Admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.spdb.DBManager;
import com.spdb.GetsSets;

/**
 * Servlet implementation class LoadFileServlet
 */
@WebServlet("/LoadFileServlet")
public class LoadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}





	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}



	private int readTranscripts(File file, String filename, HttpServletRequest request, HttpServletResponse response, int x) throws ServletException, IOException{

		String line;
		String[] info;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while((line = br.readLine()) != null) {
			//net maj min gpa num cs
			info = line.split(" ");
			String netid = info[0];
			String major = info[1];
			String minor = info[2];
			String gpa = info[3];
			String numCredits = info[4];
			String csCreds = info[5];

			GetsSets set = new GetsSets();
			set.setId(netid);
			System.out.println("NetID Before: "+ set.getId());
			set.setMajor(major);
			set.setMinor(minor);
			set.setGpa(Float.parseFloat(gpa));
			set.setNumCredits(Integer.parseInt(numCredits));
			set.setCscreds(Integer.parseInt(csCreds));
			int rank = DBManager.computeRank(set.getMajor(), set.getMinor(), set.getNumCredits(), set.getGpa(), set.getCscreds());
			System.out.println(rank);
			set.setRank(rank);

			try {
				DBManager.update(netid, "studentRecords", set);
			}catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				br.close();
				br=null;
				return 1;
				/*String iMessage = "There was an error loading the file";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);*/
			}

		}//end while

		br.close();
		br = null;
		return 0;
	}

	private int readCourses(File file, String filename, HttpServletRequest request, HttpServletResponse response, int x) throws ServletException, IOException {
		boolean added = false;
		String line;
		String[] info;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) { 
			info = line.split(" ");
			String cname = info[0];
			String cid = info[1];
			String section = info[2];
			String netid = info[3];

			GetsSets set = new GetsSets();
			set.setCid(cid);
			set.setSection(section);
			set.setNetid(netid);
			String name = "";
			name = cname.replaceAll("-", " ");
			set.setCname(name);
			try {
				added = DBManager.update(netid, "addCourse", set);
			}catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				br.close();
				br = null;
				return 1;
				/*if(!response.isCommitted()) {
					String iMessage = "There was an error loading the file";
					request.setAttribute("iMessage", iMessage);
					request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);
				}*/
			}
			
			if (added){
				try {
					DBManager.generateSpns(cid, Integer.parseInt(section));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} //end while not null
		br.close();
		br = null;
		return 0;
	}

	private int readClassesTaken(File file, String filename, HttpServletRequest request, HttpServletResponse response, int x) throws ServletException, IOException {
		
		String line;
		String[] info;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			
			info = line.split(" ");
			String netid=info[0];
			String cid=info[1];
			String grade = info[2];
			
			GetsSets set = new GetsSets();
			set.setNetid(netid);
			set.setCid(cid);
			set.setGrade(grade);
			
			try {
				DBManager.update(netid, "insertClass", set);
			}catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				br.close();
				br = null;
				return 1;
				/*String iMessage = "There was an error loading the file";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);*/

			}
			
		}//end while
		
		br.close();
		br=null;
		return 0;
	}
	
	private int readPrereqs(File file, String filename, HttpServletRequest request, HttpServletResponse response, int x) throws IOException, ServletException  {
		String line;
		String[] info;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) { 
			info = line.split(" ");
			String cid = info[0];
			String prereq = info[1];			

			GetsSets set = new GetsSets();
			set.setCid(cid);
			set.setPrereq(prereq);
			System.out.println("cid: "+set.getCid()+ " prereq: "+set.getPrereq());

			try {
				DBManager.update(null, "addPrereq", set);
			}catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
				br.close();
				br = null;
				return 1;
				/*String iMessage = "There was an error loading the file";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);*/

			}

		}//end while not null
		br.close();
		br = null;
		return 0;
	}

	private int readUsers(File file, String filename, HttpServletRequest request, HttpServletResponse response, int x) throws IOException, ServletException {
		String line;
		String[] info;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			info = line.split(" ");
			String netid = info[0];
			String first = info[1];
			String last = info[2];
			String password = info[3];
			String email = info[4];
			String RUID = info[5];
			String isProf = info[6];
			String isAdmin = info[7];

			GetsSets set = new GetsSets();
			set.setId(netid);
			set.setfName(first);
			set.setlName(last);
			set.setPassword(password);
			set.setEmail(email);
			set.setRuid(RUID);
			if(isProf.equals("0")) {
				set.setProfBool("0");
			} else {
				set.setProfBool("1");
			}
			if(isAdmin.equals("0")) {
				set.setAdminBool("0");
			} else {
				set.setAdminBool("1");
			}
			try {
				DBManager.insert(set);
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				br.close();
				br=null;
				return 1;
				/*String iMessage = "There was an error loading the file";
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);*/
			}

		} //end while null
		br.close();
		br = null;
		return 0;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		String filename = (String) request.getParameter("filename");
		String type = (String) request.getParameter("type");
		int x = 0;

		PoolProperties props = new PoolProperties();

		if (filename == null || filename.equals("")) {
			x=1;
			String iMessage = "Please do not leave any fields blank!";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);
			return;
		} else if(!filename.contains(".txt")) {
			x=1;
			String iMessage = "Please select a text file.";
			request.setAttribute("iMessage", iMessage);
			request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);
			return;
		} else {

			ServletContext context = request.getSession().getServletContext();
			File file = new File(context.getRealPath("/Files/"+filename));
			System.out.println(file.getAbsolutePath());
			System.out.println(filename);


			if(!file.exists()) {
				x=1;
				String iMessage = (filename + " not found.");
				request.setAttribute("iMessage", iMessage);
				request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);
				return;
			} else {
				System.out.println("File exists!");
				switch (type) {
				case "Choose Type":
					x=1;
					String iMessage = "Please select the information type!";
					request.setAttribute("iMessage", iMessage);
					request.getRequestDispatcher("./admin/LoadFile.jsp").forward(request, response);
					return;
				case "Users":
					x = readUsers(file,filename,request,response, x);
					break;
				case "Courses":
					x = readCourses(file,filename,request,response, x);
					break;
				case "Prerequisites":
					x = readPrereqs(file,filename,request,response, x);
					break;
				case "Transcripts":
					x = readTranscripts(file,filename,request,response, x);
					break;
				case "Classes Taken":
					x = readClassesTaken(file, filename, request, response, x);
					break;
				default:
					break;


				} //end switch
			} //end if file found
		}//end big if else if else

		if(x==0) {
			String nuMessage = "Successfully added file record!";
			request.setAttribute("nuMessage", nuMessage);
			request.getRequestDispatcher("./admin/WelcomeAdmin.jsp").forward(request, response);
		}
	}
}