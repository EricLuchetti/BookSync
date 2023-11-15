package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHandler {

    //Dentro dessa Classe temos o script de login

    public static String login(String username, String password) {
        String encryptedPassword = Services.encryptPassword(password);

        try (Connection connection = DatabaseConnector.connect()) {
            String query = "SELECT * FROM users WHERE login = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, encryptedPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return "Login successful";
                    } else {
                        return "Login failed";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Login failed due to a database error.";
        }
    }

    
//DEBUG
    public static void main(String[] args) {
        String result = login("john_doe", "password123");
        System.out.println(result);
    }
}