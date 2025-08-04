package emolog;

import javax.swing.*;
import java.awt.*;

class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("EmoLog - Secure Diary Login");
        setSize(420, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(135, 206, 250);
                Color color2 = new Color(72, 61, 139);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(null);

        JPanel formPanel = new JPanel();
        formPanel.setBounds(60, 100, 300, 280);
        formPanel.setBackground(new Color(255, 255, 255, 230));
        formPanel.setLayout(null);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        JLabel titleLabel = new JLabel("EmoLog Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(60, 10, 180, 30);
        titleLabel.setForeground(new Color(72, 61, 139));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 60, 80, 20);
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTextField emailField = new JTextField();
        emailField.setBounds(30, 80, 240, 30);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 120, 80, 20);
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPasswordField passField = new JPasswordField();
        passField.setBounds(30, 140, 240, 30);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(90, 200, 120, 35);
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(72, 61, 139));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        formPanel.add(titleLabel);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passLabel);
        formPanel.add(passField);
        formPanel.add(loginButton);

        bgPanel.add(formPanel);
        add(bgPanel);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String pass = new String(passField.getPassword());
            if (EmoLog.userCredentials.containsKey(email) && EmoLog.userCredentials.get(email).equals(pass)) {
                dispose();
                new DiaryFrame(email);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
