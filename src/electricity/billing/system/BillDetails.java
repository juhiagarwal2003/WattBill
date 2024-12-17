package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

    BillDetails(String meter) {

        // Window Setup
        setTitle("Bill Details");
        setSize(800, 600);
        setLocation(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background Color
        getContentPane().setBackground(new Color(248, 248, 248)); // Light grey background

        // Title Label
        JLabel titleLabel = new JLabel("Bill Details for Meter No: " + meter, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(39, 76, 119)); // Dark Blue color
        titleLabel.setBounds(200, 20, 400, 30);
        add(titleLabel);

        // Table Setup
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(33, 150, 243)); // Highlighted row color

        // Table Data
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM bill WHERE meter_no = '" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs)); // Populating the table with data from the database
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Scroll Pane for the table
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 70, 740, 400);
        add(sp);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        exitButton.setBackground(new Color(233, 46, 66)); // Red color for exit
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(350, 500, 100, 40);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(e -> {
            dispose(); // Close the current window
        });
        add(exitButton);

        // Frame Visibility
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails("101");
    }
}
