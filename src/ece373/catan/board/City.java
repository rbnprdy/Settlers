package ece373.catan.board;

import ece373.catan.player.*;

public class City {

	private Player player;
	
	public City(Player p) {
		player = p;
	}
	
	public Player getPlayer() {
		return player;
	}
}
