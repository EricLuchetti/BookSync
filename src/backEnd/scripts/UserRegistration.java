package backEnd.scripts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistration {

    public static void addUser(String name, String sex, boolean admin, String login, String password) {
        try (Connection connection = backEnd.scripts.DatabaseConnector.connect()) {
            String insertUserQuery = "INSERT INTO users (name, sex, admin, login, password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, sex);
                preparedStatement.setBoolean(3, admin);
                preparedStatement.setString(4, login);
                preparedStatement.setString(5, password);
                preparedStatement.executeUpdate();
                System.out.println("User added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String name = "John Doe";
        String sex = "M";
        boolean admin = false;
        String login = "johndoe";
        String password = "password123";

        addUser(name, sex, admin, login, password);
    }
}
