// This class is created by LookIP
package de.proxycord.cwbw.setup;

import de.proxycord.cwbw.CWBW;
import de.proxycord.cwbw.handlers.DataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event){
        final Player player = event.getPlayer();
        if(CWBW.getInstance().getSetupHandler().getChat()){
            if(CWBW.getInstance().getDataHandler().getSetup().containsKey(player.getName())) {
                if (CWBW.getInstance().getDataHandler().getSetup().get(player.getName()).equals(DataHandler.Setup.GAMEMODE)) {
                    final String message = event.getMessage();
                    if(!(message.chars().allMatch(c -> (c >= '0' && c <= '9')))){
                        player.sendMessage("§cYou can only use numbers!");
                    } else {

                    }
                }
            }
        }
    }
}
