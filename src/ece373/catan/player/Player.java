package ece373.catan.player;


import java.awt.Color;
import java.util.ArrayList;
import ece373.catan.board.*;
import ece373.catan.card.*;
import ece373.catan.game.*;


public class Player {
	private String playerName;
	private boolean buttonsDisabled;
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
		buttonsDisabled = false;
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
	
	public void disableButtons() {
		buttonsDisabled = true;
	}
	
	public void enableButtons() {
		buttonsDisabled = false;
	}
	
	public boolean areButtonsDisabled() {
		return buttonsDisabled;
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
			newVictoryPoints = newVictoryPoints + 1;
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
	
	public void setLongestRoadCard(LongestRoadCard c) {
		this.longestRoadCard = c;
	}

	public LargestArmyCard getLargestArmyCard() {
		return largestArmyCard;
	}

	public void setLargestArmyCard(LargestArmyCard c) {
		this.largestArmyCard = c;
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

	public void makeTrade(Player player, ResourceType my_offered_card, ResourceType their_offered_card) {
		this.removeResourceCardOfType(my_offered_card);
		player.addCard(new ResourceCard(my_offered_card));
		
		player.removeResourceCardOfType(their_offered_card);
		this.addCard(new ResourceCard(their_offered_card));

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
	
	public boolean hasCard(ResourceType rt) {
		for(ResourceCard rc: resourceCards) {
			if (rc.getType().equals(rt)) {
				return true;
			}
		}
		
		return false;
	}
}
