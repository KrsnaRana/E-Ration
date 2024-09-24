package org.harsh.UI;

import org.harsh.Database.DBManager;
import org.harsh.Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class RegistrationFrame extends JFrame {
    private JTextField nameField, ageField, emailField, state, city, country, dob, rationNo;
    private JPasswordField passwordField;
    private JComboBox<String> sexTypeComboBox, userTypeComboBox, cardTypeComboBox;
    private DBManager databaseManager;

    public RegistrationFrame() {
        databaseManager = new DBManager();
        initializeUI();
    }

    private void initializeUI() {
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel stateLabel = new JLabel("State:");
        JLabel countryLabel = new JLabel("Country:");
        JLabel cityLabel = new JLabel("City:");
        JLabel dobLabel = new JLabel("DOB(yyyy-mm-dd):");
        JLabel rationNoLabel = new JLabel("Ration Number:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel userTypeLabel = new JLabel("User Type:");
        JLabel sexTypeLabel = new JLabel("Gender:");
        JLabel cardTypeLabel = new JLabel("Card type:");
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        state = new JTextField(20);
        city = new JTextField(20);
        country = new JTextField(20);
        dob = new JTextField(20);
        rationNo = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(20);
        String[] userTypes = {"Seller", "Buyer"};
        String[] sexType = {"Male", "Female"};
        String[] CardTypes = {"APL", "BPL"};
        userTypeComboBox = new JComboBox<>(userTypes);
        sexTypeComboBox = new JComboBox<>(sexType);
        cardTypeComboBox = new JComboBox<>(CardTypes);
        JButton registerButton = new JButton("Register");

        JPanel panel = new JPanel(new GridLayout(13, 2, 5, 5));

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(ageLabel);
        panel.add(ageField);

        panel.add(emailLabel);
        panel.add(emailField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(sexTypeLabel);
        panel.add(sexTypeComboBox);

        panel.add(stateLabel);
        panel.add(state);

        panel.add(cityLabel);
        panel.add(city);

        panel.add(countryLabel);
        panel.add(country);

        panel.add(dobLabel);
        panel.add(dob);

        panel.add(rationNoLabel);
        panel.add(rationNo);

        panel.add(userTypeLabel);
        panel.add(userTypeComboBox);

        panel.add(cardTypeLabel);
        panel.add(cardTypeComboBox);


        panel.add(new JLabel());
        panel.add(registerButton);

        registerButton.addActionListener(e -> registerUser());

        this.add(panel);
        this.setTitle("Registration");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void registerUser() {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();
        String state1 = state.getText();
        String city1 = city.getText();
        String country1 = country.getText();
        String dob1 = dob.getText();
        String rNo = rationNo.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        String sex = (String) sexTypeComboBox.getSelectedItem();
        String card = (String) cardTypeComboBox.getSelectedItem();
        String userType = (String) userTypeComboBox.getSelectedItem();

        if (isValidPassword(password)) {
            User user = new User(name, password, userType, age, email, sex, state1, city1, dob1, rNo, card, country1) ;
            if (databaseManager.registerUser(user)) {
                JOptionPane.showMessageDialog(this, "Registration successful. You can now log in.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).*$";
        return Pattern.matches(passwordPattern, password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistrationFrame::new);
    }
}