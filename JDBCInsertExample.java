import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCInsertExample {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), email VARCHAR(100))";
        String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement createStmt = connection.prepareStatement(createTableSQL);
             PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {

            // Step-4: Create table if not exists
            createStmt.execute();
            System.out.println("Table users created/verified.");

            // Step-5 & 6: First record
            insertStmt.setString(1, "John Doe");
            insertStmt.setString(2, "john.doe@example.com");
            insertStmt.executeUpdate();

            // Step-7: Second record
            insertStmt.setString(1, "Jane Smith");
            insertStmt.setString(2, "jane.smith@example.com");
            insertStmt.executeUpdate();

            // Step-8: Success message
            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            System.err.println("Database error!");
            e.printStackTrace();
        }
    }
}
