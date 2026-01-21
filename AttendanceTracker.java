// AttendanceTracker.java
// Cute Attendance Tracker with Time In and E-Signature shown at startup and reset on every Submit.
// Author: Alonzo, Trisha Mae 
// BSIT-GD 1
import java.awt.*;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.swing.*;

public class AttendanceTracker {
    public static void main(String[] args) {
        JFrame frame = new JFrame("🌸 Attendance Tracker 🌸");
        frame.setSize(650, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 240, 245)); // pastel pink
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("✨ Welcome to Attendance Tracker ✨", JLabel.CENTER);
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        header.setForeground(new Color(199, 21, 133));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(header, gbc);
        gbc.gridwidth = 1;

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 14);
        Font fieldFont = new Font("Arial Rounded MT Bold", Font.PLAIN, 13);

        // Name
        JLabel nameLabel = new JLabel("👩 Name:");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField();
        nameField.setFont(fieldFont);

        // Course/Year
        JLabel courseLabel = new JLabel("📚 Course/Year:");
        courseLabel.setFont(labelFont);
        JTextField courseField = new JTextField();
        courseField.setFont(fieldFont);

        // Time In (locked, initialized at startup)
        JLabel timeInLabel = new JLabel("⏰ Time In:");
        timeInLabel.setFont(labelFont);
        JTextField timeInField = new JTextField(LocalDateTime.now().toString());
        timeInField.setEditable(false);
        timeInField.setBackground(new Color(245, 245, 245));
        timeInField.setFont(fieldFont);

        // E-Signature (locked, initialized at startup)
        JLabel signatureLabel = new JLabel("🖊️ E-Signature:");
        signatureLabel.setFont(labelFont);
        JTextField signatureField = new JTextField(UUID.randomUUID().toString());
        signatureField.setEditable(false);
        signatureField.setBackground(new Color(245, 245, 245));
        signatureField.setFont(fieldFont);

        // Attendance Log (list of all submissions)
        JLabel logLabel = new JLabel("📝 Attendance Log:");
        logLabel.setFont(labelFont);
        DefaultListModel<String> logModel = new DefaultListModel<>();
        JList<String> logList = new JList<>(logModel);
        logList.setFont(new Font("Monospaced", Font.PLAIN, 13)); // neat alignment
        JScrollPane logScroll = new JScrollPane(logList);
        logScroll.setMinimumSize(new Dimension(600, 250)); // bigger starting box

        // Submit button
        JButton submitButton = new JButton("💖 Submit 💖");
        submitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        submitButton.setBackground(new Color(255, 182, 193));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(e -> {
            // Refresh Time In and E-Signature on every submit
            String newTimeIn = LocalDateTime.now().toString();
            String newSignature = UUID.randomUUID().toString();
            timeInField.setText(newTimeIn);
            signatureField.setText(newSignature);

            String record = String.format("🌸 %-15s | %-10s | %s | %s",
                    nameField.getText(), courseField.getText(), newTimeIn, newSignature);
            logModel.addElement(record);

            JOptionPane.showMessageDialog(frame,
                    newSignature + "🌸 Attendance Recorded 🌸\n\n"+
                            "Name: " + nameField.getText() + "\n" +
                                    "Course/Year: " + courseField.getText() + "\n" +
                                            "Time In: " + newTimeIn + "\n" +
                                                    "E-Signature: ",
                "✨ Confirmation ✨",
                JOptionPane.INFORMATION_MESSAGE
            );

            // Reset input fields for next entry
            nameField.setText("");
            courseField.setText("");
        });

        // Layout
        gbc.gridx = 0; gbc.gridy = 1; panel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(courseLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(courseField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(timeInLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3; panel.add(timeInField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(signatureLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4; panel.add(signatureField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        panel.add(logLabel, gbc);

        // FIXED LOG LAYOUT
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;   // expand horizontally & vertically
        gbc.weightx = 1.0;                    // take full width
        gbc.weighty = 1.0;                    // take vertical space
        panel.add(logScroll, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }
}
