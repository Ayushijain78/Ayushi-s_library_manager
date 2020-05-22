package project1;
import java.sql.*;
import java.util.Base64;

public class StudentDb
{
	Connection cn;
	Statement st;
	PreparedStatement pinsert,pdelete,pupdate,course;
	
	public StudentDb() throws ClassNotFoundException,SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/classwork?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		pinsert=cn.prepareStatement("insert into student(roll_no,name,course,gender,mobile,email,address,pwd,image) values(?,?,?,?,?,?,?,?,?)");
		pdelete=cn.prepareStatement("delete from student where roll_no=?");
		pupdate=cn.prepareStatement("update student set  name=?,course=?,mobile=?,address=?,email=?,image=? where roll_no=?");
		
	
	}
	
	int insertStudent(int roll_no,String name,String course,String gender,String mobile,String email,String address,String pwd,byte[] b)throws SQLException
	{
		pinsert.setInt(1, roll_no);
		pinsert.setString(2, name);
		pinsert.setString(3, course);
		pinsert.setString(4, gender);
		pinsert.setString(5,mobile );
		pinsert.setString(6, email);
		pinsert.setString(7, address);
		pinsert.setString(8, pwd);
		pinsert.setBytes(9,b);
		int x=pinsert.executeUpdate();
		
		return x;
		
	}
	int updateStudent(int roll,String name,String course,String mobile,String email,String address,byte[] img)throws SQLException
	{
		
		pupdate.setString(1, name);
		pupdate.setString(2, course);
		pupdate.setString(3, mobile );
		pupdate.setString(4, email);
		pupdate.setString(5, address);
		pupdate.setBytes(6, img);
		pupdate.setInt(7, roll);
		
		
		int x=pupdate.executeUpdate();
		
		return x;
		
	}
	int deleteStudent(int id)throws SQLException
	{
		pdelete.setInt(1, id);
		
		int x=pdelete.executeUpdate();
		return x;
	}
	
	ResultSet getStudentData()throws SQLException
	{	
		PreparedStatement ptt=cn.prepareStatement("select * from student ");
		ResultSet rs=ptt.executeQuery();
		return rs;
	}
	
	ResultSet getStudentData(String course)throws SQLException
	{	
		PreparedStatement ptt=cn.prepareStatement("select * from student where course=?");
		ptt.setString(1, course);
		ResultSet rs=ptt.executeQuery();
		return rs;
	}
	
	
	ResultSet getStudentData(int roll)throws SQLException
	{	
		PreparedStatement ptt=cn.prepareStatement("select * from student where roll_no=?");
		ptt.setInt(1, roll);
		ResultSet rs=ptt.executeQuery();
		return rs;
	}
	
	
	
	
	int getNextRollno() throws SQLException
	{
		int num=1;
		st=cn.createStatement();
		
		ResultSet rs=st.executeQuery("select max(roll_no) from student");
		
		if(rs.next())
			num += rs.getInt(1);
		
		return num;
	}

	int getTotalStudent() throws SQLException
	{
		int num=0;
		st=cn.createStatement();
		
		ResultSet rs=st.executeQuery("select count(*) from student");
		
		if(rs.next())
			num=rs.getInt(1);
		
		return num;
	}
	

	String pwdDecrypt(String pwd)
	{
		Base64.Decoder decoder = Base64.getDecoder();  
	       
        String dStr = new String(decoder.decode(pwd));  
		return dStr;  
     }
	String pwdEncrypt(String pwd)
	{
		Base64.Encoder encoder=Base64.getEncoder();
		String str=encoder.encodeToString(pwd.getBytes());
		
		return str;
		
	}
int updatecourse(int roll,String co)throws SQLException
{
	course=cn.prepareStatement("update student set course=? where roll_no=? ");
	course.setString(1, co);
	course.setInt(2, roll);
	int x =course.executeUpdate();
	return x;
}

	
	
		
}

