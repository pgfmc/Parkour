package net.pgfmc.parkour.abilities.airdash;

import java.util.LinkedList;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class AirDasher implements Listener {
	
	public static LinkedList<AirDasher> AIR_JUMPERS = new LinkedList<AirDasher>();
	
	public enum State {
		Ready,
		Cooldown,
		Gliding,
		Ground
	};
	
	Player p;
	State state;
	AirDasherHandler handler;
	
	public AirDasher(Player p, State state, AirDasherHandler handler)
	{
		this.p = p;
		this.state = state;
		this.handler = handler;
		
	}

	
	
	
	
	public void setState(State state)
	{
		this.state = state;
	}
	
	public State getState()
	{
		return state;
	}
	
	public Player getPlayer()
	{
		return p;
	}
	
	

}
