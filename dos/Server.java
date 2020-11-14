package dos;

import java.io.*;
import java.net.*;  
import java.util.concurrent.TimeUnit;

public class Server {  

	public static void main(String[] args)
	{  
		int i=1;
		int count = 0;
		boolean dos = false;
		int countdown = 10000;
		int chance = 1;
		long startTimer = 0;
		try
		{
			 
			ServerSocket ss=new ServerSocket(6666);  
			while(true)	
			{    
				if(dos==false)
				{
					dos = false;
					Socket s=ss.accept();//establishes connection
					if(chance==1)
					{
					 	startTimer = System.nanoTime();
						chance = 0;
					}
					System.out.println(s);

					DataInputStream dis=new DataInputStream(s.getInputStream());  
					String  packet=(String)dis.readUTF();

					System.out.println(packet);
					count++;

					if(count==10){
						long endTimer = System.nanoTime();
						System.out.println(endTimer-startTimer);
						if(endTimer-startTimer<=59999999999.9988)
						{
							System.out.println("Flooding\nTemporarily Blocked");
							DataOutputStream dout=new DataOutputStream(s.getOutputStream());
							dout.writeUTF("Dos detected\nTemporarily Blocked");
							i=1;
							count = 0;
							dos = true;
						}
						else
						{
							chance = 1;
							count = 1;
							System.out.println("Packet accepted");
							DataOutputStream dout=new DataOutputStream(s.getOutputStream());
							dout.writeUTF("Packet accepted");
							count++;
							i=1;
						}
					}
					DataOutputStream dout=new DataOutputStream(s.getOutputStream());
					dout.writeUTF("Packet accepted");
				}

				else
				{
					Thread.sleep(10000);
						dos = false;
						countdown = 10000;
						chance = 1;
				}
			}
		}
			catch(Exception e){System.out.println(e);}  
	}  
} 