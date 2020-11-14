package dos;

import java.io.*;
import mainMenu.HomePage;
import java.net.*; 
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;

public class Client {  

public Client()
	{  
				JFrame f=new JFrame("DOS detection");

				Font font1 = new Font("SansSerif", Font.BOLD, 20);

				JLabel block = new JLabel();
				

		        JLabel dosDescription = new JLabel();
		                
		        f.add(dosDescription);
		        dosDescription.setBounds(10, 5, 300, 100);

		        dosDescription.setFont(font1);
		        dosDescription.setHorizontalAlignment(JTextField.CENTER);

		        dosDescription.setText("DOS detection demo"); 
					
				JButton b=new JButton("Send Packet");
				JButton backbutton=new JButton("Back");
				b.setBounds(100,100,140, 40); 
				backbutton.setBounds(260,100,140, 40); 


				f.add(b);    
				f.setSize(1000,650);    
				f.setLayout(null);    
				f.setVisible(true);    
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				f.add(backbutton);    
				f.setSize(1000,650);    
				f.setLayout(null);    
				f.setVisible(true);    
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				backbutton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						f.setVisible(false);
			            HomePage h = new HomePage();
						
					}
				});
				    
				
				b.addActionListener(new ActionListener() {
	        
					@Override
					public void actionPerformed(ActionEvent arg0) {
			try
			{
				Socket s=new Socket("localhost",6666);
				
				block.setVisible(false);

				DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
			
				dout.writeUTF("Packet"+'\n');  
				
				DataInputStream dis=new DataInputStream(s.getInputStream());
				String  closeCondition=(String)dis.readUTF();
				System.out.println(closeCondition);

				if(closeCondition.equals("Dos detected\nTemporarily Blocked")){    
			        f.add(block);
			        block.setBounds(10, 110, 300, 200);

			        block.setFont(font1);
			        block.setHorizontalAlignment(JTextField.CENTER);
			        block.setForeground(Color.red);
			        block.setText("Dos detected--->Temporarily Blocked");
					block.setVisible(true);
				} 	
			}

			catch(Exception e){System.out.println(e);}  	
		}          
	    });
	}  
}  