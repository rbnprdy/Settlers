package ece373.catan.game;

import ece373.catan.player.*;
import ece373.catan.board.*;
import ece373.catan.card.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.Random;

public class Game {
	
	private ArrayList<Player> players;
	private Board board;
	private ArrayList<DevelopmentCard> developmentCards;
	private Player currentPlayer;
	private GameGUI gui;
	

	public Game() {
		players = new ArrayList<Player>();
		board = new Board(this);
		developmentCards = new ArrayList<DevelopmentCard>();
		currentPlayer = null;
		this.gui = null;
	}
	
	public void setGUI(GameGUI g) {
		this.gui = g;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(Player newPlay) { //adds a player to the game 
		players.add(newPlay);
	}
	
	public Board getBoard() {// returns the game board
		return this.board;
	}
	
	public void setBoard(Board newBoard) { //creates the new board
		board = newBoard;
	}
	
	public ArrayList<DevelopmentCard> getDevelopmentCards(){ //returns the entire deck of development cards
		return this.developmentCards;
	}
	
	public void setDevelopmentCards(ArrayList<DevelopmentCard> deck) { //sets the development card deck
		developmentCards = deck;
	}
	
	public Player getCurrentPlayer() { //returns the player who currently is using their turn
		return this.currentPlayer;
	}
	
	public void setCurrentPlayer(Player play) {
		currentPlayer = play;
	}
	
	public Player checkForWinner() { //needs to be made
		
		for (Player p: players) {
			if (p.getVictoryPoints() >= 10) {
				return p;
			}
		}
		
		return null;
	}
	
	public void continueInitialBuilding() {
		gui.nextInitialBuild();
	}
	
	public void beginNextTurn() {
		
		this.checkForLongestRoad();
		this.checkForLargestArmy();
		
		// Check for winner
		Player winner = checkForWinner();
		if (winner != null) {
			JOptionPane.showOptionDialog(null, 
			        winner.getName() + " won!",  
			        "Game Over",
			        JOptionPane.OK_OPTION, 
			        JOptionPane.PLAIN_MESSAGE, 
			        null, 
			        new String[]{"Exit"}, // this is the array
			        "default");
			
			System.exit(0);
		}
		
		int nextIndex = players.indexOf(currentPlayer) + 1;
		if (nextIndex == players.size()) {
			currentPlayer = players.get(0);
		} else {
			currentPlayer = players.get(nextIndex);
		}

		gui.loadTurnGUI();
	}
	
	public Integer rollDice() {
		Integer dieOne, dieTwo, sum;
		Random randOne = new Random();
		Random randTwo = new Random();
		
		dieOne = randOne.nextInt(5) + 1;
		dieTwo = randTwo.nextInt(5) + 1;
		
		sum = dieOne + dieTwo;
		
		return sum;
	}
	
	public void updatePlayerGUI() {
		gui.reloadPlayerGUI();
	}
	
	public void checkForLongestRoad() {
		int maxCount = 4;
		Player currentMax = null;
		
		for (Player p: players) {
			if (p.getLongestRoadCard() != null) {
				maxCount = p.getRoads().size();
				currentMax = p;
			}
		}
		
//		Player a = players.get(1);
//		a.setLongestRoadCard(new LongestRoadCard());
		
		for (Player p: players) {
		if (p != currentMax && p.getRoads().size() > maxCount) {
			currentMax.setLargestArmyCard(null);
			p.setLongestRoadCard(new LongestRoadCard());
			
			JOptionPane.showOptionDialog(null, 
			        p.getName() + " now has the longest road!",  
			        "Longest Road",
			        JOptionPane.OK_OPTION, 
			        JOptionPane.PLAIN_MESSAGE, 
			        null, 
			        new String[]{"Okay"}, // this is the array
			        "default");
			
			return;
		}
	}
		
	}
	
	public void checkForLargestArmy() {
		int maxCount = 2;
		Player currentMax = null;
		
		for (Player p: players) {
			if (p.getLargestArmyCard() != null) {
				maxCount = p.getArmySize();
				currentMax = p;
			}
		}
		
//		Player a = players.get(0);
//		a.setLargestArmyCard(new LargestArmyCard());
		
		for (Player p: players) {
			if (p != currentMax && p.getArmySize() > maxCount) {
				currentMax.setLargestArmyCard(null);
				p.setLargestArmyCard(new LargestArmyCard());
				
				JOptionPane.showOptionDialog(null, 
				        p.getName() + " now has the largest army!",  
				        "Largest Army",
				        JOptionPane.OK_OPTION, 
				        JOptionPane.PLAIN_MESSAGE, 
				        null, 
				        new String[]{"Okay"}, // this is the array
				        "default");
				
				return;
			}
		}
	}
}
