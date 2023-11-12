import java.sql.*;
public class ConexaoManager {

    public static String URL = "jdbc:mysql://localhost:3306/sql";
    public static String USER = "root";
    public static String PWD = "";

    private Connection dbconn = null;
    private Statement sqlmgr = null;
    private ResultSet resultsql = null;
    
    public void OpenDatabase () {

        try {
            dbconn = DriverManager.getConnection(URL, USER , PWD);
            System.out.println("Conectado com sucesso em: " + URL);
            sqlmgr = dbconn.createStatement();
        } catch (Exception Error)
        {
            System.out.println("Error on connect: " +Error.getMessage);
        }
    }
    public void CloseDatabase() throws SQLException {
        sqlmgr.close();
        dbconn.close();
    }

    public int ExecuteQuery(String sql) {
        try {
                return sqlmgr.executeUpdate(sql);
        } catch (Exception Error)
        {
            System.out.println("ERROR" +Error.getMessage());
    }
    return -1;
    }

}