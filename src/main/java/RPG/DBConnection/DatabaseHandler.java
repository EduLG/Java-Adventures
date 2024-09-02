package RPG.DBConnection;

import RPG.Resources.Enemy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.net.URISyntaxException;

public class DatabaseHandler {



    int idTurn = 1;
    private static Connection conn = null;



    //Get Absolute route in any case
    public String findRoute() {

        try {

            File archivoActual = new File(DatabaseHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String dbr = archivoActual.getParent();
            String URI1 = dbr.substring(0, dbr.length() - 6);
            String dbRoute = (URI1 + "\\src\\main\\java\\org\\DB\\Role.db");
            return dbRoute;
        }
        catch(URISyntaxException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection()throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + findRoute());
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

    public Enemy getEnemy() {
        Enemy enemy = null;

        try (Connection connection = getConnection()){
            Statement stmt = connection.createStatement();

            String query = "SELECT * FROM enemy WHERE id = 1";
            ResultSet resultSet = stmt.executeQuery(query);
            idTurn =idTurn +1 ;

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int health = resultSet.getInt("health");
                int attack = resultSet.getInt("attack");

                enemy = new Enemy(id, name, health, attack);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return enemy;
    }
}

