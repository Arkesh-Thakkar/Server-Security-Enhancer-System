package portScannner;

import java.net.InetSocketAddress;
import mainMenu.HomePage;
    import java.net.Socket;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    import java.util.concurrent.Callable;
    import java.util.concurrent.ExecutionException;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.Future;
    import java.util.concurrent.TimeUnit;
    import javax.swing.*;

import mainMenu.HomePage;

import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;


public class PortScanner {
     private static String inStr;
     public PortScanner ()
    {
        //String inStr = JOptionPane.showInputDialog(null, "Enter the host",
        //    "Input Dialog", JOptionPane.PLAIN_MESSAGE);
        JFrame portFrame = new JFrame("PortScanner");

        Font font1 = new Font("SansSerif", Font.BOLD, 20);

        JLabel url = new JLabel();
                
        portFrame.add(url);
        url.setBounds(10, 5, 300, 100);

        url.setFont(font1);
        url.setHorizontalAlignment(JTextField.CENTER);

        url.setText("Enter the url:");

        JTextField textfield= new JTextField();
        textfield.setBounds(100, 70, 200, 30);

        JButton scanButton = new JButton("Scan");
        scanButton.setBounds(110,110,100, 40);
        JButton backbutton=new JButton("Back");
        backbutton.setBounds(220,110,100, 40);

        textfield.setFont(font1);
        textfield.setHorizontalAlignment(JTextField.CENTER);

        portFrame.add(textfield);
        portFrame.add(scanButton);
        
        portFrame.setSize(1000,650);
        portFrame.setLayout(null); 
        portFrame.setVisible(true);
        portFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        portFrame.add(backbutton);    
        portFrame.setSize(1000,650);    
        portFrame.setLayout(null);    
        portFrame.setVisible(true);    
        portFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        backbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				portFrame.setVisible(false);
	            HomePage h = new HomePage();
				
			}
		});

        scanButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
              inStr = textfield.getText();
            // System.out.println(name); //IT WORKS
      

        try
        {
        
        final ExecutorService es = Executors.newCachedThreadPool();
        //System.out.print("Please input the ip address you would like to scan for open ports: ");
        //Scanner inputScanner = new Scanner(System.in);
        final String ip = inStr;
        final int timeout = 2000;
        
        final List<Future<ScanResult>> futures = new ArrayList<>();
        for (int port = 1; port <= 65535; port++) {
            // for (int port = 1; port <= 80; port++) {
            futures.add(portIsOpen(es, ip, port, timeout));
        }
        es.awaitTermination(200L, TimeUnit.MILLISECONDS);
        int openPorts = 0;
        JLabel label = new JLabel();
                
        portFrame.add(label);
        label.setBounds(10, 110, 300, 100);

        label.setFont(font1);
        label.setHorizontalAlignment(JTextField.CENTER);

        label.setText("List of Open ports are:");
        int x =140;
        for (final Future<ScanResult> f : futures) {
            if (f.get().isOpen()) {
                openPorts++;
                System.out.println(f.get().getPort());
                JLabel l = new JLabel();

                l.setFont(font1);
                l.setHorizontalAlignment(JTextField.CENTER);

                portFrame.add(l);
                l.setBounds(10, x, 200, 100);
                x=x+30;        
                String s = String.valueOf(f.get().getPort());
                    l.setText(s);
            }
        }
       // portFrame.setVisible(true);
        System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
        + timeout + "ms)");

        JLabel label3 = new JLabel();

        label3.setFont(font1);
        label3.setHorizontalAlignment(JTextField.CENTER);

        portFrame.add(label3);
        label3.setBounds(10, x, 350, 100);
                        
        label3.setText("Total number of open ports are:"+openPorts);
        //portFrame.pack();

        
        
    }
    catch(Exception z)
    {
        z.printStackTrace();
        }
    }
    });

    }



    public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port,
    final int timeout) 
    {
        return es.submit(new Callable<ScanResult>() {
            @Override
            public ScanResult call() {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), timeout);
                    socket.close();
                    return new ScanResult(port, true);
                } catch (Exception ex) {
                    return new ScanResult(port, false);
                }
            }
        });
    }

    public static class ScanResult {
private int port;

private boolean isOpen;

public ScanResult(int port, boolean isOpen) {
    super();
    this.port = port;
    this.isOpen = isOpen;
}

public int getPort() {
    return port;
}

public void setPort(int port) {
    this.port = port;
}

public boolean isOpen() {
    return isOpen;
}

public void setOpen(boolean isOpen) {
    this.isOpen = isOpen;
}

    }
    }