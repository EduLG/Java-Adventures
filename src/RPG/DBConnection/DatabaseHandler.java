package RPG.DBConnection;

import RPG.Resources.Enemy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private static final String URL = "jdbc:sqlite:../DB/Role.db";
    private static Connection conn = null;

    public Connection connect() {
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Successful connection to DB");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión a SQLite cerrada.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Enemy getEnemyById(int id) {
        String sql = "SELECT id, name, initialHealth, health, attack FROM enemy WHERE id = ?";
        Enemy enemy = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int initialHealth = rs.getInt("initialHealth");
                int health = rs.getInt("health");
                int attack = rs.getInt("attack");


                enemy = new Enemy(name, initialHealth, health, attack);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return enemy;
    }


}

