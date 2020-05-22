package project1;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LibraryLogin extends JFrame
{
	/*
	Full texts	
	book_id
	isbn
	b_name
	author
	status
	price
	 */
	JPanel jp1,jp2;
	JTextField t1,t2,t4,t5,t3,t6,t8;
	
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JButton add;
	LibraryDb db;
	Liabrary lib;
	public LibraryLogin(Liabrary liabrary) 
	{
		init();
		this.lib=liabrary;
	}

	void init()
	{
		try 
		{
		db=new LibraryDb();
		int s=db.getNextisbn();
		t2=new JTextField();
		t2.setText(s+"");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		t3=new JTextField();
		t4=new JTextField();
		t5=new JTextField();
		t8=new JTextField();
		l2=new JLabel("isbn",JLabel.LEFT);
		l3=new JLabel("name",JLabel.LEFT);
		l4=new JLabel("author",JLabel.LEFT);
		l5=new JLabel("price",JLabel.LEFT);
		l7=new JLabel("Add New Book To Library",JLabel.CENTER);
		l8=new JLabel("quantity",JLabel.LEFT);
		add=new JButton("Add Book");
		jp1=new JPanel();
		jp2=new JPanel();
		jp1.setPreferredSize(new Dimension(100,100));
		jp2.setPreferredSize(new Dimension(200,100));
		jp2.setBackground(Color.LIGHT_GRAY);
		jp1.setBackground(Color.DARK_GRAY);
		
		jp1.setLayout(null);
		jp2.setLayout(null);
		l7.setFont(new Font("Monaco", Font.PLAIN, 30));
		l7.setForeground(Color.white);
		
		
		l7.setBounds(40,20,350,40);
		l2.setBounds(50,40,80,30);
		l3.setBounds(50,80,80,30);
		l4.setBounds(50,120,80,30);
		l5.setBounds(50,160,80,30);
		l8.setBounds(50,200,80,30);
		
		t2.setBounds(130,40,150,30);
		t3.setBounds(130,80,150,30);
		t4.setBounds(130,120,150,30);
		t5.setBounds(130,160,150,30);
		t8.setBounds(130,210,150,30);
		add.setBounds(130,250,150,30);
		

		jp2.add(l2);
		jp2.add(l3);
		jp2.add(l4);
		jp2.add(l5);
		jp2.add(l8);
		jp2.add(t8);
		jp1.add(l7);
		

		jp2.add(t2);
		jp2.add(t3);
		jp2.add(t4);
		jp2.add(t5);
	
		jp2.add(add);
		
		
		add(jp1,BorderLayout.NORTH);
		add(jp2,BorderLayout.CENTER);
		
		ActionListener al=(ActionEvent e)->{btn(e);};
		add.addActionListener(al);
		
	}
	
	private void btn(ActionEvent e) 
	{
		JButton jb=(JButton)e.getSource();
		
		if(jb==add)
		{
			try 
				{
					db=new LibraryDb();
					int x=db.addbook(Integer.parseInt(t2.getText()),t3.getText(),t4.getText(),Integer.parseInt(t5.getText()),Integer.parseInt(t8.getText()));
					if(x==1)
					{	
						String[] s= new String[6];
						s[0]=t2.getText();
						s[1]=t3.getText();
						s[2]=t4.getText();
						s[3]=t5.getText();
						s[4]="available";
						s[5]=t8.getText();
						
						lib.df.addRow(s);
						JOptionPane.showMessageDialog(null,"New book added:".toUpperCase());
						dispose();
					}
				}
			
	
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
		}
	}
	
	
	
	

}
