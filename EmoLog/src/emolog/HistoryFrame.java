package emolog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class HistoryFrame extends JFrame {
    private JTextArea historyArea;
    private String currentUser;

    public HistoryFrame(String user) {
        this.currentUser = user;

        setTitle("EmoLog - Diary History");
        setSize(700, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 182, 193);
                Color color2 = new Color(135, 206, 250);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Diary History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(72, 61, 139));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(200, 20, 300, 30);
        bgPanel.add(titleLabel);

        historyArea = new JTextArea();
        historyArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBounds(40, 70, 600, 420);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Past Diary Entries"));
        bgPanel.add(scrollPane);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(300, 510, 100, 35);
        closeBtn.setBackground(new Color(72, 61, 139));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 16));
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> dispose());

        bgPanel.add(closeBtn);

        add(bgPanel);

        loadHistory();

        setVisible(true);
    }

    private void loadHistory() {
        String fileName = currentUser.replace("@", "_").replace(".", "_") + "_diary.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            historyArea.setText("No diary history found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            ArrayList<String> entries = new ArrayList<>();
            StringBuilder entry = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("-----------------------------------------------------")) {
                    entries.add(entry.toString());
                    entry = new StringBuilder();
                } else {
                    entry.append(line).append("\n");
                }
            }
            // Add last entry if file doesn't end with separator
            if (entry.length() > 0) {
                entries.add(entry.toString());
            }

            // Show entries in reverse order (latest first)
            for (int i = entries.size() - 1; i >= 0; i--) {
                sb.append(entries.get(i)).append("\n-----------------------------------------------------\n");
            }

            historyArea.setText(sb.toString());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading diary history.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
