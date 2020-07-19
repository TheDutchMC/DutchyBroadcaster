package nl.thedutchmc.broadcaster;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equals("reloadconfig")) {
			
			if(!sender.hasPermission("broadcaster.reloadconfig")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
				return true;
			}
			
			
			Broadcaster.INSTANCE.configurationHandler.readConfig();
			sender.sendMessage(ChatColor.GOLD + "Reloading config.");
			
			if(!ConfigurationHandler.enableBroadcast) {
				Broadcaster.broadcastTask.cancel();
			} else {
				if(Broadcaster.broadcastTask != null && Broadcaster.broadcastTask.isCancelled()) {
					Broadcaster.scheduleBroadcast();
				}
			}
			
			return true;
		}
		
		return false;
	}
	
}
