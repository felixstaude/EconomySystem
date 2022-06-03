package felixstaude.economysystem.main;

import felixstaude.economysystem.sql.economy.SQLEcoMoney;

import java.util.UUID;

public class Economy {
    private static EconomySystem economySystem;

    public static Integer getEconomy(UUID player){

        return SQLEcoMoney.getEconomy(player);
    }

    public static void setEconomy(UUID player, int amount){

        SQLEcoMoney.update(player, amount, false);
    }

    public static void addEconomy(UUID player, int amount){

        SQLEcoMoney.update(player, amount, false);
    }

    public static void removeEconomy(UUID player, int amount){

        SQLEcoMoney.update(player, amount, true);
    }

}
