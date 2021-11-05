// This class is created by LookIP
package de.proxycord.cwbw.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        final Player player = event.getPlayer();
        if(!player.hasPlayedBefore()){

        } else {

        }
    }

    private void set(final Player player){

    }
}
