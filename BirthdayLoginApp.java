import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BirthdayLoginApp{
    
    private JTextField nameField;
    private JButton submitButton;
    
    public BirthdayLoginApp() {
        // Frame setup
        setTitle("Birthday Wish App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel for login form
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Name label and field
        JLabel nameLabel = new JLabel("Enter Your Name:", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setPreferredSize(new Dimension(200, 30));
        
        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(255, 100, 100));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        
        // Add components to panel
        loginPanel.add(nameLabel);
        loginPanel.add(nameField);
        loginPanel.add(new JLabel()); // Empty cell
        loginPanel.add(submitButton);
        
        add(loginPanel, BorderLayout.CENTER);
        
        // Button action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        BirthdayLoginApp.this,
                        "Please enter your name!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    // Close login window
                    dispose();
                    // Show birthday wish with video
                    showBirthdayWish(name);
                }
            }
        });
        
        // Allow Enter key to submit
        nameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitButton.doClick();
            }
        });
    }
    
    private void showBirthdayWish(String name) {
        // Create birthday wish frame
        JFrame wishFrame = new JFrame("Happy Birthday!");
        wishFrame.setSize(600, 500);
        wishFrame.setLocationRelativeTo(null);
        wishFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Panel for content
        JPanel wishPanel = new JPanel();
        wishPanel.setLayout(new BorderLayout());
        wishPanel.setBackground(new Color(255, 255, 224));
        
        // Welcome message
        JLabel titleLabel = new JLabel("🎉 Happy Birthday! 🎉", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(255, 0, 100));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        JLabel nameLabel = new JLabel("Dear " + name + ",", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(new Color(0, 100, 200));
        
        JLabel messageLabel = new JLabel(
            "<html><center>" +
            "Wishing you a wonderful day filled with joy!<br>" +
            "May this year bring you success, health, and happiness!<br><br>" +
            "🎂 Happy Birthday! 🎈<br>" +
            "</center></html>",
            SwingConstants.CENTER
        );
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setForeground(new Color(50, 50, 50));
        
        // Video panel (placeholder - replace with actual video path)
        JPanel videoPanel = new JPanel();
        videoPanel.setBackground(Color.BLACK);
        videoPanel.setPreferredSize(new Dimension(400, 250));
        videoPanel.setLayout(new BorderLayout());
        
        JLabel videoLabel = new JLabel("🎬 Playing Birthday Video...", SwingConstants.CENTER);
        videoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        videoLabel.setForeground(Color.WHITE);
        videoPanel.add(videoLabel, BorderLayout.CENTER);
        
        // Instruction label
        JLabel instructionLabel = new JLabel(
            "<html><center>Video would play here.<br>" +
            "<small>Replace 'birthday_video.mp4' with your actual video file</small></center></html>",
            SwingConstants.CENTER
        );
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        instructionLabel.setForeground(Color.GRAY);
        instructionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        
        // Add all components
        wishPanel.add(titleLabel, BorderLayout.NORTH);
        wishPanel.add(nameLabel, BorderLayout.CENTER);
        wishPanel.add(messageLabel, BorderLayout.SOUTH);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 224));
        mainPanel.add(wishPanel, BorderLayout.NORTH);
        mainPanel.add(videoPanel, BorderLayout.CENTER);
        mainPanel.add(instructionLabel, BorderLayout.SOUTH);
        
        wishFrame.add(mainPanel);
        wishFrame.setVisible(true);
        
        // Play video (if file exists)
        playVideo("birthday_video.mp4");
    }
    
    private void playVideo(String videoPath) {
        try {
            // Try to play video using desktop API
            File videoFile = new File(videoPath);
            if (videoFile.exists()) {
                Desktop.getDesktop().open(videoFile);
            } else {
                System.out.println("Video file not found: " + videoPath);
                System.out.println("Please place 'birthday_video.mp4' in the project directory");
            }
        } catch (Exception e) {
            System.out.println("Could not play video: " + e.getMessage());
            System.out.println("Video playback requires a media player installed on your system");
        }
    }
    
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BirthdayLoginApp().setVisible(true);
            }
        });
    }
}