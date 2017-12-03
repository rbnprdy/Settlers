package ece373.catan.card;

import ece373.catan.player.*;
import ece373.catan.game.*;

public class YearOfPlentyCard extends DevelopmentCard{
	private String title;
	private String description;
	private boolean beenPlayed;
	
	public YearOfPlentyCard() {
		title = "Year of Plenty Card";
		description = "Take any 2 resource cards from the bank and add them to your hand.";
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
