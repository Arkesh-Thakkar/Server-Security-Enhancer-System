package bruteForce;

import mainMenu.HomePage;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BruteForceUI extends JFrame implements ActionListener {

    JButton submitButton,backButton;
    JLabel unameLabel,pwdLabel;
    final JTextField text1;
    final JPasswordField text2;

    public BruteForceUI(){

        setSize(1000,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Login Page");
        unameLabel = new JLabel();
        unameLabel.setText("Username");
        pwdLabel = new JLabel();
        pwdLabel.setText("Password");
        text1 = new JTextField(15);
        text2 = new JPasswordField(15);
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        add(unameLabel);
        add(text1);
        add(pwdLabel);
        add(text2);
        add(submitButton);
        add(backButton);

        unameLabel.setVisible(true);
        unameLabel.setBounds(200,200,100,50);
        pwdLabel.setVisible(true);
        pwdLabel.setBounds(200,250,100,50);
        text1.setVisible(true);
        text1.setBounds(300,200,400,50);
        text2.setVisible(true);
        text2.setBounds(300,250,400,50);
        submitButton.setVisible(true);
        submitButton.setBounds(250,350,100,50);
        backButton.setVisible(true);
        backButton.setBounds(350,350,100,50);
        setVisible(true);
        submitButton.addActionListener(this);
        backButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == submitButton)
        {
            int authResult = Authentication.Auth(text1.getText(),text2.getText());
            if (authResult==0){
                setVisible(false);
                Welcome w = new Welcome();
            } else if(authResult == 1){
                JOptionPane.showMessageDialog(this,"This Account is blocked","Error",JOptionPane.ERROR_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
            }


        }
        if(e.getSource() == backButton)
        {
            setVisible(false);
            HomePage h = new HomePage();
        }
    }
}

