package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.game.*;
import ece373.catan.board.*;

public class RoadBuildingCard extends DevelopmentCard{
	private String title;
	private String description;
	
	public RoadBuildingCard() {
		title = "Road Buidling Card";
		description = "Place 1 new road as if you had just built it.";
				
	}
	
	public void playCard(Game g,Player p) {
		g.getBoard().buildRoadWithPlayer(p);
		p.getDevelopmentCards().remove(this);
		return;
	}
}
