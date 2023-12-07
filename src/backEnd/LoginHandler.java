package backEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHandler {

    //Dentro dessa Classe temos o script de login

    public static Void login(String username, String password, User user) {
        String encryptedPassword = Services.encryptPassword(password);

        try (Connection connection = DatabaseConnector.connect()) {
            String query = "SELECT * FROM users WHERE login = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, encryptedPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user.setUserFromDBRow(resultSet);
                        if (user.isAdmin()) {
                            frontEnd.telas.AdminMenuScreen.main(null, user);
                            System.out.println(user.toString());
                        }
                        else{
                            frontEnd.telas.MainMenuScreen.main(null, user);
                        }
                    } else {
                        //TODO tela de erro de login
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
}

    
//DEBUG
    public static void main(String[] args) {
        //String result = login("john_doe", "password123");
        //System.out.println(result);
    }
}