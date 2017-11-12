package ece373.catan.board;

import ece373.catan.player.Player;

public class Road {

	private Player player;
	
	public Road(Player p) {
		player = p;
	}
	
	public Player getPlayer() {
		return player;
	}
}
