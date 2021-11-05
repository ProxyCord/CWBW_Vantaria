package de.proxycord.cwbw;

import de.proxycord.cwbw.handlers.Config;
import de.proxycord.cwbw.handlers.DataHandler;
import de.proxycord.cwbw.handlers.LocationManager;
import de.proxycord.cwbw.handlers.TeamHandler;
import de.proxycord.cwbw.listeners.PlayerJoinListener;
import de.proxycord.cwbw.setup.AsyncPlayerChatListener;
import de.proxycord.cwbw.setup.SetupHandler;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("all")
@Getter
public final class CWBW extends JavaPlugin {

    LocationManager locationManager;
    DataHandler dataHandler;
    SetupHandler setupHandler;
    TeamHandler teamHandler;
    private static CWBW instance;

    @Override
    public void onLoad() {
        instance = this;
        locationManager = new LocationManager();
        dataHandler = new DataHandler();
        setupHandler = new SetupHandler();
        teamHandler = new TeamHandler();
        dataHandler.setMessages();
    }

    @Override
    public void onEnable() {
        loadListeners();
        teamHandler.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CWBW getInstance() {
        return instance;
    }

    private void loadListeners(){
        final PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new AsyncPlayerChatListener(), this);
    }
}
