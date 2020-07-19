package nl.thedutchmc.broadcaster;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.md_5.bungee.api.ChatColor;

public class Broadcaster extends JavaPlugin {
	
	public static Broadcaster INSTANCE;
	public static BukkitTask broadcastTask;
	
	public ConfigurationHandler configurationHandler;
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		
		configurationHandler = new ConfigurationHandler();
		configurationHandler.loadConfig();
		
		if(this.isEnabled()) {
			
			getCommand("reloadconfig").setExecutor(new CommandHandler());
					
			scheduleBroadcast();
		}
	}
	
	public static void scheduleBroadcast() {
		
		if(!ConfigurationHandler.enableBroadcast) return;
		
		broadcastTask = new BukkitRunnable() {
			
			int broadcastIndex = 0;
			
			@Override
			public void run() {
				
				if(ConfigurationHandler.broadcastRandomizedOrder) {
					final Random r = new Random();
					broadcastIndex = r.nextInt(ConfigurationHandler.broadcastMessages.size());
				}
				
				if(broadcastIndex >= ConfigurationHandler.broadcastMessages.size()) broadcastIndex = 0;
								
				String message = ConfigurationHandler.broadcastMessages.get(broadcastIndex);
				String[] comp = message.split("&");
								
				if(comp[0].equals("raw")) {

					if(Bukkit.getOnlinePlayers().size() != 0)
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw @a " + comp[1]);
				
				} else if(comp[0].equals("text")) {
					
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.sendMessage(ChatColor.of(ConfigurationHandler.broadcastTextHexColor) + comp[1]);
					}
				}
				
				if(!ConfigurationHandler.broadcastRandomizedOrder) {
					broadcastIndex++;
				}
			}
		}.runTaskTimer(INSTANCE, 120, ConfigurationHandler.broadcastInterval * 20);
	}
}
