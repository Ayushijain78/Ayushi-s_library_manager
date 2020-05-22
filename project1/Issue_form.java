package project1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

public class Issue_form extends JFrame
{
	JTextField tf1,tf2,tf7,tf6,tf5,tf4,tf3;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JFormattedTextField t1;
	DateFormatter dtf;
	JPanel jp1;
	JButton bsearch,ssearch,issue;
	LibraryDb bdb;
	StudentDb sdb;
	LocalDate dt;
	ArrayList<Book> bk;
	Liabrary b;
	public Issue_form(){init();} 
	
	public Issue_form(Liabrary b)
	{
		init();
		this.b=b;
	} 
	
	void init()
	{
		
		dt=LocalDate.now();
		System.out.println(dt);
		LocalDate x=dt.plusMonths(6);
		
		issue=new JButton("issue");
		tf1=new JTextField();
		tf2=new JTextField();
		tf3=new JTextField();
		tf4=new JTextField();
		tf5=new JTextField();
		tf6=new JTextField();
		tf7=new JTextField();
		tf6.setText(dt+"");
		tf6.setEnabled(false);
		tf7.setText(x+"");
		tf7.setEnabled(false);
		
		bsearch=new JButton("seach book");
		ssearch=new JButton("Search student");
		l1=new JLabel("isbn",JLabel.LEFT);
		l2=new JLabel("book name",JLabel.LEFT);
		
		l4=new JLabel("roll no",JLabel.LEFT);
		l5=new JLabel("student name",JLabel.LEFT);
		l6=new JLabel("date of issue",JLabel.LEFT);
		l3=new JLabel("author",JLabel.LEFT);
		l7=new JLabel("date of return",JLabel.LEFT);
		
		l1.setBounds(30,20,100,30);
		l2.setBounds(30,60,100,30);
		l3.setBounds(30,100,100,30);
		l4.setBounds(30,140,100,30);
		l5.setBounds(30,180,100,30);
		l6.setBounds(30,220,100,30);
		l7.setBounds(30,260,100,30);
		
		l1.setForeground(Color.white);
		l2.setForeground(Color.white);
		l3.setForeground(Color.white);
		l4.setForeground(Color.white);
		l5.setForeground(Color.white);
		l6.setForeground(Color.white);
		l7.setForeground(Color.white);
		
		tf1.setBounds(130,20,150,30);bsearch.setBounds(290,20,150,30);
		tf2.setBounds(130,60,150,30);
		tf3.setBounds(130,100,150,30);
		tf4.setBounds(130,140,150,30);ssearch.setBounds(290,140,150,30);
		tf5.setBounds(130,180,150,30);
		tf6.setBounds(130,220,150,30);
		tf7.setBounds(130,260,150,30);
		
		issue.setBounds(130,300,150,30);
		jp1=new JPanel();
		jp1.setLayout(null);
		jp1.setBackground(Color.GRAY);
		jp1.add(l1);jp1.add(l2);jp1.add(l3);jp1.add(l4);jp1.add(l5);jp1.add(l6);
		jp1.add(tf1);jp1.add(tf2);jp1.add(tf3);jp1.add(tf4);jp1.add(tf5);jp1.add(tf6);
		jp1.add(tf7);
		jp1.add(l7);
		jp1.add(ssearch);
		jp1.add(bsearch);
		jp1.add(issue);
		issue.setEnabled(false);
		add(jp1,BorderLayout.CENTER);
		ActionListener al=(ActionEvent e)->{btn(e);};
		ssearch.addActionListener(al);
		bsearch.addActionListener(al);
		issue.addActionListener(al);
	}
	
	private void btn(ActionEvent e)
	{
		if(e.getSource()==ssearch)
		{
			try
			{  
				sdb=new StudentDb();
				ResultSet rs=sdb.getStudentData(Integer.parseInt(tf4.getText()));
				ResultSet rs1=bdb.checkbook(Integer.parseInt(tf1.getText()),Integer.parseInt(tf4.getText()));
				boolean rsd=rs1.next();
				System.out.println(rsd);
				if(rs.next()  && rsd==false)
				{		
					bk=BookStream.getBookList();
					List<Book>g=bk.stream().collect(Collectors.toList());
					b.table(g);
					tf5.setText(rs.getString("name"));
					System.out.println(rs.getString("name"));
				}
				else
				{
					JOptionPane.showMessageDialog(null,"you alredy issued this book");
				}
			}
			
			
			catch (Exception e2)
			{
				e2.printStackTrace();
				
			}
		}
		else if(e.getSource()==bsearch)
		{
			try
			{
				bdb=new LibraryDb();
				ResultSet rs=bdb.getBook(Integer.parseInt(tf1.getText()));
				
				if(rs.next() && rs.getString("status").equals("available") )
				
				{
					if( rs.getInt("qty")>0)
					{
						if(tf2.getText()!=null&&tf3.getText()!=null&&tf4.getText()!=null)
						{
							int qty=rs.getInt("qty")-1;
							issue.setEnabled(true);
							int x=bdb.Updatebookstatus(Integer.parseInt(tf1.getText()),"available",qty);
							
							
							tf2.setText(rs.getString("b_name"));
							tf3.setText(rs.getString("author"));
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Currently Not Available");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"no book available".toUpperCase());
				//	dispose();
				}
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
				
			}
		}
		if(e.getSource()==issue)
		{
			
			if(tf2!=null && tf5!=null )
			{
				try
				{
					bdb=new LibraryDb();
					
						
						int x=bdb.issueBook(Integer.parseInt(tf1.getText()),tf2.getText(),tf3.getText(),Integer.parseInt(tf4.getText()),tf5.getText(),tf6.getText(),tf7.getText());
					
						if(x==1)
						{
							JOptionPane.showMessageDialog(null,"issued");
							dispose();
						}
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
		
		
	}

	public static void main(String[] s)
	{
		Issue_form k=new Issue_form();
		k.setSize(500,500);
		k.setVisible(true);
		k.setLocationRelativeTo(null);
	}
	

}
