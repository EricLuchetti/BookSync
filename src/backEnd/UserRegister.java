package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegister {
    public static void registerUser(String name, String sex, String login, String password, int[] genreIds) {
        // Check if the user with the same login already exists
        if (!userExists(login)) {
            // If the user doesn't exist, proceed with registration
            String encryptedPassword = Services.encryptPassword(password);

            try (Connection connection = DatabaseConnector.connect()) {
                // Insert user into the users table
                String insertUserQuery = "INSERT INTO users (name, sex, admin, login, password) VALUES (?, ?, 0, ?, ?)";
                try (PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    insertUserStatement.setString(1, name);
                    insertUserStatement.setString(2, sex);
                    insertUserStatement.setString(3, login);
                    insertUserStatement.setString(4, encryptedPassword);

                    int affectedRows = insertUserStatement.executeUpdate();

                    if (affectedRows > 0) {
                        // User inserted successfully, get the generated user ID
                        ResultSet generatedKeys = insertUserStatement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int userId = generatedKeys.getInt(1);

                            // Insert genre_ids into the user_genres table
                            String insertGenresQuery = "INSERT INTO users_genres (user_id, genre_id) VALUES (?, ?)";
                            try (PreparedStatement insertGenresStatement = connection.prepareStatement(insertGenresQuery)) {
                                for (int genreId : genreIds) {
                                    insertGenresStatement.setInt(1, userId);
                                    insertGenresStatement.setInt(2, genreId);
                                    insertGenresStatement.executeUpdate();
                                }
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User with the same login already exists. Registration failed.");
        }
    }

    private static boolean userExists(String login) {
        try (Connection connection = DatabaseConnector.connect()) {
            String checkUserQuery = "SELECT COUNT(*) FROM users WHERE login = ?";
            try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery)) {
                checkUserStatement.setString(1, login);
                ResultSet resultSet = checkUserStatement.executeQuery();

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        // Example usage:
        String name = "John Doe";
        String sex = "Male";
        String login = "john_doe";
        String password = "securepassword";
        int[] genreIds = {1, 2};

        registerUser(name, sex, login, password, genreIds);
    }
}
