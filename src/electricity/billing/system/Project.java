package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    String atype, meter;

    Project(String atype, String meter) {
        this.atype = atype;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/wattbill.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1400, 650, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(200, 0, 1200, 650);
        add(image);

        // Sidebar Panel
        JPanel sidebar = new JPanel();
        sidebar.setBounds(0, 0, 200, 700);
        sidebar.setBackground(new Color(39, 76, 119)); // Dark Blue
        sidebar.setLayout(new GridLayout(10, 1, 10, 10));
        add(sidebar);

        // Sidebar Buttons
        if (atype.equals("Admin")) {
            addSidebarButton(sidebar, "New Customer");
            addSidebarButton(sidebar, "Customer Details");
            addSidebarButton(sidebar, "Deposit Details");
            addSidebarButton(sidebar, "Calculate Bill");
        } else {
            addSidebarButton(sidebar, "View Information");
            addSidebarButton(sidebar, "Update Information");
            addSidebarButton(sidebar, "Bill Details");
            addSidebarButton(sidebar, "Pay Bill");
            addSidebarButton(sidebar, "Generate Bill");
        }

        addSidebarButton(sidebar, "Notepad");
        addSidebarButton(sidebar, "Calculator");
        addSidebarButton(sidebar, "Exit");

        setLayout(null);
        setVisible(true);
    }

    
    // Method to add buttons with hover effect
    private void addSidebarButton(JPanel sidebar, String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        
        button.setBackground(new Color(39, 76, 119)); // Deep Blue
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Set Rounded Corners
        button.setBorder(BorderFactory.createLineBorder(new Color(39, 76, 119), 2, true));

        // Hover Effect
        // Hover Effect with smooth transition (without altering button text)
button.addMouseListener(new MouseAdapter() {
    public void mouseEntered(MouseEvent e) {
        // Lighter color on hover
        button.setBackground(new Color(49, 92, 139)); // Lighter Blue on hover
    }
    public void mouseExited(MouseEvent e) {
        // Reset to original color on exit
        button.setBackground(new Color(39, 76, 119)); // Dark Blue reset
    }
    public void mousePressed(MouseEvent e) {
        // Darker color when clicked
        button.setBackground(new Color(19, 55, 88)); // Darker blue when pressed
    }
    public void mouseReleased(MouseEvent e) {
        // Return to normal state after release
        button.setBackground(new Color(39, 76, 119)); // Dark Blue when released
    }
});

        button.addActionListener(this);
        sidebar.add(button);
    }
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (msg.equals("View Information")) {
            new ViewInformation(meter);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter);
        }
    }

    public static void main(String[] args) {
        new Project("", "101");
    }
}