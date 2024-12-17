package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PayBill extends JFrame implements ActionListener {

    Choice cmonth;
    JButton pay, back;
    String meter;

    PayBill(String meter) {
        this.meter = meter;

        setLayout(null);
        setBounds(300, 150, 900, 600);

        // Set Background Color and Title
        getContentPane().setBackground(Color.decode("#f4f4f9"));
        setTitle("Electricity Bill Payment");

        // Heading Label
        JLabel heading = new JLabel("Electricity Bill Payment");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(120, 20, 400, 30);
        heading.setForeground(Color.decode("#333333"));
        add(heading);

        // Meter Number Label
        JLabel lblmeternumber = new JLabel("Meter Number:");
        lblmeternumber.setBounds(35, 80, 200, 20);
        lblmeternumber.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblmeternumber);

        // Display Meter Number
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(300, 80, 200, 20);
        meternumber.setFont(new Font("Arial", Font.PLAIN, 16));
        add(meternumber);

        // Customer Name Label
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(35, 140, 200, 20);
        lblname.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblname);

        // Display Customer Name
        JLabel labelname = new JLabel("");
        labelname.setBounds(300, 140, 200, 20);
        labelname.setFont(new Font("Arial", Font.PLAIN, 16));
        add(labelname);

        // Month Label and Choice for Month
        JLabel lblmonth = new JLabel("Month:");
        lblmonth.setBounds(35, 200, 200, 20);
        lblmonth.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblmonth);

        cmonth = new Choice();
        cmonth.setBounds(300, 200, 200, 25);
        cmonth.setFont(new Font("Arial", Font.PLAIN, 16));
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
        add(cmonth);

        // Units Consumed Label
        JLabel lblunits = new JLabel("Units Consumed:");
        lblunits.setBounds(35, 260, 200, 20);
        lblunits.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblunits);

        JLabel labelunits = new JLabel("");
        labelunits.setBounds(300, 260, 200, 20);
        labelunits.setFont(new Font("Arial", Font.PLAIN, 16));
        add(labelunits);

        // Total Bill Label
        JLabel lbltotalbill = new JLabel("Total Bill:");
        lbltotalbill.setBounds(35, 320, 200, 20);
        lbltotalbill.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lbltotalbill);

        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(300, 320, 200, 20);
        labeltotalbill.setFont(new Font("Arial", Font.PLAIN, 16));
        add(labeltotalbill);

        // Status Label
        JLabel lblstatus = new JLabel("Status:");
        lblstatus.setBounds(35, 380, 200, 20);
        lblstatus.setFont(new Font("Arial", Font.PLAIN, 16));
        add(lblstatus);

        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(300, 380, 200, 20);
        labelstatus.setFont(new Font("Arial", Font.PLAIN, 16));
        labelstatus.setForeground(Color.RED);
        add(labelstatus);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE meter_no = '" + meter + "'");
            while (rs.next()) {
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }

            rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_no = '" + meter + "' AND month = 'January'");
            while (rs.next()) {
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmonth.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_no = '" + meter + "' AND month = '" + cmonth.getSelectedItem() + "'");
                    while (rs.next()) {
                        labelunits.setText(rs.getString("units"));
                        labeltotalbill.setText(rs.getString("totalbill"));
                        labelstatus.setText(rs.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Pay Button
        pay = new JButton("Pay");
        pay.setBackground(Color.decode("#4CAF50"));
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 150, 30);
        pay.setFont(new Font("Arial", Font.BOLD, 16));
        pay.addActionListener(this);
        add(pay);

        // Back Button
        back = new JButton("Back");
        back.setBackground(Color.decode("#FF6347"));
        back.setForeground(Color.WHITE);
        back.setBounds(270, 460, 150, 30);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        // Add Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 120, 600, 300);
        add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("UPDATE bill SET status = 'Paid' WHERE meter_no = '" + meter + "' AND month='" + cmonth.getSelectedItem() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);  // This can be the Paytm integration class for further payment options
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
