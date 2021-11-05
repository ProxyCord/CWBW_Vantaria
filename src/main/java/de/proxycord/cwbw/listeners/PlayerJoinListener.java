// This class is created by LookIP
package de.proxycord.cwbw.listeners;

import de.proxycord.cwbw.CWBW;
import de.proxycord.cwbw.handlers.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        final Player player = event.getPlayer();
        if(!player.hasPlayedBefore()){
            event.setJoinMessage(null);
            set(player);
        } else {
            event.setJoinMessage(null);
            set(player);
        }
    }

    private void set(final Player player){
        Bukkit.getScheduler().scheduleSyncDelayedTask(CWBW.getInstance(), new Runnable() {
            @Override
            public void run() {
                CWBW.getInstance().getTeamHandler().setPlayerTeam(player, TeamHandler.Team.BLUE);
            }
        }, 5);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',CWBW.getInstance()
                .getDataHandler().getLoadedMessages().get("message.prefix") + CWBW.getInstance()
                .getDataHandler().getLoadedMessages().get("message.join").replace("%player%", player.getName())));
    }
}
