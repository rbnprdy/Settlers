package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.board.*;

public class KnightCard extends DevelopmentCard{
	private String title;
	private String description;
	
	public KnightCard(){
		title = "Knight";
		description = "Move the robber. Steal 1 resource card from the owner "
				+ "of an adjacent settlement or city";
	}
	
	public void playCard(Player p) {
		System.out.println("Method playCard not implemented for " + this.getClass().getName());
		return;
	}
}
