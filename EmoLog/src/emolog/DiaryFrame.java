package emolog;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DiaryFrame extends JFrame {
    private JTextArea diaryArea;
    private JLabel dateTimeLabel, emotionLabel;
    private String currentUser;

    public DiaryFrame(String user) {
        this.currentUser = user;

        setTitle("EmoLog - Daily Emotion Diary");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        dateTimeLabel = new JLabel("Date & Time: " + getDateTime());
        dateTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        dateTimeLabel.setBounds(20, 20, 400, 30);
        dateTimeLabel.setForeground(new Color(60, 60, 60));
        bgPanel.add(dateTimeLabel);

        emotionLabel = new JLabel("Emotion: 😐 Neutral");
        emotionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        emotionLabel.setBounds(450, 20, 220, 30);
        emotionLabel.setForeground(new Color(72, 61, 139));
        bgPanel.add(emotionLabel);

        diaryArea = new JTextArea();
        diaryArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        diaryArea.setLineWrap(true);
        diaryArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(diaryArea);
        scrollPane.setBounds(40, 70, 600, 350);
        scrollPane.setBorder(BorderFactory.createTitledBorder("How was your day?"));

        bgPanel.add(scrollPane);

        JButton analyzeBtn = new JButton("🧠 Analyze Emotion");
        analyzeBtn.setBounds(70, 450, 150, 40);
        analyzeBtn.setBackground(new Color(72, 61, 139));
        analyzeBtn.setForeground(Color.WHITE);
        analyzeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        analyzeBtn.setFocusPainted(false);
        analyzeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton saveBtn = new JButton("💾 Save & Next");
        saveBtn.setBounds(270, 450, 150, 40);
        saveBtn.setBackground(new Color(34, 139, 34));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Arial", Font.BOLD, 14));
        saveBtn.setFocusPainted(false);
        saveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton historyBtn = new JButton("📜 History");
        historyBtn.setBounds(470, 450, 150, 40);
        historyBtn.setBackground(new Color(105, 105, 105));
        historyBtn.setForeground(Color.WHITE);
        historyBtn.setFont(new Font("Arial", Font.BOLD, 14));
        historyBtn.setFocusPainted(false);
        historyBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        bgPanel.add(analyzeBtn);
        bgPanel.add(saveBtn);
        bgPanel.add(historyBtn);

        analyzeBtn.addActionListener(e -> {
            String text = diaryArea.getText();
            String emotion = EmotionAnalyzer.analyzeEmotion(text);
            emotionLabel.setText("Emotion: " + emotion);
        });

        saveBtn.addActionListener(e -> saveEntry());

        historyBtn.addActionListener(e -> new HistoryFrame(currentUser));

        add(bgPanel);
        setVisible(true);
    }
    private String getDateTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
    return dtf.format(LocalDateTime.now());
    }

    private void saveEntry() {
        try {
            String fileName = currentUser.replace("@", "_").replace(".", "_") + "_diary.txt";
            FileWriter writer = new FileWriter(fileName, true);
            writer.write("Date: " + getDateTime() + "\n");
            writer.write("Entry:\n" + diaryArea.getText() + "\n");
            writer.write("-----------------------------------------------------\n");
            writer.close();

            JOptionPane.showMessageDialog(this, "Diary entry saved!");
            diaryArea.setText("");
            emotionLabel.setText("Emotion: 😐 Neutral");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving diary entry.");
        }
    }
}
