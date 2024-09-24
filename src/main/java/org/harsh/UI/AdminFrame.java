package org.harsh.UI;

import org.harsh.Database.DBManager;
import org.harsh.Model.Management.ProductManagement;
import org.harsh.Model.Management.UserManagement;
import org.harsh.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {

    static User currentUser;
    DBManager dbManager;
    ProductManagement productManagement;
    UserManagement userManagement;

    public AdminFrame(User currentUser) {
        this.currentUser = currentUser;
        dbManager = new DBManager();

        initializeUI();
    }

    private void initializeUI() {

        JButton productButton = new JButton("Product Management");
        JButton userButton = new JButton("User Management");

        JPanel panel = new JPanel(new BorderLayout());

        JPanel namesPanel = new JPanel(new GridLayout(3, 1));

        panel.add(namesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(productButton);
        buttonPanel.add(userButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        userButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            userManagement = new UserManagement();
            userManagement.setVisible(true);
            userManagement.loadUsers();
        }));

        productButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            productManagement = new ProductManagement();
            productManagement.setVisible(true);
            productManagement.loadTasks();
        }));

        this.add(panel);

        this.setTitle("Admin Panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {

    }

}