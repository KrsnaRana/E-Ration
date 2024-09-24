package org.harsh.UI;

import org.harsh.Database.DBManager;
import org.harsh.Model.User;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

public class LoginFrame extends JFrame {

    private JTextField EmailField;
    private JPasswordField passwordField;
    DBManager db = new DBManager();
    JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

    public LoginFrame() {
        initializeUI();
    }

    private void initializeUI() {
        JLabel titleLabel = new JLabel("Login");
        JLabel usernameLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password:");
        EmailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        panel.add(titleLabel);
        panel.add(new JLabel());
        panel.add(usernameLabel);
        panel.add(EmailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(e -> loginUser());

        this.add(panel);
        this.setTitle("Login");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void loginUser() {
        String email = EmailField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        if (db.loginUser(email, password)) {
            User user = db.getUserByUsername(email);

            invokeLater(() -> {
                if ("Seller".equals(user.getRole())) {
                    AdminFrame mainFrame = new AdminFrame(user);
                    mainFrame.setVisible(true);
                } else {
                    UserMainFrame userFrame = new UserMainFrame();
                    userFrame.setVisible(true);
                }
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, "Login Successful.");
            });

        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your username and password.");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        invokeLater(LoginFrame::new);
    }
}