package felixstaude.economysystem.sql.playtime;

import org.bukkit.Bukkit;
import felixstaude.economysystem.sql.economy.SQLEcoSettings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLPlayTime {

    public static boolean isUserExists(UUID uuid){
        try {
            PreparedStatement ps = SQLEcoSettings.getConnection().prepareStatement("SELECT Value FROM PlaytimeDatabase WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static void update(UUID uuid, int amount){
        int time = 0;
        if(getTime(uuid) == -1){
            time = getTime(uuid) + 1;
        } else {
            time = getTime(uuid);
        }
        int newValue = 0;
        if(isUserExists(uuid)){
            try {
                PreparedStatement ps = SQLPlaySettings.getConnection().prepareStatement("UPDATE PlaytimeDatabase SET Playtime = ? WHERE UUID = ?");
                ps.setInt(1, time);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = SQLPlaySettings.getConnection().prepareStatement("INSERT INTO PlaytimeDatabase (UUID, Playtime) VALUES (?,?)");
                ps.setString(1, uuid.toString());
                ps.setInt(2, amount);
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static void delete(UUID uuid){
        if(isUserExists(uuid)){
            try {
                PreparedStatement ps = SQLPlaySettings.getConnection().prepareStatement("DELETE * FROM PlaytimeDatabase WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            Bukkit.getLogger().warning("the player with the uuid " + uuid.toString() + " is not in the database");
        }
    }
    public static Integer getTime(UUID uuid){
        try {
            PreparedStatement ps = SQLPlaySettings.getConnection().prepareStatement("SELECT Playtime FROM PlaytimeDatabase WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt("value");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
