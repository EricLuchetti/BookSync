package backEnd;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private String sex;
    private boolean admin;
    private String login;

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getLogin() {
        return login;
    }
    
    // Set values from DB row
    public void setUserFromDBRow(ResultSet dbRow) throws SQLException {
        this.id = dbRow.getInt("id");
        this.name = dbRow.getString("name");
        this.sex = dbRow.getString("sex");
        this.admin = dbRow.getBoolean("admin");
        this.login = dbRow.getString("login");
    }

     // Override toString() method
     @Override
     public String toString() {
         return "User{" +
                 "id=" + id +
                 ", name='" + name + '\'' +
                 ", sex='" + sex + '\'' +
                 ", admin=" + admin +
                 ", login='" + login + '\'' +
                 '}';
     }
}
