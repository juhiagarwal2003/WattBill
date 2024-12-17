package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener {

    Choice meternumber, cmonth;
    JTable table;
    JButton search, print;

    DepositDetails() {
        super("Deposit Details");

        // Window settings
        setSize(800, 700);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 230, 255)); // Light background color
        
        // Top panel for search options
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        topPanel.setBackground(new Color(200, 200, 255)); // Soft background color

        JLabel lblmeternumber = new JLabel("Search By Meter Number:");
        lblmeternumber.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(lblmeternumber);

        meternumber = new Choice();
        meternumber.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(meternumber);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                meternumber.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblmonth = new JLabel("Search By Month:");
        lblmonth.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(lblmonth);

        cmonth = new Choice();
        cmonth.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(cmonth);

        // Add months to the month choice dropdown
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        for (String month : months) {
            cmonth.add(month);
        }

        // Buttons for search and print
        search = new JButton("Search");
        search.setFont(new Font("Arial", Font.BOLD, 14));
        search.setBackground(new Color(50, 150, 250)); // Blue background
        search.setForeground(Color.WHITE);
        search.setFocusPainted(false);
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search.addActionListener(this);
        topPanel.add(search);

        print = new JButton("Print");
        print.setFont(new Font("Arial", Font.BOLD, 14));
        print.setBackground(new Color(50, 150, 250)); // Blue background
        print.setForeground(Color.WHITE);
        print.setFocusPainted(false);
        print.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        print.addActionListener(this);
        topPanel.add(print);

        // Add top panel to the frame
        add(topPanel, BorderLayout.NORTH);

        // Table to display bill details
        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Customize the table
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(sp, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from bill where meter_no = '" + meternumber.getSelectedItem() + "' and month = '" + cmonth.getSelectedItem() + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DepositDetails();
    }
}
