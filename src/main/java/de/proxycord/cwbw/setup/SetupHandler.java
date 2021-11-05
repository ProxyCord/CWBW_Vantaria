// This class is created by LookIP
package de.proxycord.cwbw.setup;

import de.proxycord.cwbw.CWBW;
import de.proxycord.cwbw.handlers.DataHandler;
import org.bukkit.entity.Player;

public class SetupHandler {

    private Boolean chat = false;

    public Boolean getChat() {
        return chat;
    }

    public void startSetup(final Player player){
        if(CWBW.getInstance().getDataHandler().getSetup().containsKey(player.getName())){
            player.sendMessage("§cYou have already started a setup, to start again, stop the server.");
        } else {
            CWBW.getInstance().getDataHandler().getSetup().put(player.getName(), DataHandler.Setup.SPAWN);
            player.sendMessage("§6§lHey! Welcome to the setup for CWBW by Vantaria.de");
            player.sendMessage("§7I show you all and you make this what i say okay?");
            player.sendMessage("§7Perfect! Then lets start.");
            player.sendMessage("§a§lPlease set the Spawn §7(/setlocation Spawn)");
        }
    }

    public void startSetupTwo(final Player player){
        CWBW.getInstance().getDataHandler().getSetup().replace(player.getName(), DataHandler.Setup.GAMEMODE);
        chat = true;
        player.sendMessage("§7Now... how many players can play max? (The max is 16 players)");
        player.sendMessage("§7Write this in chat, but make sure that you only use numbers!");
    }
}
