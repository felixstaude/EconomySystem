package felixstaude.economysystem.sql.economy;

import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLEcoMoney {

    public static boolean isUserExists(UUID uuid){
        try {
            PreparedStatement ps = SQLEcoSettings.getConnection().prepareStatement("SELECT Value FROM MoneyDatabase WHERE UUID = ?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static void update(UUID uuid, int amount, boolean remove){
        int money = 0;
        if(getEconomy(uuid) == -1){
            money = getEconomy(uuid) + 1;
        } else {
            money = getEconomy(uuid);
        }
        int newValue = 0;
        if(remove){
            amount -= money;
        } else {
            amount += money;
        }
        if(isUserExists(uuid)){
            try {
                PreparedStatement ps = SQLEcoSettings.getConnection().prepareStatement("UPDATE MoneyDatabase SET Value = ? WHERE UUID = ?");
                ps.setInt(1, amount);
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = SQLEcoSettings.getConnection().prepareStatement("INSERT INTO MoneyDatabase (UUID, Value) VALUES (?,?)");
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
                PreparedStatement ps = SQLEcoSettings.getConnection().prepareStatement("DELETE * FROM MoneyDatabase WHERE UUID = ?");
                ps.setString(1, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else {
            Bukkit.getLogger().warning("the player with the uuid " + uuid.toString() + " is not in the database");
        }
    }
    public static Integer getEconomy(UUID uuid){
        try {
            PreparedStatement ps = SQLEcoSettings.getConnection().prepareStatement("SELECT Value FROM MoneyDatabase WHERE UUID = ?");
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
