package felixstaude.economysystem.sql.playtime;

import felixstaude.economysystem.main.EconomySystem;
import felixstaude.economysystem.sql.SQLFile;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLPlaySettings {

    public static String host;
    public static String port;
    public static String database;
    public static String username;
    public static String password;
    public static Connection con;

    public static void connect(){
        if(!isConnected()){
            SQLFile file = new SQLFile();
            file.setStandard();
            file.readData();
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `EconomySystem`.`PlaytimeDatabase`" +
                        " ( `UUID` VARCHAR(100), `Playtime` INT(100)) ENGINE = InnoDB; ");
                ps.executeUpdate();
                Bukkit.getLogger().info("Playtime SQL connected");
            } catch (SQLException throwables) {
                Bukkit.getLogger().warning("can't connect Playtime SQL");
                throwables.printStackTrace();
                Bukkit.getServer().getPluginManager().disablePlugin(EconomySystem.getPlugin(EconomySystem.class));
            }
        }
    }

    public static void disconnect(){
        if(isConnected()) {
            try {
                con.close();
                Bukkit.getLogger().info("Playtime SQL disconnected");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static boolean isConnected(){
        return (con == null ? false : true);
    }

    public static Connection getConnection(){
        return con;
    }
}
