package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.board.*;
import ece373.catan.game.*;

public class KnightCard extends DevelopmentCard{
	private String title;
	private String description;
	private boolean beenPlayed;
	
	public KnightCard(){
		title = "Knight";
		description = "Move the robber. Steal 1 resource card from the owner "
				+ "of an adjacent settlement or city";
		beenPlayed = false;
	}
	
	public void setBeenPlayed(boolean x) {
		beenPlayed = x;
		return;
	}
	
	public boolean getBeenPlayed() {
		return beenPlayed;
	}
}
