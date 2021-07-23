package net.pgfmc.parkour.abilities.airdash;

import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.pgfmc.parkour.Main;
import net.pgfmc.parkour.abilities.airdash.AirDasher.State;

public class AirDasherHandler {
	
	public static LinkedList<AirDasher> AIR_JUMPERS = new LinkedList<AirDasher>();
	
	public void addAirDasher(Player p)
	{
		AIR_JUMPERS.add(new AirDasher(p, State.Cooldown, this));
	}
	
	
	public void updateState()
	{
		Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable()
		{
            public void run()
            {
            	for (AirDasher ad : AIR_JUMPERS)
            	{
            		Player p = ad.getPlayer();
            		Location loc = p.getLocation();
            		loc.setY(loc.getY() - 0.1);
            		
            		/*
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
            		*/
            		
            		
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
            }
        }, 0, 1); // delay before start, delay before next loop

	}

	
	
	public void removeAirDasher(Player p)
	{
		for (AirDasher ad : AIR_JUMPERS)
		{
			if (ad.p.equals(p))
			{
				AIR_JUMPERS.remove(ad);
				return;
			}
		}
	}
	
	public AirDasher getAirDasher(Player p)
	{
		for (AirDasher ad : AIR_JUMPERS)
		{
			if (ad.p.equals(p))
			{
				return ad;
			}
		}
		
		return null;
	}
	

}
