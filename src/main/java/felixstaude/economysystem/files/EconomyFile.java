package felixstaude.economysystem.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class EconomyFile {
    FileConfiguration cfg = getFileConfiguration();

    public void setStandart(){

        cfg.options().copyDefaults(true);

        cfg.addDefault("standart-value", 1000);
        cfg.addDefault("money-per-hour", 20);
        cfg.addDefault("daily-bonus", 1000);
        cfg.addDefault("daily-bonus-addition-per-day", 25);
        try {
            cfg.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private File getFile(){

        return new File("plugins/EconomySystem", "economy.yml");
    }
    private FileConfiguration getFileConfiguration(){

        return YamlConfiguration.loadConfiguration(getFile());
    }
    public void readData(){

    }

}
