package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.game.*;
import ece373.catan.board.*;

public class LargestArmyCard {
	private String title;
	private String description;
	
	public LargestArmyCard(){
		title = "Largest Army";
		description = "<html><b>2 Victory Points</b><br/>The first player to play 3 knight cards gets this card. "
				+ "Another player who plays more knight cards takes this card.</html>";
	}
	
	public Player calculateLargestArmy(Game game) {
		int max_army_size = 0;
		Player max_army_player = null;
		for(Player player: game.getPlayers()) {
			if (player.getArmySize() > max_army_size) {
				max_army_size = player.getArmySize();
				max_army_player = player;
			}
		}
		
		return max_army_player;
	}
}
