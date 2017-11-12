package ece373.catan.board;

import ece373.catan.player.*;

public class Settlement {

	private Player player;
	
	public Settlement(Player p) {
		player = p;
	}
	
	public Player getPlayer() {
		return player;
	}
}
