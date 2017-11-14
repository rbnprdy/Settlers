package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.board.*;

public class MonopolyCard extends DevelopmentCard{
	private String title;
	private String description;
	
	public MonopolyCard() {
		title = "Monopoly";
		description = "When you play this card, announce one type of resource. All other players"
				+ "must give you all their resource cards of that type";
	}
	
	public void playCard(Player player) {
		// TODO Auto-generated method stub
		return;
	}
}
