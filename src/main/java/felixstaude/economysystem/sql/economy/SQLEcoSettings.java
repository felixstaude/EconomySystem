package felixstaude.economysystem.sql.economy;

import felixstaude.economysystem.sql.SQLFile;
import org.bukkit.Bukkit;
import theepicflexo.economysystem.main.EconomySystem;

import java.sql.*;

public class SQLEcoSettings {

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
                PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `EconomySystem`." +
                        "`MoneyDatabase` ( `UUID` VARCHAR(100), " +
                        "`Value` INT(100)) ENGINE = InnoDB; ");
                ps.executeUpdate();
                Bukkit.getLogger().info("SQL connected");
            } catch (SQLException throwables) {
                Bukkit.getLogger().warning("can't connect SQL");
                throwables.printStackTrace();
                Bukkit.getServer().getPluginManager().disablePlugin(EconomySystem.getPlugin(EconomySystem.class));
            }
        }
    }
    public static void disconnect(){
        if(isConnected()) {
            try {
                con.close();
                Bukkit.getLogger().info("SQL disconnected");
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
