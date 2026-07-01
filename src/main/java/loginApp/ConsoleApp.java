package loginApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleApp {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/loginapp_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "C@s38vxuaj";

    public static void main(String[] args) {
        Scanner nyuuryoku = new Scanner(System.in);

        int kaisu = 0;
        boolean seikou = false;

        while (kaisu < 2 && !seikou) {
            kaisu++;
            System.out.println("=== Login (attempt " + kaisu + "/2) ===");

            System.out.print("ユーザー: ");
            String yuuzaName = nyuuryoku.nextLine();

            System.out.print("パスワード: ");
            String password = nyuuryoku.nextLine();

            seikou = checkLogin(yuuzaName, password);

            if (seikou) {
                System.out.println("ログインできました！");
            } else {
                if (kaisu < 2) {
                    System.out.println("もう一度ログインしてください！");
                } else {
                    System.out.println("========================================");
                    System.out.println("Forgot password");
                    System.out.println("Please reset your password at:");
                    System.out.println("https://example.com/reset-password");
                    System.out.println("========================================");
                }
            }
        }

        nyuuryoku.close();
    }

    private static boolean checkLogin(String yuuzaName, String password) {
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
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}
