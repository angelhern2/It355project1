import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    String username = "user";
    String password = "password holder"; // add password at the end

    try(
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root",
            "password"))
    {
        // Using a PreparedStatement to prevent SQL injection
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }catch(
    SQLException e)
    {
        e.printStackTrace();
    }
}
