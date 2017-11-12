package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.board.*;

public abstract class DevelopmentCard extends Card {
	private String title;
	private String description;
	public DevelopmentCard() {
		title = "Development Card";
		description = "Default development card description";
	}
	
	public abstract void playCard(Player player);
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String new_title) {
		title = new_title;
		return;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String new_description) {
		description = new_description;
		return;
	}
	

}
