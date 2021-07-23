package net.pgfmc.parkour.abilities.airdash;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

import net.pgfmc.parkour.abilities.airdash.AirDasher.State;

public class AirDash implements Listener {
	
	AirDasherHandler handler;
	
	public AirDash(AirDasherHandler handler)
	{
		this.handler = handler;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		handler.addAirDasher(e.getPlayer());
		return;
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		handler.removeAirDasher(e.getPlayer());
		return;
	}
	
/*
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		AirDasher ad = handler.getAirDasher(p);
		Location loc = p.getLocation();
		loc.setY(loc.getY() - 0.1);
		
		// 
		if (p.getVelocity().getY() == 0.0) // No movement in the Y axis
		{
			if (!p.getWorld().getBlockAt(loc).isPassable()) // If below them is ground/not air
			{
				ad.setState(State.Ground);
				return;
			} else { return; }// If below them is air
			
		} else // Movement in y axis (jump)
		{
			if (ad.getState() == State.Ground)
			{
				ad.setState(State.Ready);
				return;
			}
		}
		// 
		
		
		if (!p.getWorld().getBlockAt(loc).isPassable()) // If below them is not air
		{
			ad.setState(State.Ground);
			return;
		} else // If below them is air
		{
			if (ad.getState() == State.Ground)
			{
				ad.setState(State.Ready);
			}
		}
	}
*/
	
	
	@EventHandler
	public void onDash(PlayerInteractEvent e)
	{
		Action action = e.getAction();
		if (!(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK))) { return; }
		
		Player p = e.getPlayer();
		AirDasher ad = handler.getAirDasher(p);
		Location loc = p.getLocation();
		loc.setY(loc.getY() - 0.1);

		
		if (!(ad.getState() == State.Ready)) { return; } // If they aren't ready
		
		p.getLocation().zero();
		Vector dir = p.getLocation().getDirection();
		p.setVelocity(dir.multiply(1.25));
		
		ad.setState(State.Cooldown);
	}
	
	

}
