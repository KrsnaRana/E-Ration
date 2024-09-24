package org.harsh.Model.Management;

import org.harsh.Database.DBManager;
import org.harsh.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public class UserManagement extends JFrame {

    DBManager db = new DBManager();
    private final UserManagement userManagement = this;
    private final DefaultTableModel tableModel;
    private final JTable taskTable;
    private final JTextField userName;
    private final JTextField userEmail;
    private final JTextField userAge;
    private final JTextField userCity;
    private final JTextField userState;
    private final JTextField userCountry;
    private final JTextField userDob;
    private final JTextField userRationNo;
    private final JPasswordField userPassword;
    private final JComboBox<String> sexTypeCbox;
    private final JComboBox<String> userTypeCbox;
    private final JComboBox<String> cardTypeCbox;

    public UserManagement() {
        setTitle("User management");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JLabel CardTypeLabel = new JLabel("Card type:");

        userName = new JTextField(20);
        userAge = new JTextField(20);
        userAge.setText("0");
        userState = new JTextField(20);
        userCity = new JTextField(20);
        userCountry = new JTextField(20);
        userDob = new JTextField(20);
        userRationNo = new JTextField(20);
        userPassword = new JPasswordField(20);
        userEmail = new JTextField(20);

        String[] userTypes = {"Seller", "Buyer"};
        String[] sexType = {"Male", "Female"};
        String[] CardTypes = {"APL", "BPL"};

        userTypeCbox = new JComboBox<>(userTypes);
        sexTypeCbox = new JComboBox<>(sexType);
        cardTypeCbox = new JComboBox<>(CardTypes);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Email");
        tableModel.addColumn("Password");
        tableModel.addColumn("Sex");
        tableModel.addColumn("State");
        tableModel.addColumn("city");
        tableModel.addColumn("country");
        tableModel.addColumn("DOB");
        tableModel.addColumn("Ration No");
        tableModel.addColumn("Card Type");
        tableModel.addColumn("User Role");

        taskTable = new JTable(tableModel);

        JScrollPane scrollPane;
        scrollPane = new JScrollPane(taskTable);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        JButton addBtn = new JButton("Add User");
        JButton updateBtn = new JButton("Update User");
        JButton deleteBtn = new JButton("Delete User");
        JButton backBtn = new JButton("Go Back");

        JPanel panel = new JPanel(new GridLayout(20, 2, 2, 5));
        panel.add(nameLabel);
        panel.add(userName);

        panel.add(ageLabel);
        panel.add(userAge);

        panel.add(emailLabel);
        panel.add(userEmail);

        panel.add(passwordLabel);
        panel.add(userPassword);

        panel.add(sexTypeLabel);
        panel.add(sexTypeCbox);

        panel.add(stateLabel);
        panel.add(userState);

        panel.add(cityLabel);
        panel.add(userCity);

        panel.add(countryLabel);
        panel.add(userCountry);

        panel.add(dobLabel);
        panel.add(userDob);

        panel.add(rationNoLabel);
        panel.add(userRationNo);

        panel.add(userTypeLabel);
        panel.add(userTypeCbox);

        panel.add(CardTypeLabel);
        panel.add(cardTypeCbox);

        panel.add(addBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(backBtn);

        panel.add(new JLabel());

        Container contentPane = new Container();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER);

        this.add(panel);
        this.setTitle("User Management");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        addBtn.addActionListener(actionEvent -> addUser());

        updateBtn.addActionListener(e -> updateUser());

        deleteBtn.addActionListener(e -> {
            userAge.setText("0");
            deleteUser();
        });

        backBtn.addActionListener(e -> userManagement.setVisible(false));

    }

    private void addUser() {
        String name = userName.getText();
        String age = userAge.getText();
        String email = userEmail.getText();
        String state = userState.getText();
        String city = userCity.getText();
        String country = userCountry.getText();
        String dob = userDob.getText();
        String rationNo = userRationNo.getText();
        char[] passwordChars = userPassword.getPassword();
        String password = new String(passwordChars);

        String sex = (String) sexTypeCbox.getSelectedItem();
        String card = (String) cardTypeCbox.getSelectedItem();
        String userType = (String) userTypeCbox.getSelectedItem();
        int ageC;

        try {
            ageC = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage() + "Invalid age. Age must be a valid integer.");
            return;
        }

        if (isValidPassword(password)) {
            User user = new User(name, password, userType, ageC, email, sex, state, city, dob, rationNo, card, country);
            if (db.registerUser(user)) {
                JOptionPane.showMessageDialog(this, "Registration successful. You can now log in.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. The password should 8 or more characters long and must be ended with @#$%^&+= . Please try again.");
        }

    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }

    private void updateUser() {
        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a user to update.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = userName.getText();
        String age = userAge.getText();
        String email = userEmail.getText();
        String password = userPassword.getText();
        String sex = (String) sexTypeCbox.getSelectedItem();
        String card = (String) cardTypeCbox.getSelectedItem();
        String userType = (String) userTypeCbox.getSelectedItem();
        String state = userState.getText();
        String city = userCity.getText();
        String country = userCountry.getText();
        String dob = userDob.getText();
        String rNo = userRationNo.getText();
        int ageC;

        try {
            ageC = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age. Age must be a valid integer.");
            return;
        }

        User user = new User(id, name, ageC, email, password, sex, state, city, country, dob, rNo, card, userType);

        if (db.updateUser(user)) {
            tableModel.setValueAt(user.getUserId(), selectedRow, 0);
            tableModel.setValueAt(user.getName(), selectedRow, 1);
            tableModel.setValueAt(user.getAge(), selectedRow, 2);
            tableModel.setValueAt(user.getEmail(), selectedRow, 3);
            tableModel.setValueAt(user.getPassword(), selectedRow, 4);
            tableModel.setValueAt(user.getSex(), selectedRow, 5);
            tableModel.setValueAt(user.getState(), selectedRow, 6);
            tableModel.setValueAt(user.getCity(), selectedRow, 7);
            tableModel.setValueAt(user.getCountry(), selectedRow, 8);
            tableModel.setValueAt(user.getDob(), selectedRow, 9);
            tableModel.setValueAt(user.getRationNo(), selectedRow, 10);
            tableModel.setValueAt(user.getCardType(), selectedRow, 11);
            tableModel.setValueAt(user.getRole(), selectedRow, 12);
            JOptionPane.showMessageDialog(this, "User updated Succesfully.");
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "User update failed.");
        }


    }

    private void deleteUser() {
        userAge.setText("0");
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a user to delete");
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = userName.getText();
        String age = userAge.getText();
        String email = userEmail.getText();
        String password = userPassword.getText();
        String sex = (String) sexTypeCbox.getSelectedItem();
        String card = (String) cardTypeCbox.getSelectedItem();
        String userType = (String) userTypeCbox.getSelectedItem();
        String state = userState.getText();
        String city = userCity.getText();
        String country = userCountry.getText();
        String dob = userDob.getText();
        String rNo = userRationNo.getText();

        int ageC;
        try {
            ageC = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age. Age must be a valid integer");
            return;
        }

        User user = new User(id, name, ageC, email, password, sex, state, city, country, dob, rNo, card, userType);

        if (db.deleteUser(user)) {
            tableModel.removeRow(selectedRow);
            clearInputFields();
            JOptionPane.showMessageDialog(this, rNo + " deleted successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Something went wrong");
        }
        userAge.setText("0");
    }

    private void clearInputFields() {
        userName.setText("");
        userAge.setText("");
        userEmail.setText("");
        userPassword.setText("");
        userState.setText("");
        userCity.setText("");
        userCountry.setText("");
        userDob.setText("");
        userRationNo.setText("");
    }

    public void loadUsers() {

        List<User> users = db.getAllUsers();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        for (User user : users) {
            tableModel.addRow(new Object[]{
                    user.getUserId(), user.getName(), user.getAge(), user.getEmail(), user.getPassword(), user.getSex(), user.getState(), user.getCity(), user.getCountry(), user.getDob(), user.getRationNo(), user.getCardType(), user.getRole()
            });
        }
        userAge.setText("0");
    }

}