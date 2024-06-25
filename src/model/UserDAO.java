package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DatabaseConnection;

public class UserDAO {

    public boolean isValidUser(User user) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) {
            System.out.println("Failed to obtain database connection.");
            return false;
        }

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs  = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nim"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
