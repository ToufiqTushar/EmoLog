package emolog;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class EmoLog {
    public static final Map<String, String> userCredentials = new HashMap<>();

    static {
        userCredentials.put("tushar", "1234");  // Demo login
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
