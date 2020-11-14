package mainMenu;

import bruteForce.BruteForceUI;
import dos.Client;
import portScannner.PortScanner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class HomePage extends JFrame implements ActionListener {

    JButton bfButton = new JButton("Brute Force Demo");
    JButton dosButton = new JButton("DOS Demo");
    JButton portScannerButton = new JButton("Open Port Scanner");
    JPanel panel = new JPanel();

    public HomePage(){


        setSize(1000,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Selection Module Page");
        add(bfButton);
        add(dosButton);
        add(portScannerButton);



        bfButton.setVisible(true);
        bfButton.setBounds(700,400,175,30);
        dosButton.setVisible(true);
        dosButton.setBounds(700,450,175,30);
        portScannerButton.setVisible(true);
        portScannerButton.setBounds(700,350,175,30);
        bfButton.addActionListener(this);
        portScannerButton.addActionListener(this);
        dosButton.addActionListener(this);
        setVisible(true);

    }

    public static void main(String[] args) {
        HomePage h = new HomePage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == bfButton)
        {
            setVisible(false);
            BruteForceUI b = new BruteForceUI();
        }
        else if(e.getSource() == portScannerButton)
        {
            setVisible(false);
            PortScanner p = new PortScanner();
        }
        else if(e.getSource() == dosButton)
        {
            setVisible(false);
            Client p = new Client();
        }
    }


}
