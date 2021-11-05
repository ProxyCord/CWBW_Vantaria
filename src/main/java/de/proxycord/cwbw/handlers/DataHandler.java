// This class is created by LookIP
package de.proxycord.cwbw.handlers;

import de.proxycord.cwbw.CWBW;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {

    public Config config_your = new Config(CWBW.getInstance().getDataFolder(), "config.yml");

    public enum Setup {
        SPAWN,
        GAMEMODE,
        MINPLAYERS;
    }

    private final Map<String, Setup> setup = new HashMap<>();
    private final List<Player> ingamePlayers = new ArrayList<>();
    private final Integer maxPlayersPerTeam = 1;
    private final String map = "";
    private final List<TeamHandler.Team> teamsIngame = new ArrayList<>();

    public List<TeamHandler.Team> getTeamsIngame() {return teamsIngame;}
    public String getMap() {return map;}
    public Integer getMaxPlayersPerTeam() {return maxPlayersPerTeam;}
    public List<Player> getIngamePlayers() {return ingamePlayers;}
    public Map<String, Setup> getSetup() {
        return setup;
    }

    final public String prefix = "§8[§6§lCWBW§8] ";
}