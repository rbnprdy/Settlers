package ece373.catan.board;

import ece373.catan.player.*;

public class City {

	private Player player;
	
	public City(Player p) {
		player = p;
		p.addCity(this);
	}
	
	public Player getPlayer() {
		return player;
	}
}
