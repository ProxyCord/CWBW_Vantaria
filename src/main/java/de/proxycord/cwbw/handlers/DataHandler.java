// This class is created by LookIP
package de.proxycord.cwbw.handlers;

import de.proxycord.cwbw.CWBW;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class DataHandler {

    public enum Setup {
        SPAWN,
        GAMEMODE,
        MINPLAYERS;
    }

    public static String team_red = "Red";
    public static String team_blue = "Blue";
    public static String team_green = "Green";
    public static String team_yellow = "Yellow";
    public static String team_white = "White";
    public static String team_black = "Black";
    public static String team_pink = "Pink";
    public static String team_cyan = "Cyan";

    private final Map<String, Setup> setup = new HashMap<>();
    private final List<Player> ingamePlayers = new ArrayList<>();
    private final Integer maxPlayersPerTeam = 1;
    private final String map = "";
    private final List<TeamHandler.Team> teamsIngame = new ArrayList<>();
    private final Map<String, String> loadedMessages = new HashMap<>();

    public List<TeamHandler.Team> getTeamsIngame() {return teamsIngame;}
    public String getMap() {return map;}
    public Integer getMaxPlayersPerTeam() {return maxPlayersPerTeam;}
    public List<Player> getIngamePlayers() {return ingamePlayers;}
    public Map<String, Setup> getSetup() {
        return setup;
    }

    final String configName = "config.yml";
    public Config messages = new Config(CWBW.getInstance().getDataFolder(), configName);
    final FileConfiguration message = messages.getConfig();

    public void setMessages(){
        message.addDefault("message.join", "&7The player &a%player% &7want to play CwBw!");
        message.addDefault("message.leave", "&7The player &a%player% &7has leaved the game!");
        message.addDefault("message.prefix", "&8[&6&lCWBW&8] &7");
        message.options().copyDefaults(true);
        messages.save();
    }

    public Map<String, String> getLoadedMessages() {
        if(loadedMessages.isEmpty()) {
            for(final String key : message.getKeys(false)) {
                final ConfigurationSection section = message.getConfigurationSection(key);
                for(final String key2 : section.getKeys(false)) {
                    final String value = message.getString(key + "." + key2);
                    loadedMessages.put(key + "." + key2, value);
                }
            }
        }
        return loadedMessages;
    }
}