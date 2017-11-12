package ece373.catan.player;


import java.util.ArrayList;
import ece373.catan.board.*;
import ece373.catan.card.*;


public class Player {
		private String player_name;
		private ArrayList<ResourceCard> resourceCards;
		private ArrayList<DevelopmentCard> developmentCards;
		private LargestArmyCard largestArmyCard;
		private LongestRoadCard longestRoadCard;
		private int victoryPoints;
		
		public Player(String new_player_name) {
			player_name = new_player_name;
			resourceCards = new ArrayList<ResourceCard>();
			developmentCards = new ArrayList<DevelopmentCard>();
			largestArmyCard = null;
			longestRoadCard = null;
			victoryPoints = 0;
		}
		
		public void buildRoad(Board board, int edge_index) {
			Road new_road = new Road(this);
			board.getEdges().get(edge_index).setRoad(new_road);
			return;
		}
		
		public void buildSettlement(Board board, int node_index) {
			Settlement new_settlement = new Settlement(this);
			board.getNodes().get(node_index).setSettlement(new_settlement);
			return;
		}
		
		public void buildCity(Board board, int node_index) {
			City new_city = new City(this);
			board.getNodes().get(node_index).setCity(new_city);
			return;
		}
		
		public void proposeTrade(Player player) {
			
			return;
		}
		
		public ArrayList<ResourceCard> getResourceCards(){
			return resourceCards;
		}
		
		public ArrayList<DevelopmentCard> getDevelopmentCards(){
			return developmentCards;
		}
		
		public LongestRoadCard getLongestRoadCard() {
			return longestRoadCard;
		}
		
		public LargestArmyCard getLargestArmyCard() {
			return largestArmyCard;
		}
		
		public void makeTrade(Player player, ArrayList<ResourceCard> my_offered_cards, ArrayList<ResourceCard> their_offered_cards) {
			for(ResourceCard my_offered_card: my_offered_cards) {	//Go through my offered cards
				for(ResourceCard user_card: resourceCards) {	//Go through my cards
					if(user_card.getType().equals(my_offered_card.getType())){	//if equal, swap with player
						player.getResourceCards().add(my_offered_card);
						this.getResourceCards().remove(my_offered_card);
					}
				}
			}
			for(ResourceCard their_offered_card: their_offered_cards) {
				for(ResourceCard other_user_card: player.getResourceCards()) {
					if(other_user_card.getType().equals(their_offered_card.getType())) {
						this.getResourceCards().add(their_offered_card);
						player.getResourceCards().remove(their_offered_card);
					}
				}
			}
			
			return;
		}
		
		public int getArmySize() {
			int army_size = 0;
			for(DevelopmentCard card: developmentCards) {
				if(card instanceof KnightCard) {
					army_size = army_size + 1;
				}
			}
			return army_size;
		}
}
