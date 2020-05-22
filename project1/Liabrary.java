package project1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Liabrary extends JFrame
{
	JMenu j1,j2,j3,j4,j5;
	JMenuBar jbar;
	JMenuItem jm1,jm2,jm3,jm4,jm5,jm6,jm7,jm8,jm9;
	JPanel jp1,jp2,jp3,jp4;
	JTextField t1,t2,t3,t4,t5,t6;
	JPasswordField pwd;
	JButton jb1,jb2,jb3,login,jb4,add,sort;
	LibraryDb db;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JTable table,table1;
	DefaultTableModel df,df1;
	String[] str,str1;
	String[] header={"isbn","book name","author","status","price","qty"};
	String[]header2= {"isbn","book name","author","Roll no","student name","issued date","return date"};
	int row ,col;
	JScrollPane scr,scr1;
	ArrayList<Book> ee;
	public Liabrary() 
		{	
		
		
			jp1=new JPanel();
			jp2=new JPanel();
			jp3=new JPanel();
			jp4=new JPanel();
			jp1.setBackground(Color.DARK_GRAY);
			jp2.setBackground(Color.darkGray);
			jp3.setBackground(Color.GRAY);
			jp4.setBackground(Color.GRAY);
			jp2.setPreferredSize(new Dimension(400,100));
			jp3.setPreferredSize(new Dimension(400,100));
			jp4.setPreferredSize(new Dimension(400,100));
			jp1.setLayout(null);
			jp3.setLayout(null);
			jp4.setLayout(null);
			l4=new JLabel("Welcome to library".toUpperCase());
			l4.setFont(new Font("Monaco", Font.PLAIN, 30));
			l4.setForeground(Color.white);
			jb4=new JButton("log out".toUpperCase());
			jb1=new JButton("Login");
			jb2=new JButton("Search Book");
			jb3=new JButton("show all Books");
			jp2.setLayout(null);
			l4.setBounds(300,50,500,50);
			jb1.setBounds(30,200,200,50);
			jb4.setBounds(30,200,200,50);
			jb2.setBounds(30,300,200,50);
			jb3.setBounds(30,400,200,50);
			jb4.setVisible(false);
			jp1.add(l4);
			jp1.add(jb4);
			df=new DefaultTableModel(header,0);
			df1=new DefaultTableModel(header2,0);
			// ee=BookStream.getBookList();
			
			table1=new JTable(df1);
			
			table=new JTable(df);
			scr=new JScrollPane(table);
			scr1=new JScrollPane(table1);
			scr1.setBounds(300,200,600,300);
			
			scr.setBounds(300,200,600,300);
			scr.setVisible(false);
			//jp1.add(jb3);
			jp1.add(jb2);
			jp1.add(jb1);
			
			scr1.setVisible(false);

			add(scr1,BorderLayout.EAST);
			add(scr,BorderLayout.EAST);
			add(jp1,BorderLayout.CENTER);
			add(jp2,BorderLayout.SOUTH);
			add(jp3,BorderLayout.EAST);
			add(jp4,BorderLayout.EAST);
			menubar();
			
			login();
		
			jbar.setVisible(false);
			jp3.setVisible(false);
			jp4.setVisible(false);
			ActionListener al=(ActionEvent e)->{btn(e);};
			jb1.addActionListener(al);
			jb4.addActionListener(al);	
			jb2.addActionListener(al);
			jb3.addActionListener(al);
			
		}
	private void btn(ActionEvent e) 
	{
		
		if(e.getSource()==jb1)
		{
			
			jp4.setVisible(true);
			jp4.removeAll();
			scr.setVisible(false);
			
			login();
			
			
		}
		else if(e.getSource()==jb2)
		{
			SearchBook l=new SearchBook();
			l.setVisible(true);
			l.setSize(400,400);
			l.setLocationRelativeTo(null);;
		}
		else if(e.getSource()==jb3)
		{
			removerow();
			ee=BookStream.getBookList();
			List<Book> lst=ee.stream().filter(a->a.getstatus().equals("available")).collect(Collectors.toList());
			table(lst);
			scr.setVisible(true);
			scr1.setVisible(false);
		}
		else if(e.getSource()==jb4)
		{
			System.exit(0);
		}
	}
	void login()
	{
	t1=new JTextField("7247302898");
	pwd= new JPasswordField("12345");

	
	l1=new JLabel("email/phone".toUpperCase(),JLabel.LEFT);
	l2=new JLabel("Password".toUpperCase(),JLabel.LEFT);
	l3=new JLabel("admin Log in".toUpperCase(),JLabel.CENTER);
	l3.setFont(new Font("Monaco", Font.PLAIN, 20));
	l1.setForeground(Color.white);
	l2.setForeground(Color.white);
	l3.setForeground(Color.white);
	
	login=new JButton("Log in".toUpperCase());
	login.setBackground(Color.DARK_GRAY);
	login.setForeground(Color.white);
	
	l3.setBounds(50,155,250,40);
	l1.setBounds(15,200,80,40);
	l2.setBounds(15,250,80,40);
	t1.setBounds(100,200,150,30);
	pwd.setBounds(100,250,150,30);
	login.setBounds(100,300,150,30);
	
	jp4.add(l3);
	jp4.add(l1);
	jp4.add(l2);
	jp4.add(t1);
	jp4.add(pwd);
	jp4.add(login);
	
	

	ActionListener al=(ActionEvent e)->{btn1(e);};
	login.addActionListener(al);
	
}
private void btn1(ActionEvent e) 


{
	if(e.getSource()==login)
	{
		try
		{
			db=new LibraryDb();
			char[] pwd1=pwd.getPassword();
			String p1=new String(pwd1);
			ResultSet rs=db.adminLogin(t1.getText(),p1);
			if(rs.next())
			{
				
				l4.setText("Welcome" +" "+rs.getString("name").toUpperCase());
				l4.setBounds(380,50,300,50);
				jp4.setVisible(false);
				jb1.setVisible(false);
				jb4.setVisible(true);
				jbar.setVisible(true);
			}
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}
}

void menubar()
{
	j1=new JMenu("display");
	j2=new JMenu("student");
	j3=new JMenu("book");
	j4=new JMenu("sort");
	j5=new JMenu("return");
	
	jm1=new JMenuItem("display books".toUpperCase());
	jm2=new JMenuItem("issue book".toUpperCase());
	jm3=new JMenuItem("new Student".toUpperCase());
	jm4=new JMenuItem("add new book".toUpperCase());
	jm5=new JMenuItem("display available books".toUpperCase());//
	
	jm6=new JMenuItem("sort by price");
	jm7=new JMenuItem("sort by isbn");
	jm8=new JMenuItem("show issued books data ");
	jm9=new JMenuItem("retun book");
	jbar=new JMenuBar();
	jbar.add(j1);
	jbar.add(j2);
	jbar.add(j3);
	jbar.add(j4);
	jbar.add(j5);
	j1.add(jm5);
	j1.add(jm8);
	j2.add(jm2);
	//j2.add(jm3);
	j3.add(jm4);
	j3.add(jm1);
	j4.add(jm6);
	j4.add(jm7);
	j5.add(jm9);
	add(jbar,BorderLayout.NORTH);
	
	ActionListener al= (ActionEvent t)->{btnn(t);};
	jm4.addActionListener(al);
	jm1.addActionListener(al);
	jm5.addActionListener(al);
	jm6.addActionListener(al);
	jm7.addActionListener(al);
	jm8.addActionListener(al);
	jm2.addActionListener(al);
	jm9.addActionListener(al);
}
	
	private void btnn(ActionEvent t)
	{
	
		JMenuItem im=(JMenuItem)t.getSource();
		if(im==jm4)
		{
			LibraryLogin l=new LibraryLogin(this);
			l.setSize(520,500);
			l.setVisible(true);
			l.setLocationRelativeTo(null);
		}
		else if(im==jm1)
		{
			removerow();
			ee=BookStream.getBookList();
			table(ee);
			scr.setVisible(true);
			scr1.setVisible(false);
		}
		else if(im==jm5)
		{
			 removerow();
			 ee=BookStream.getBookList();
			 List<Book> b=ee.stream().filter(e->e.getstatus().equals("available")).collect(Collectors.toList());
			 table(b);
			 scr.setVisible(true);
			 scr1.setVisible(false);
		}
		
		else if(im==jm6)
		{
			removerow();
			ee=BookStream.getBookList();
			List<Book> b=ee.stream().sorted((p1,p2)->p1.getprice()-p2.getprice()).collect(Collectors.toList());
			System.out.println(b);
			table(b);
			scr.setVisible(true);
			scr1.setVisible(false);
		}
		else if(im==jm7)
		{
			removerow();
			ee=BookStream.getBookList();
			List<Book> b=ee.stream().sorted((p1,p2)->p1.getisbn()-p2.getisbn()).collect(Collectors.toList());
			table(b);
			scr.setVisible(true);
			scr1.setVisible(false);
		}
		else if(im==jm8)
		{
			removerow1();
			issueTable();
			
			scr1.setVisible(true);
			scr.setVisible(false);
		}
		else if(jm2==im)
		{
			Issue_form i=new Issue_form(this);
			
			i.setSize(500,500);
			i.setVisible(true);
			i.setLocationRelativeTo(null);
			
		}
		else if(im==jm9)
		{
			
			ReturnBook dig=new ReturnBook(this);
			
			dig.setSize(500,600);
			dig.setVisible(true);
			dig.setLocationRelativeTo(null);
			
		}
		
	}

void removerow()
{
	int rowCount = df.getRowCount();
	
	for (int i = rowCount - 1; i >= 0; i--)
	{
	    df.removeRow(i);
	  
	}
}

void removerow1()
{
	int rowCount = df1.getRowCount();
	
	for (int i = rowCount - 1; i >= 0; i--)
	{
	    df1.removeRow(i);
	  
	}
}
	void table(List<Book> l)
	{
		for(Book e1:l)
		{	str =new String[6];
			str[0]=Integer.toString(e1.getisbn());
			str[1]=e1.getname();
			str[2]=e1.getauthor();
			str[3]=e1.getstatus();
			str[4]=Integer.toString(e1.getprice());
			str[5]=Integer.toString(e1.getqty());
			df.addRow(str);
		}
	}
	
	
	
	void issueTable()
	{
		try
		{
			db=new LibraryDb();
			
			ResultSet rs=db.getissuedBook();
			while(rs.next())
			{
				str1=new String[7];
				str1[0]=Integer.toString(rs.getInt("isbn"));
				str1[1]=rs.getString("b_name");
				str1[2]=rs.getString("author");
				str1[3]=Integer.toString(rs.getInt("roll_no"));
				str1[4]=rs.getString("sname");
				str1[5]=rs.getString("date_of_issue");
				str1[6]=rs.getString("date_of_return");
				df1.addRow(str1);
				
			}
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
	}
	public static void main(String[] s)
	{
	
		
		Liabrary j=new Liabrary();
		j.setSize(1250,700);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
		
	}

}

class BookStream
{
	static ArrayList<Book> bk;
	static LibraryDb db;
	
	static
	{
		try
		{
			db=new LibraryDb();
			ResultSet rs=db.getBook();
			bk=new ArrayList<Book>();
			while(rs.next())
			{
				int isbn=rs.getInt("isbn");
				String name=rs.getString("b_name");
				String author=rs.getString("author");
				String status=rs.getString("status");
				int price=rs.getInt("price");
				int qty=rs.getInt("qty");
				
				Book b=new Book(isbn,name,author,status,price,qty);
				
				bk.add(b);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	static ArrayList<Book> getBookList()
	{
		return bk;
	}
}