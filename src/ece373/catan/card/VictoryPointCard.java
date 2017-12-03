package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.game.*;
import ece373.catan.board.*;

public class VictoryPointCard extends DevelopmentCard {
	private String title;
	private String description;
	private boolean beenPlayed;
	
	public VictoryPointCard() {
		title = "Victory Point Card";
		description = "This card gives you one extra victory point";
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
