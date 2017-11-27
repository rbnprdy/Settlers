package ece373.catan.game;

import ece373.catan.player.*;
import ece373.catan.board.*;
import ece373.catan.card.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
	
	private ArrayList<Player> players;
	private Board board;
	private ArrayList<DevelopmentCard> developmentCards;
	private Player currentPlayer;
	private Integer playerNumber;
	private GameGUI gui;
	

	public Game() {
		players = new ArrayList<Player>();
		board = new Board(this);
		developmentCards = new ArrayList<DevelopmentCard>();
		currentPlayer = null;
		playerNumber = 0;
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
		Player winner = new Player("temp", Color.black);
		
		return winner;
	}
	
	public void continueInitialBuilding() {
		gui.nextInitialBuild();
	}
	
	public void beginNextTurn() {
		
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
		
		dieOne = randOne.nextInt(6);
		dieTwo = randTwo.nextInt(6);
		
		sum = dieOne + dieTwo;
		
		return sum;
	}
	
	public Integer getPlayerNumber() {
		return this.playerNumber;
	}
	
	public void setPlayerNumber() {
		playerNumber = 0;
	}
}
