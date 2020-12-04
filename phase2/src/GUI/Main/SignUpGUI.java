package GUI.Main;

import Controller.LoginSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SignUpGUI implements ILoginView, ActionListener {
    private PanelStack panelStack;
    private JFrame frame;
    private LoginSystem loginSystem;
    private JPanel signUpPanel = new JPanel();
    private JLabel titleLabel = new JLabel();
    private JLabel username = new JLabel();
    private JLabel password = new JLabel();
    private JLabel programTitle = new JLabel();
    private JTextField usertext = new JTextField(20);
    private JPasswordField passtext = new JPasswordField(20);
    private JButton signUpButton = new JButton();
    private String[] userTypes = {"Attendee", "Organizer"};
    private JComboBox typeComboBox = new JComboBox(userTypes);
    private MainMenuGUI mainMenuGUI;
    private JButton backButton = new JButton();

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,500);
//        frame.setResizable(false);
//        LoginSystem loginSystem = new LoginSystem();
//        frame.setContentPane(new SignUpGUI(loginSystem, frame).signUpPanel);
//        frame.setVisible(true);
//    }

    public SignUpGUI(MainMenuGUI menu, LoginSystem loginSystem, PanelStack panelStack) {
        this.loginSystem = loginSystem;
        this.panelStack = panelStack;
        mainMenuGUI = menu;
        signUpPage();
        signUpButton.addActionListener(this);
    }

    public JPanel signUpPage(){
        // PANEL:
        signUpPanel.setLayout(null);
        signUpPanel.setSize(500, 500);
        // SIGNUP TITLE:
        titleLabel.setText("Sign Up");
        titleLabel.setFont(new Font("", Font.BOLD, 20));
        titleLabel.setVisible(true);
        titleLabel.setBounds(219, 164, 80, 30);
        signUpPanel.add(titleLabel);
        // PROGRAM TITLE:
        programTitle.setText("THE AMONG US SUMMIT");
        programTitle.setBounds(125, 10, 500, 60);
        programTitle.setFont(new Font("", Font.BOLD, 20));
        signUpPanel.add(programTitle);
        // USERNAME:
        username.setText("Username");
        username.setBounds(123, 214, 80, 25);
        signUpPanel.add(username);
        usertext.setBounds(193, 214, 165, 25);
        signUpPanel.add(usertext);
        // PASSWORD:
        password.setText("Password");
        password.setBounds(123, 264, 80, 25);
        signUpPanel.add(password);
        passtext.setBounds(193, 264, 165, 25);
        signUpPanel.add(passtext);
        // COMBOBOX:
        typeComboBox.setBounds(214, 314, 80, 25);
        signUpPanel.add(typeComboBox);
        // SIGNUP BUTTON:
        signUpButton.setText("Sign Up");
        signUpButton.setBounds(214, 364, 80, 25);
        signUpPanel.add(signUpButton);
        // BACK BUTTON:
        backButton.setText("Back");
        backButton.setBounds(10, 430, 80, 25);
        signUpPanel.add(backButton);
        backButtonListen();
        return signUpPanel;
    }

    private void backButtonListen(){
        backButton.addActionListener(e -> {
            frame.setContentPane(mainMenuGUI.startMainMenuPage());
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String uname = usertext.getText();
        String pword = passtext.getText();

        if (!loginSystem.isUser(uname)) {
            if (Objects.equals(typeComboBox.getSelectedItem(), "Attendee")) {
                loginSystem.signUpUser(uname, pword, "A");
                JOptionPane.showMessageDialog(signUpPanel, "You have successfully signed up as an Attendee.");
                frame.setContentPane(mainMenuGUI.startMainMenuPage());
                signUpPanel.setVisible(false);
            }
            else {
                String input = JOptionPane.showInputDialog("Please enter the Organizer code.");
                if (input.equals("AmongUs")) {
                    loginSystem.signUpUser(uname, pword, "O");
                    JOptionPane.showMessageDialog(signUpPanel, "You have successfully signed up as an Organizer.");
                    frame.setContentPane(mainMenuGUI.startMainMenuPage());
                    signUpPanel.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(signUpPanel, "Invalid Organizer code.");
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(signUpPanel, "Username already exists. Please select a different username.");
        }
    }
}
