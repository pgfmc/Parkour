package net.pgfmc.parkour;

import org.bukkit.plugin.java.JavaPlugin;

import net.pgfmc.parkour.abilities.airdash.AirDash;
import net.pgfmc.parkour.abilities.airdash.AirDasherHandler;

public class Main extends JavaPlugin {
	
	AirDasherHandler handler = new AirDasherHandler();
	
	public static Main plugin;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		getServer().getPluginManager().registerEvents(new AirDash(handler), this);
		handler.updateState();
	}
	
	@Override
	public void onDisable()
	{
		
	}

}
