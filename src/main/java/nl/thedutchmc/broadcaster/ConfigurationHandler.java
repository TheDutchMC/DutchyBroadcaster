package nl.thedutchmc.broadcaster;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHandler {

	public static boolean enableBroadcast, broadcastRandomizedOrder;
	public static String broadcastTextHexColor;
	public static int broadcastInterval;
	public static List<String> broadcastMessages;
	
	private File file;
	private FileConfiguration config;
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void loadConfig() {
		
		file = new File(Broadcaster.INSTANCE.getDataFolder(), "config.yml");
		
		if(!file.exists()) {
			file.getParentFile().mkdirs();
		
			Broadcaster.INSTANCE.saveResource("config.yml", false);
		}
		
		config = new YamlConfiguration();
		
		try {
			config.load(file);
			readConfig();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			System.err.println("[DutchyBroadcaster] Invalid config.yml! Please make sure the syntax is correct! Disabling.");
			Bukkit.getPluginManager().disablePlugin(Broadcaster.INSTANCE);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readConfig() {
		
		//boolean
		enableBroadcast = Boolean.valueOf(this.getConfig().getString("enableBroadcast"));
		broadcastRandomizedOrder = Boolean.valueOf(this.getConfig().getString("broadcastRandomizedOrder"));
		
		//Integer
		broadcastInterval = Integer.valueOf(this.getConfig().getString("broadcastInterval"));
		
		//List
		broadcastMessages = (List<String>) this.getConfig().getList("broadcastMessages");
		
		//String
		broadcastTextHexColor = this.getConfig().getString("broadcastTextHexColor");
		
		//Checks to see if config is fully valid
		if(broadcastMessages.size() == 0) {
			System.err.println("[DutchyBroadcaster] broadcastMessages may not be empty! Disabling.");
			Bukkit.getPluginManager().disablePlugin(Broadcaster.INSTANCE);
		}
		
		for(int i = 0; i < broadcastMessages.size(); i++) {
			String str = broadcastMessages.get(i);
			if(!str.contains("&")) {
				System.err.println("[DutchyBroadcaster] It seems broadcastMessages (number " + (i + 1) +") is invalid! Disabling.");
				Bukkit.getPluginManager().disablePlugin(Broadcaster.INSTANCE);
			}
		}
				
	}
}
