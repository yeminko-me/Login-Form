package loginApp;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginGUI {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/loginapp_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "C@s38vxuaj";

    private JLabel messejiLabel;
    private JTextField yuuzaField;
    private JPasswordField passField;
    private JButton loginButton;

    private int kaisu = 0;

    public LoginGUI() {
        JFrame frame = new JFrame("Login App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        Font font = new Font("Monospaced", Font.PLAIN, 14);

        JLabel yuuzaLabel = new JLabel("ユーザー:");
        yuuzaLabel.setBounds(50, 30, 120, 25);
        yuuzaLabel.setFont(font);
        frame.add(yuuzaLabel);

        yuuzaField = new JTextField();
        yuuzaField.setBounds(160, 30, 180, 25);
        yuuzaField.setFont(font);
        frame.add(yuuzaField);

        JLabel passLabel = new JLabel("パスワード:");
        passLabel.setBounds(50, 70, 120, 25);
        passLabel.setFont(font);
        frame.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(160, 70, 180, 25);
        passField.setFont(font);
        frame.add(passField);

        loginButton = new JButton("ログイン");
        loginButton.setBounds(150, 115, 100, 30);
        loginButton.setFont(font);
        frame.add(loginButton);

        messejiLabel = new JLabel("");
        messejiLabel.setBounds(50, 160, 300, 30);
        messejiLabel.setFont(font);
        frame.add(messejiLabel);

        loginButton.addActionListener(e -> loginButtonClick());

        frame.setVisible(true);
    }

    private void loginButtonClick() {
        kaisu++;

        String yuuzaName = yuuzaField.getText();
        String password = new String(passField.getPassword());

        boolean seikou = checkLogin(yuuzaName, password);

        if (seikou) {
            messejiLabel.setText("ログインできました！");
            loginButton.setEnabled(false);
        } else {
            if (kaisu < 2) {
                messejiLabel.setText("もう一度ログインしてください！");
            } else {
                messejiLabel.setText("パスワード忘れ");
                loginButton.setEnabled(false);
            }
        }
    }

    private boolean checkLogin(String yuuzaName, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE yuuzaName = ? AND password = ?";

        try (Connection dbSetuzoku = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = dbSetuzoku.prepareStatement(sql)) {

            pstmt.setString(1, yuuzaName);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            messejiLabel.setText("DB Error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}
