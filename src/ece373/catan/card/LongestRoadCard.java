package ece373.catan.card;
import ece373.catan.player.*;
import ece373.catan.board.*;

public class LongestRoadCard {
	private String title;
	private String description;
	
	public LongestRoadCard() {
		title = "Longest Road";
		description = "<html><b>2 Victory Points</b><br/>This Card goes to the player with the longest "
				+ "unbroken road of at least 5 segments. Another player who builds a longer road takes this card</html>";
	}
	
	public void CalculateLongestRoad(Board board) {
		//TODO
		return;
	}
}
