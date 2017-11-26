package ece373.catan.player;


import java.awt.Color;
import java.util.ArrayList;
import ece373.catan.board.*;
import ece373.catan.card.*;


public class Player {
		private String playerName;
		private ArrayList<ResourceCard> resourceCards;
		private ArrayList<DevelopmentCard> developmentCards;
		private LargestArmyCard largestArmyCard;
		private LongestRoadCard longestRoadCard;
		private ArrayList<Settlement> settlements;
		private ArrayList<City> cities;
		private ArrayList<Road> roads;
		private int victoryPoints;
		private Color color;
		
		public Player(String new_player_name, Color color) {
			playerName = new_player_name;
			this.color = color;
			resourceCards = new ArrayList<ResourceCard>();
			developmentCards = new ArrayList<DevelopmentCard>();
			largestArmyCard = null;
			longestRoadCard = null;
			settlements = new ArrayList<Settlement>();
			cities = new ArrayList<City>();
			roads = new ArrayList<Road>();
			victoryPoints = 0;
		}
		
		public Color getColor() {
			return this.color;
		}
		
		public void buildRoad(Board board, int edge_index) {
			if(board.getEdges().get(edge_index).getRoad() == null) {
				Road new_road = new Road(this);
				board.getEdges().get(edge_index).setRoad(new_road);
			}
			else {
				System.out.println("A road is already built on this edge");
			}
			return;
		}
		
		public void buildSettlement(Board board, int node_index) {
			if(board.getNodes().get(node_index).getSettlement() == null) {
				Settlement new_settlement = new Settlement(this);
				board.getNodes().get(node_index).setSettlement(new_settlement);
			}
			else {
				System.out.println("A Settlement is already built on this node");
			}
			return;
		}
		
		public void buildCity(Board board, int node_index) {
			if(board.getNodes().get(node_index).getSettlement() != null) {
				if(board.getNodes().get(node_index).getSettlement().getPlayer() == this) {
					City new_city = new City(this);
					board.getNodes().get(node_index).setCity(new_city);
				}
				else {
					System.out.print("This is another player's settlement");
				}
			}
			else {
				System.out.print("There is no settlement at this node");
			}
			return;
		}
		
		public void proposeTrade(Player player) {
			
			return;
		}
		
		public ArrayList<ResourceCard> getResourceCards(){
			return resourceCards;
		}
		
		public void addResourceCardOfType(ResourceType type) {
			resourceCards.add(new ResourceCard(type));
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
		
		public void addSettlement(Settlement newSettle) {
			settlements.add(newSettle);
		}
		
		public void addCity(City newCity) {
			cities.add(newCity);
		}
		
		public void addRoad(Road newRoad) {
			roads.add(newRoad);
		}
		
		public ArrayList<Settlement> getSettlements(){
			return this.settlements;
		}
		
		public ArrayList<City> getCities(){
			return this.cities;
		}
		
		public ArrayList<Road> getRoads(){
			return this.roads;
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
