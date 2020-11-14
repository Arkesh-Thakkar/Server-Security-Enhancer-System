package bruteForce;

import mainMenu.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame implements ActionListener {

    public Welcome(){
        setSize(1000,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Home Page Account");
        JLabel welcomeText = new JLabel();
        welcomeText.setText("Welcome To your Account");

        add(welcomeText);
        welcomeText.setVisible(true);
        welcomeText.setBounds(400,300,300,50);

        JButton backButton = new JButton("Back");
        add(backButton);
        backButton.setVisible(true);
        backButton.setBounds(500,350,100,50);
        backButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        HomePage h = new HomePage();
    }
}
