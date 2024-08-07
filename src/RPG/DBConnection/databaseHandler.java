package RPG.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseHandler {
    private static final String URL = "jdbc:sqlite:../DB/Role.db";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Successful connection to DB");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createEnemyTable() {
        String sql = "CREATE TABLE IF NOT EXISTS enemy (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL,\n"
                + " health INTEGER NOT NULL,\n"
                + " attack INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertEnemy(String name, int health, int attack) {
        String sql = "INSERT INTO enemy(name, health, attack) VALUES(?,?,?)";

    }
}

