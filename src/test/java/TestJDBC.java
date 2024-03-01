import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/c2202l_javaee_ebook?useSSL=false";
        String username = "root";
        String password = "g9v$;6~CmQRCq#U";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Oops, there's an error");
            e.printStackTrace();
        }
    }
}
