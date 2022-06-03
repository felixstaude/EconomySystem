package felixstaude.economysystem.sql;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import felixstaude.economysystem.sql.economy.SQLEcoSettings;
import felixstaude.economysystem.sql.playtime.SQLPlaySettings;

import java.io.File;
import java.io.IOException;

public class SQLFile {
    FileConfiguration cfg = getFileConfiguration();

    public void setStandard(){

        cfg.options().copyDefaults(true);

        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        cfg.addDefault("database", "EconomySystem");
        cfg.addDefault("username", "user1");
        cfg.addDefault("password", "passwd");
        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private File getFile(){
        return new File("plugins/EconomySystem", "mysql.yml");
    }
    private FileConfiguration getFileConfiguration(){
        return YamlConfiguration.loadConfiguration(getFile());
    }
    public void readData(){
        SQLEcoSettings.host = cfg.getString("host");
        SQLEcoSettings.database = cfg.getString("database");
        SQLEcoSettings.port = cfg.getString("port");
        SQLEcoSettings.username = cfg.getString("username");
        SQLEcoSettings.password = cfg.getString("password");

        SQLPlaySettings.host = cfg.getString("host");
        SQLPlaySettings.database = cfg.getString("database");
        SQLPlaySettings.port = cfg.getString("port");
        SQLPlaySettings.username = cfg.getString("username");
        SQLPlaySettings.password = cfg.getString("password");

     /*   SQLDaySettings.host = cfg.getString("host");
        SQLDaySettings.database = cfg.getString("database");
        SQLDaySettings.port = cfg.getString("port");
        SQLDaySettings.username = cfg.getString("username");
        SQLDaySettings.password = cfg.getString("password");
    */}
}
