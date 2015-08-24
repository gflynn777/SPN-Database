package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class DBManager {
	
	private static DBConnection db = new DBConnection();
	private static Connection conn = null;
	
	public static void insert(GetsSets set) throws ClassNotFoundException, SQLException{
		
		//Open the connection
		conn = db.openConnection();
		
		String sql = "INSERT INTO user (netid, fname, lname, email, ruid, password, isProf) VALUES(?,?,?,?,?,?,?)";
		//String sql2 = "INSERT INTO Student (netid) VALUES(?)";
		String sql3 = "INSERT INTO professor (netid) VALUES(?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, set.getId());
		pstmt.setString(2, set.getfName());
		pstmt.setString(3, set.getlName());
		pstmt.setString(4, set.getEmail());
		pstmt.setString(5, set.getRuid());
		pstmt.setString(6, set.getPassword());
		pstmt.setString(7, set.isProfBool());
		pstmt.executeUpdate();
		//Add to Professor Table
		if (set.isProfBool().equals("1"))
		{
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, set.getId());
			pstmt.executeUpdate();
		}
		
		//Close the connection
		conn = db.closeConnection();

	}//ENDMETHOD

	public static boolean emailChecker(String email) throws ClassNotFoundException, SQLException
	{
		int count = 0;
		boolean returnVal;
		//Open the connection
		conn = db.openConnection();
		
		//Check to make sure email is unique
		String sql = "SELECT COUNT(*) FROM user WHERE email=\"" + email + "\"";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();
		result.next();
		if (result.getInt(1) == 0)
			returnVal = false;
		else
			returnVal = true;
		
		conn = db.closeConnection();
		System.out.println("EmailChecker: "+ returnVal);
		return returnVal;
		
	}
	
	public static boolean ruidChecker(GetsSets set) throws ClassNotFoundException, SQLException
	{
		int count = 0;
		boolean returnVal;
		//Open the connection
		conn = db.openConnection();
		
		//Check to make sure email is unique
		String sql = "SELECT COUNT(*) FROM user WHERE ruid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, set.getRuid());
		ResultSet result = pstmt.executeQuery();
		
		while(result.next())
			count = result.getInt(1);
		if (count == 0)
			returnVal = false;
		else
			returnVal = true;
		System.out.println("RuidChecker: "+returnVal);
		conn = db.closeConnection();
		return returnVal;
	}
	
	public static boolean idPassCheck(GetsSets set) throws SQLException, ClassNotFoundException
	{
		//Open the connection
		conn = db.openConnection();
		int count = 0;
		//Check ID and Password
		System.out.println("Checking... ID: " +set.getId() + " PW: " + set.getPassword());
		String sql = "SELECT COUNT(*) FROM user WHERE netid=? AND password=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, set.getId());
		pstmt.setString(2, set.getPassword());
		ResultSet result = pstmt.executeQuery();
		while(result.next())
			count = result.getInt(1);
		if (count == 0){
			conn = db.closeConnection();
			return false;
		}
		conn = db.closeConnection();
		return true;
	}
	
	public static int isProf(String id) throws ClassNotFoundException, SQLException
	{
		conn = db.openConnection();
		String sql = "SELECT R.isProf FROM user R WHERE R.netid=\""+id+ "\"";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();
		result.next();
		int res = result.getInt(1);
		conn = db.closeConnection();
		return res;
	}
	
	public static int isAdmin(String id) throws ClassNotFoundException, SQLException
	{
		conn = db.openConnection();
		String sql = "SELECT R.isAdmin FROM user R WHERE R.netid=\""+id+ "\"";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();
		result.next();
		int res = result.getInt(1);
		conn = db.closeConnection();
		return res;
	}
	
	public static boolean update(String id, String field, GetsSets set) throws SQLException, ClassNotFoundException
	{
		PreparedStatement pstmt = null;
		String sql;
		conn = db.openConnection();

		switch (field) {
			case "admin":
				sql = "UPDATE user set isAdmin=1 WHERE netid=\""+id+ "\"";
				pstmt = conn.prepareStatement(sql);
				System.out.println("Made "+id+" an admin!");
				pstmt.executeUpdate();
				break;
				
			case "addCourse":
				sql = "INSERT course (cname, cid, section, netid) VALUES (?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, set.getCname());
				pstmt.setString(2, set.getCid());
				pstmt.setString(3, set.getSection());
				pstmt.setString(4, set.getNetid());
				pstmt.executeUpdate();
				break;
			
			case "deleteCourse":
				sql = "DELETE FROM course WHERE cid=\""+set.getCid()+"\" AND section=\""+set.getSection()+"\"";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("Deleted Course: "+set.getCid()+ " Section: "+set.getSection());
				break;
				
			case "addPrereq":
				sql = "INSERT INTO prereq (cid, prereq) VALUES(?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, set.getCid());
				pstmt.setString(2, set.getPrereq());
				pstmt.executeUpdate();
				break;
				
			case "newRequest":
				sql = "INSERT INTO spn_request (netid, cid, section, date_stamp,filed) VALUES (?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, set.getCid());
				pstmt.setString(3, set.getSection());
				pstmt.setString(4, set.getDate());
				pstmt.setString(5, "0");
				pstmt.executeUpdate();
				break;
				
			case "studentRecords":
				sql = "INSERT INTO transcript(netid, Major, Minor, gpa, numCreds, csCreds) VALUES (?,?,?,?,?,?)";
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, set.getMajor());
				pstmt.setString(3, set.getMinor());
				pstmt.setFloat(4, set.getGpa());
				pstmt.setInt(5, set.getNumCreds());
				pstmt.setInt(6, set.getCscreds());
				pstmt.executeUpdate();
				String sql1 = "INSERT INTO student(netid, major, minor, gpa, numCredits, csCreds, rank) VALUES(?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1, id);
				pstmt.setString(2, set.getMajor());
				pstmt.setString(3, set.getMinor());
				pstmt.setFloat(4, set.getGpa());
				pstmt.setInt(5, set.getNumCreds());
				pstmt.setInt(6, set.getCscreds());
				pstmt.setInt(7, set.getRank());
				pstmt.executeUpdate();
				/*sql2 = ("UPDATE student S SET S.rank=" +set.getRank() + " WHERE S.netid=\"" +id + "\"");
				PreparedStatement pstmt2= conn.prepareStatement(sql2);
				pstmt.executeUpdate();
				System.out.println("Entered rank is " +set.getRank());*/
				break;
				
			case "deleteSPN":
				sql = "DELETE FROM spn_request WHERE cid=\"" + set.getCid() + "\""
					+ "AND section=\"" + set.getSection() + "\"";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				System.out.println("Deleted SPN Request");
				String sql21 = "DELETE FROM makesrequest WHERE netid=\"" + set.getId() + "\""
						+ "AND cid=\"" + set.getCid() + "\" AND section=\"" +set.getSection() + "\"";
				pstmt = conn.prepareStatement(sql21);
				pstmt.executeUpdate();
				break;
				
			case "teachesCourse":
				sql = "INSERT INTO teaches (netid, cid, section) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, set.getCid());
				pstmt.setString(3, set.getSection());
				pstmt.executeUpdate();
				break;
			
			case "fillReq":
				sql = "UPDATE spn set";
				pstmt = conn.prepareStatement(sql);
				break;
				
			case "assignSpn":
				sql = "UPDATE spn set netid=? where spnNumber=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, set.getNetid());
				pstmt.setString(2, set.getSpn());
				pstmt.executeUpdate();
				String sql2 = "UPDATE spn_request set filed=1 where netid=? and cid=? and section=?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, set.getNetid());
				pstmt2.setString(2, set.getCid());
				pstmt2.setString(3, set.getSection());
				pstmt2.executeUpdate();
				break;
				
			case "denySpn":
				sql = "UPDATE spn_request set filed=1 where netid=? and cid=? and section=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, set.getNetid());
				pstmt.setString(2, set.getCid());
				pstmt.setString(3, set.getSection());
				pstmt.executeUpdate();
				sql2 = "UPDATE spn_request set denied=1 where netid=? and cid=? and section=?";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, set.getNetid());
				pstmt2.setString(2, set.getCid());
				pstmt2.setString(3, set.getSection());
				pstmt2.executeUpdate();
				String sql3 = "UPDATE spn_request set comment=? where netid=? and cid=? and section=?";
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setString(1, set.getComment());
				pstmt3.setString(2, set.getNetid());
				pstmt3.setString(3, set.getCid());
				pstmt3.setString(4, set.getSection());
				pstmt3.executeUpdate();
				break;
				
			case "modifyMessage":
				String message = set.getMessage();
				String netid = set.getId();
				sql = "UPDATE user P set P.comment=\""+message+"\" where P.netid=\""+netid+"\"";
				pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, set.getMessage());
				//pstmt.setString(2, set.getNetid());
				pstmt.executeUpdate();
				break;
				
			case "insertClass":				
				sql = "INSERT INTO classes (netid, cid, grade) VALUES(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, set.getNetid());
				pstmt.setString(2, set.getCid());
				pstmt.setString(3, set.getGrade());
				pstmt.executeUpdate();
				break;
				
			//angelo
			case "insertEnrolled":
				sql = "INSERT INTO enrolled (netid, cid, section) VALUES(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, set.getNetid());
				pstmt.setString(2, set.getCid());
				pstmt.setString(3, set.getSection());
				pstmt.executeUpdate();
				break;
		}//ENDSWITCH
		conn = db.closeConnection();
		return true;
	}
	
	public static boolean idExists(String id) throws ClassNotFoundException, SQLException
	{
		boolean returnVal;
		conn = db.openConnection();
		String sql = "Select * FROM user where netid=\""+id+ "\"";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();
		if (result.next())
			returnVal =  true;
		else
			returnVal = false;
		
		return returnVal;
		
	}
	
	public static String newId(String fname, String lname) throws ClassNotFoundException, SQLException
	{
		int x = 1;
		StringBuilder newId = new StringBuilder();
		newId.append(fname.charAt(0));
		newId.append(lname.charAt(0));
		newId.append("1");
		while (idExists(newId.toString().toLowerCase()))
		{
			newId.deleteCharAt(2);
			newId.append(++x);
		}
		return newId.toString().toLowerCase(); 
	}
	
	public static String createSpn(String cid, String section)
	{
		StringBuilder newSpn = new StringBuilder();
		Random ran = new Random();
		newSpn.append(cid);
		newSpn.append(section);
		newSpn.append(ran.nextInt(10000));
		
		return newSpn.toString();
	}
	
	public static int computeRank(String major, String minor, int credits, float gpa, int cscreds){
		int rank = 0;
		rank += (1.5) * cscreds;
		rank += (credits - cscreds);
		if (major.equalsIgnoreCase("Computer Science") || major.equalsIgnoreCase("CS"))
			rank += 25;
		if(minor.equalsIgnoreCase("Computer Science") || minor.equalsIgnoreCase("CS"))
			rank += 15;
		if(gpa==4.0)
			rank += 10;
		if(gpa >= 3.5 && gpa < 4.0)
			rank += 9;
		if(gpa >= 3.0 && gpa < 3.5)
			rank += 8;
		if(gpa >= 2.5 && gpa < 3.0)
			rank += 6;
		if(gpa >= 2.0 && gpa < 2.5)
			rank += 4;
		return rank;
	}
	public static void generateSpns(String cid, int section) throws ClassNotFoundException, SQLException{
		conn = db.openConnection();
		Random x = new Random();
		
		for (int i=0; i<10; i++)
		{
			int R = x.nextInt(9999999 - 1000000) + 1000000;
			String sql = "INSERT INTO spn(cid, section, spnNumber, used) VALUES("+cid+", "+section+", "+R+", 0)";
			//System.out.println("SPN: "+R);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		System.out.println("10 SPNs have been generated!");
	}
	public static String getNextSpn(String cid, String section) throws SQLException, ClassNotFoundException{
		conn = db.openConnection();
		String sql = "SELECT spnNumber from spn where cid=? and section=? and used=0 and netid is null";
		System.out.println("Cid: "+cid+" "+section);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cid);
		pstmt.setString(2, section);
		ResultSet result = pstmt.executeQuery();
		if (result.next()){
			return result.getString(1);
		}
		else 
			return null;
	}
}
