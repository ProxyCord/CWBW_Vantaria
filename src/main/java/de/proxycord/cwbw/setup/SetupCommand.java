// This class is created by LookIP
package de.proxycord.cwbw.setup;

import de.proxycord.cwbw.CWBW;
import de.proxycord.cwbw.handlers.DataHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            final Player player = (Player)sender;
            if(player.hasPermission("system.admin.setup")){
                CWBW.getInstance().getSetupHandler().startSetup(player);
            }
        }
        return false;
    }
}
