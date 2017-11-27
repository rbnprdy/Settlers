package ece373.catan.player;


import java.awt.Color;
import java.util.ArrayList;
import ece373.catan.board.*;
import ece373.catan.card.*;
import ece373.catan.game.*;


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

	public void calculateVictoryPoints() {
		int newVictoryPoints = 0;
		for(Settlement s: settlements) {
			newVictoryPoints++;
		}
		for(City c: cities) {
			newVictoryPoints = newVictoryPoints + 2;
		}
		for(DevelopmentCard d: developmentCards) {
			if(d instanceof VictoryPointCard) {
				newVictoryPoints++;
			}
		}
		if(largestArmyCard != null) {
			newVictoryPoints = newVictoryPoints + 2;
		}

		victoryPoints = newVictoryPoints;
		return;
	}

	public void addCard(ResourceCard rc) {
		resourceCards.add(rc);
		return;
	}
	
	public void removeResourceCardOfType(ResourceType t) {
		int index = 0;
		for (int i = 0; i < resourceCards.size(); i++) {
			if (resourceCards.get(i).getType() == t) {
				index = i;
				break;
			}
		}
		
		resourceCards.remove(index);
	}

	public void addCard(DevelopmentCard dc) {
		developmentCards.add(dc);
		return;
	}

	public void addCard(LongestRoadCard lrc) {
		longestRoadCard = lrc;
		return;
	}

	public void addCard(LargestArmyCard lac) {
		largestArmyCard = lac;
		return;
	}

	public void buildDevelopmentCard(Game g) {
		for(ResourceCard rc: resourceCards) {
			if(rc.getType() == ResourceType.SHEEP) {
				resourceCards.remove(rc);
				break;
			}
		}
		for(ResourceCard rc: resourceCards) {
			if(rc.getType() == ResourceType.WHEAT) {
				resourceCards.remove(rc);
				break;
			}
		}
		for(ResourceCard rc: resourceCards) {
			if(rc.getType() == ResourceType.STONE) {
				resourceCards.remove(rc);
				break;
			}
		}
		//g.drawDevelopmentCard(this);  IMPLEMENTED IN GAME CLASS
		
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

	public String getName() {
		return playerName;
	}

	public int getVictoryPoints() {
		return victoryPoints;
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
