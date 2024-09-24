package org.harsh.UI;

import org.harsh.Database.DBManager;
import org.harsh.Model.Product;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UserMainFrame extends JFrame {
    private final DefaultTableModel tableModel;
    private final JTable taskTable;
    DBManager db = new DBManager();
    private final JTextField buyQuantityTextField;
    String print;

    public UserMainFrame() {
        setTitle("Product Shop");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Sr No");
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Available Stock");
        tableModel.addColumn("Availability");

        taskTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        buyQuantityTextField = new JTextField(20);
        JButton buyButton = new JButton("Buy Now");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));
        inputPanel.add(new JLabel("Quantity you want to Buy in KGs :"));
        inputPanel.add(buyQuantityTextField);
        inputPanel.add(buyButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.NORTH);
        contentPane.add(inputPanel, BorderLayout.CENTER);

        loadTasks();

        buyButton.addActionListener(e -> BuyProducts());
    }

    public void loadTasks() {

        List<Product> product = db.getAllProducts();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        for (Product p : product) {
            print = "";
            if (p.getProductStock() > 0) {
                print = "Available";
            } else {
                print = "Not Available";
            }
            tableModel.addRow(new Object[]{
                    p.getProductId(), p.getProductName(), p.getProductPrice(), p.getProductStock(), print
            });

        }
    }

    public void BuyProducts() {
        int selectedRow = taskTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to Buy.");
            return;
        }

        //int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String avail = (String) tableModel.getValueAt(selectedRow, 4);
        double price = (double) tableModel.getValueAt(selectedRow, 2);
        int value = Integer.parseInt(buyQuantityTextField.getText());
        double total = value * price;

        if ("Not Available".equals(avail))
            JOptionPane.showMessageDialog(this, "Item out of stock");
        else
            JOptionPane.showMessageDialog(this, "You Bought " + name + " and the quantity you ordered is " + value + "kg and the total cost is " + total);
    }

}