package backEnd.scripts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDeletion {

    public static void deleteUserById(int userId) {
        try (Connection connection = DatabaseConnector.connect()) {
            String deleteUserQuery = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUserQuery)) {
                preparedStatement.setInt(1, userId);
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User with ID " + userId + " has been deleted successfully.");
                } else {
                    System.out.println("No user found with ID " + userId + ".");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int userIdToDelete = 1; // Replace with the actual user ID you want to delete
        deleteUserById(userIdToDelete);
    }
}
