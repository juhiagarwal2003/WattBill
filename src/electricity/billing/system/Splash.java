package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Splash extends JFrame {

    // A static flag to ensure the login screen is only displayed once
    private static boolean loginOpened = false;

    // Constructor
    Splash() {
        // Set the frame properties for the splash screen
        setUndecorated(true);  // Removes window decorations (title bar, borders)
        setLayout(new BorderLayout());

        // Create a panel to hold both image and heading
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);
        add(panel);

        // Load and scale the splash image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/mainn2.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(580, 370, Image.SCALE_SMOOTH); // Using SCALE_SMOOTH for better quality
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        
        // Create a label with the image
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(0, 50, 588, 370); // Position the image
        panel.add(imageLabel);

        // Add heading "WattBill" at the center
        JLabel headingLabel = new JLabel("WattBill", JLabel.CENTER);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        headingLabel.setBounds(0, 0, 600, 50); // Set the width to the same as the frame and position it at the top
        headingLabel.setForeground(new Color(39, 76, 119)); // Set color for the heading
        panel.add(headingLabel);

        // Set the splash screen size and location dynamically based on screen size
        setSize(600, 400);
        setLocationRelativeTo(null);  // Center the window on the screen
        setVisible(true);

        // Fade-in effect for the heading and image
        fadeIn(headingLabel, imageLabel);

        // Run splash screen for 3 seconds in a separate thread to avoid freezing UI
        new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fade-out effect before closing
                fadeOut(headingLabel, imageLabel);

                // After the fade-out completes, dispose splash screen and show the login screen
                new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();  // Close splash screen

                        // Open login only if it's not already opened
                        if (!loginOpened) {
                            new Login();  // Open login screen after splash
                            loginOpened = true; // Mark login as opened
                        }
                    }
                }).start();
            }
        }).start();
    }

    // Method to handle fade-in effect
    private void fadeIn(JLabel headingLabel, JLabel imageLabel) {
        Timer timer = new Timer(20, new ActionListener() {
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i < 255) {
                    headingLabel.setForeground(new Color(39, 76, 119, i));
                    imageLabel.setIcon(new ImageIcon(((ImageIcon) imageLabel.getIcon()).getImage().getScaledInstance(588, 370, Image.SCALE_SMOOTH)));
                    i += 5;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    // Method to handle fade-out effect
    private void fadeOut(JLabel headingLabel, JLabel imageLabel) {
        Timer timer = new Timer(20, new ActionListener() {
            int i = 255;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (i > 0) {
                    headingLabel.setForeground(new Color(39, 76, 119, i));
                    i -= 5;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new Splash();
    }
}
