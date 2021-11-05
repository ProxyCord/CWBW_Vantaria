// This class is created by LookIP
package de.proxycord.cwbw.commands;

import de.proxycord.cwbw.CWBW;
import de.proxycord.cwbw.handlers.DataHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Locations implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            final Player player = ((Player) sender).getPlayer();
            final String prefix = CWBW.getInstance().getDataHandler().getLoadedMessages().get("message.prefix");
            if(player.hasPermission("system.admin.setlocation")){
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("spawn")){
                        CWBW.getInstance().getLocationManager().setLocation("spawn", player.getLocation());
                        player.sendMessage(prefix + "§aYou set the spawn location!");
                        if(CWBW.getInstance().getDataHandler().getSetup().containsKey(player.getName())){
                            if(CWBW.getInstance().getDataHandler().getSetup().get(player.getName()).equals(DataHandler.Setup.SPAWN)){

                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
