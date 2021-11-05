package de.proxycord.cwbw;

import de.proxycord.cwbw.handlers.Config;
import de.proxycord.cwbw.handlers.DataHandler;
import de.proxycord.cwbw.handlers.LocationManager;
import de.proxycord.cwbw.setup.SetupHandler;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("all")
@Getter
public final class CWBW extends JavaPlugin {

    LocationManager locationManager;
    DataHandler dataHandler;
    SetupHandler setupHandler;
    private static CWBW instance;

    @Override
    public void onLoad() {
        instance = this;
        locationManager = new LocationManager();
        dataHandler = new DataHandler();
        setupHandler = new SetupHandler();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CWBW getInstance() {
        return instance;
    }
}
