package project1;
import java.sql.*;
public class LibraryDb 
{
	
		Connection cn;
		Statement st;
		PreparedStatement binsert,pdelete,pupdate,sinsert,supdate,sdelete,admin,isuuedata,del;
		int x;
		
		LibraryDb() throws ClassNotFoundException, SQLException
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			binsert=cn.prepareStatement("insert into books(isbn,b_name,author,price,qty) values(?,?,?,?,?)");
			sinsert=cn.prepareStatement("insert into issue (isbn,b_name,author,roll_no,sname,date_of_issue,date_of_return)values(?,?,?,?,?,?,?)");
			admin=cn.prepareStatement("select * from admin where phone=? or email=? and pwd=?");
			del=cn.prepareStatement("DELETE FROM `issue` WHERE `isbn` = ?");
		}
		int addbook(int isbn,String name,String author,int price ,int qty) throws SQLException
		{
			binsert.setInt(1,isbn);
			binsert.setString(2,name);
			binsert.setString(3,author);
			binsert.setInt(4,price);
			binsert.setInt(5,qty);
			int x=binsert.executeUpdate();
			return x;	
		}

		int addstudent(int roll_no,String name,String gender,int std,String city,String state,String phone,String pwd ) throws SQLException
		{
			sinsert.setInt(1, roll_no);
			sinsert.setString(2,name);
			sinsert.setString(3,gender);
			sinsert.setInt(4, std);
			sinsert.setString(5,city);
			sinsert.setString(6,state);
			sinsert.setString(7,pwd);
			int x=sinsert.executeUpdate();
			return x;
		}
		
		
		
		int getNextRoll_no() throws SQLException
		{
			int num=1;
			st=cn.createStatement();
			ResultSet rs=st.executeQuery("select (max)roll_no from student");
			if(rs.next()) 
		
				num+=rs.getInt("roll_no");

			return num;
		}

		ResultSet getStudentData() throws SQLException
		{
			st=cn.createStatement();
			ResultSet rs=st.executeQuery("select*from student");
			return rs;
		}

		ResultSet getStudentData(int roll_no) throws SQLException
		{
			st=cn.createStatement();
			ResultSet rs=st.executeQuery("select * from student where roll_no="+roll_no);
			return rs;
		}
		
		ResultSet getBook() throws SQLException
		{
			st=cn.createStatement();
			ResultSet rs=st.executeQuery("select * from books");
			return rs;
		}
		
		ResultSet getBook(int isbn) throws SQLException
		{
			st=cn.createStatement();
			ResultSet rs=st.executeQuery("select*from books where isbn="+isbn);
			return rs;
			
		}
		
		ResultSet adminLogin(String email,String pwd) throws SQLException
		{
			admin.setString(1, email);
			admin.setString(2, email);
			admin.setString(3, pwd);
			ResultSet rs=admin.executeQuery();
			return rs;
		}
		int getNextisbn() throws SQLException
		{
			int num=1;
			st=cn.createStatement();
			
			ResultSet rs=st.executeQuery("select max(isbn) from books");
			
			if(rs.next())
				num += rs.getInt(1);
			
			return num;
		}

		
		int issueBook(int isbn,String b_name,String author,int roll_no,String sname,String date_of_issue, String date_of_return) throws SQLException
		{
			sinsert.setInt(1, isbn);
			sinsert.setString(2,b_name);
			sinsert.setString(3,author);
			sinsert.setInt(4, roll_no);
			sinsert.setString(5,sname);
			sinsert.setString(6,date_of_issue);
			sinsert.setString(7,date_of_return);
			
			int x=sinsert.executeUpdate();
			return x;
			
		}
		
		int Updatebookstatus(int isbn,String status,int qty) throws SQLException
		{	
				pupdate=cn.prepareStatement("update books set status=?,qty=? where isbn =?");
				pupdate.setString(1,status );
				pupdate.setInt(3,isbn );
				pupdate.setInt(2,qty);
			    int x=pupdate.executeUpdate();
			    return x;
		}
		
		
		
		int Updatebookstatus(int isbn,int qty) throws SQLException
		{	
				pupdate=cn.prepareStatement("update books set qty=? where isbn =?");
				pupdate.setInt(1, qty);
				pupdate.setInt(2, isbn);
			    int x=pupdate.executeUpdate();
			    return x;
		}
		
		ResultSet getissuedBook() throws SQLException
		
		{
			st=cn.createStatement();
			ResultSet rs=st.executeQuery("select * from issue");
			return rs;
		}
		ResultSet checkbook(int isbn,int roll_no) throws SQLException
		{
			PreparedStatement check=cn.prepareStatement("select*from issue where isbn=? and roll_no=?");
			check.setInt(1, isbn);
			check.setInt(2, roll_no);
			ResultSet rs=check.executeQuery();
			return rs;
		}
		int returnbook(int isbn) throws SQLException
		{
			//PreparedStatement ptt=cn.prepareStatement("delete * from issue where isbn=?");
			del.setInt(1,isbn);
			int x=del.executeUpdate();
			return x;
			
		}
}
