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
    
    /* Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setGenreId(int genre_id) {
        this.genre_id = genre_id;
    }
 */
    // Set values from DB row
    public void setUserFromDBRow(ResultSet dbRow) throws SQLException {
        this.id = dbRow.getInt("id");
        this.name = dbRow.getString("name");
        this.sex = dbRow.getString("sex");
        this.admin = dbRow.getBoolean("admin");
        this.login = dbRow.getString("login");
    }
}
