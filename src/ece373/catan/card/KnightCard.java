package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.board.*;
import ece373.catan.game.*;

public class KnightCard extends DevelopmentCard{
	private String title;
	private String description;
	private boolean been_played;
	
	public KnightCard(){
		title = "Knight";
		description = "Move the robber. Steal 1 resource card from the owner "
				+ "of an adjacent settlement or city";
		been_played = false;
	}
	
	public void playCard(Game g,Player p) {
		g.getBoard().moveRobber();
		been_played = true;
		return;
	}
}
