package backEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    //Dentro dessa classe existem as credenciais do DB e a função de Conexão com o mesmo

    private static final String URL = "jdbc:mysql://localhost:3306/booksync";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "booksync123";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }
}