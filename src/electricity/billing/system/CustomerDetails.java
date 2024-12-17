package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class CustomerDetails extends JFrame implements ActionListener{

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;
    JLabel titleLabel;

    CustomerDetails(){
        super("Customer Details");
        
        // Set JFrame size, location, and default close operation
        setSize(1200, 650);
        setLocation(200, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create title label with custom font and color
        titleLabel = new JLabel("Customer Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.DARK_GRAY);
        add(titleLabel, BorderLayout.NORTH);
        
        // Initialize JTable and set model with data
        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set table properties
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(Color.CYAN);
        
        // Add table inside a JScrollPane
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);
        
        // Panel for buttons with padding and layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        print = new JButton("Print");
        print.setFont(new Font("Arial", Font.PLAIN, 16));
        print.setBackground(new Color(0, 123, 255));
        print.setForeground(Color.WHITE);
        print.setFocusPainted(false);
        print.addActionListener(this);
        
        buttonPanel.add(print);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set visible JFrame
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}
