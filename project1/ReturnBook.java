package project1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.DateFormatter;

public class ReturnBook extends JFrame
{
	JTextField tf1,tf2,tf7,tf6,tf5,tf4,tf3;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,j1,j2,j3,j4,j5,j6,j7;
	JFormattedTextField t1;
	DateFormatter dtf;
	JPanel jp1,jp2;
	JButton search,ret;
	LibraryDb bdb;
	StudentDb sdb;
	LocalDate dt;
	Liabrary k;
	ArrayList<Book> bk;
	public ReturnBook() {init();}

	public ReturnBook(Liabrary k) 
	{
		this.k=k;
		init();
	}
	public void init() 
	{
		
		dt=LocalDate.now();
		System.out.println(dt);
		LocalDate x=dt.plusMonths(6);

		tf1=new JTextField();
		tf2=new JTextField();
		l1=new JLabel("enter isbn number",JLabel.LEFT);
		l2=new JLabel("enter roll number",JLabel.LEFT);
		jp1=new JPanel();
		jp1.setLayout(null);
		jp1.setBackground(Color.gray);
		jp2=new JPanel();
		jp2.setLayout(null);
		jp2.setPreferredSize(new Dimension(100,70));
		jp2.setBackground(Color.DARK_GRAY);
		ret=new JButton("return");
		search=new JButton("search");
		l3=new JLabel("return book".toUpperCase(),JLabel.CENTER);
		l4=new JLabel("roll no".toUpperCase(),JLabel.LEFT);
		l5=new JLabel("student name".toUpperCase(),JLabel.LEFT);
		l6=new JLabel("isbn".toUpperCase(),JLabel.LEFT);
		l7=new JLabel("book name".toUpperCase(),JLabel.LEFT);
		l8=new JLabel("date of issue".toUpperCase(),JLabel.LEFT);
		l9=new JLabel("Summary");
		
		j1=new JLabel();
		j2=new JLabel();
		j3=new JLabel();
		j4=new JLabel();
		j5=new JLabel();
		j6=new JLabel();
		j7=new JLabel();
		
		
		
		
		
		l1.setBounds(20,10,120,40);
		l2.setBounds(20,50,120,40);
		
		tf1.setBounds(150,10,150,30);search.setBounds(310,25,100,40);
		tf2.setBounds(150,50,150,30);
		
		l9.setBounds(190,160,100,30);
		l4.setBounds(150,210,100,30);
		l5.setBounds(150,240,100,30);
		l6.setBounds(150,270,100,30);
		l7.setBounds(150,300,100,30);
		l8.setBounds(150,330,100,30);
		
		
		j2.setBounds(250,210,100,30);
		j3.setBounds(250,240,100,30);
		j4.setBounds(250,270,100,30);
		j5.setBounds(250,300,100,30);
		j6.setBounds(250,330,100,30);
		j7.setBounds(250,330,100,30);
		
		ret.setBounds(190,360,100,30);
		
		j2.setForeground(Color.white);
		j3.setForeground(Color.white);
		j4.setForeground(Color.white);
		j5.setForeground(Color.white);
		j6.setForeground(Color.white);
		j7.setForeground(Color.white);
		
		l3.setFont(new Font("Monaco", Font.PLAIN, 20));
		l3.setBounds(150,8,200,50);
		
		l3.setForeground(Color.white);
		l1.setForeground(Color.white);
		l2.setForeground(Color.white);
		l4.setForeground(Color.white);
		l6.setForeground(Color.white);
		l5.setForeground(Color.white);
		l7.setForeground(Color.white);
		l9.setForeground(Color.white);
		
		l8.setForeground(Color.white);
		
		
		jp1.add(j1);
		jp1.add(j2);
		jp1.add(j3);
		jp1.add(j4);
		jp1.add(j5);
		jp1.add(j6);
		jp1.add(j7);
		
		jp1.add(l1);
		jp1.add(l2);
		jp2.add(l3);
		jp1.add(l4);
		jp1.add(l5);
		jp1.add(l6);
		jp1.add(l7);
		jp1.add(l8);
		jp1.add(l9);
		jp1.add(tf1);
		jp1.add(tf2);
		jp1.add(search);
		jp1.add(ret);
		add(jp1,BorderLayout.CENTER);
		add(jp2,BorderLayout.NORTH);
		ActionListener al=(ActionEvent e)->{btn(e);};
		ret.addActionListener(al);
		search.addActionListener(al);
		
	}

	private void btn(ActionEvent e) 
	{
		if(e.getSource()==search)
		{
			try 
			{
				int isbn=Integer.parseInt(tf1.getText());
				int roll_no=Integer.parseInt(tf2.getText());
				
				bdb=new LibraryDb();
				ResultSet rs=bdb.checkbook(isbn, roll_no);
				if(rs.next()) 
				{
					
					j2.setText(rs.getString(5));
					j3.setText(rs.getString(3));
					j4.setText(rs.getString(2));
					j5.setText(rs.getString(6));
					j6.setText(rs.getString(7));
					
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"no user or book exist");
				}
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==ret)
		{
			try
			{
				bdb=new LibraryDb();
				int isbn=Integer.parseInt(tf1.getText());
				ResultSet rs=bdb.getBook(isbn);
				if(rs.next())
				{
					int qty = rs.getInt("qty");
					
					System.out.println(qty);
				
						int x =bdb.Updatebookstatus(isbn, qty+1);
					if(x==1)
					{
						bdb.returnbook(isbn);
						JOptionPane.showMessageDialog(null,"book returned");
						
						dispose();
					}
				}
			}
			catch(Exception r)
			{
				r.printStackTrace();
			}
			
		}
		
		
	}

	public static void main(String[] s)
	{
		ReturnBook k=new ReturnBook();
		k.setSize(500,500);
		k.setVisible(true);
		k.setLocationRelativeTo(null);
	}
	

}
