package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {

    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

    GenerateBill(String meter) {
        this.meter = meter;

        // Window settings
        setSize(500, 800);
        setLocation(550, 30);
        setTitle("Generate Bill - Reliance Power Limited");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 240, 240)); // Light background color

        // Top panel for the heading and meter number
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 10, 10));
        topPanel.setBackground(new Color(50, 150, 250)); // Blue background

        JLabel heading = new JLabel("Generate Bill", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);
        topPanel.add(heading);

        JLabel meternumber = new JLabel("Meter Number: " + meter, JLabel.CENTER);
        meternumber.setFont(new Font("Arial", Font.PLAIN, 16));
        meternumber.setForeground(Color.WHITE);
        topPanel.add(meternumber);

        // Add the top panel to the frame
        add(topPanel, BorderLayout.NORTH);

        // Panel for month selection
        JPanel monthPanel = new JPanel();
        monthPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        monthPanel.setBackground(new Color(240, 240, 240)); // Light background

        JLabel lblMonth = new JLabel("Select Month:");
        lblMonth.setFont(new Font("Arial", Font.PLAIN, 14));
        monthPanel.add(lblMonth);

        cmonth = new Choice();
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        monthPanel.add(cmonth);

        add(monthPanel, BorderLayout.CENTER);

        // Text area for displaying the bill
        area = new JTextArea(20, 40);
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");
        area.setFont(new Font("Serif", Font.ITALIC, 16));
        area.setBackground(new Color(255, 255, 255));
        area.setEditable(false);

        JScrollPane pane = new JScrollPane(area);
        add(pane, BorderLayout.CENTER);

        // Bottom panel for the generate bill button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setBackground(new Color(240, 240, 240)); // Light background

        bill = new JButton("Generate Bill");
        bill.setFont(new Font("Arial", Font.BOLD, 16));
        bill.setBackground(new Color(50, 150, 250)); // Blue background
        bill.setForeground(Color.WHITE);
        bill.setFocusPainted(false);
        bill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bill.addActionListener(this);
        bottomPanel.add(bill);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String month = cmonth.getSelectedItem();

            area.setText("\tReliance Power Limited\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF " + month + ", 2022\n\n\n");

            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '" + meter + "'");

            if (rs.next()) {
                area.append("\n    Customer Name: " + rs.getString("name"));
                area.append("\n    Meter Number   : " + rs.getString("meter_no"));
                area.append("\n    Address        : " + rs.getString("address"));
                area.append("\n    City           : " + rs.getString("city"));
                area.append("\n    State          : " + rs.getString("state"));
                area.append("\n    Email          : " + rs.getString("email"));
                area.append("\n    Phone          : " + rs.getString("phone"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            rs = c.s.executeQuery("select * from meter_info where meter_no = '" + meter + "'");

            if (rs.next()) {
                area.append("\n    Meter Location: " + rs.getString("meter_location"));
                area.append("\n    Meter Type:    " + rs.getString("meter_type"));
                area.append("\n    Phase Code:    " + rs.getString("phase_code"));
                area.append("\n    Bill Type:     " + rs.getString("bill_type"));
                area.append("\n    Days:          " + rs.getString("days"));
                area.append("\n---------------------------------------------------");
                area.append("\n");
            }

            rs = c.s.executeQuery("select * from tax");

            if (rs.next()) {
                area.append("\n    Cost Per Unit: " + rs.getString("cost_per_unit"));
                area.append("\n    Meter Rent:    " + rs.getString("cost_per_unit"));
                area.append("\n    Service Charge: " + rs.getString("service_charge"));
                area.append("\n    Service Tax:    " + rs.getString("service_charge"));
                area.append("\n    Swacch Bharat Cess: " + rs.getString("swacch_bharat_cess"));
                area.append("\n    Fixed Tax:     " + rs.getString("fixed_tax"));
                area.append("\n");
            }

            rs = c.s.executeQuery("select * from bill where meter_no = '" + meter + "' and month='" + month + "'");

            if (rs.next()) {
                area.append("\n    Current Month: " + rs.getString("month"));
                area.append("\n    Units Consumed: " + rs.getString("units"));
                area.append("\n    Total Charges: " + rs.getString("totalbill"));
                area.append("\n-------------------------------------------------------");
                area.append("\n    Total Payable: " + rs.getString("totalbill"));
                area.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
