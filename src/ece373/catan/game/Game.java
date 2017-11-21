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
	private Integer numPlayers;
	

	public Game() {
		players = new ArrayList<Player>();
		board = new Board();
		developmentCards = new ArrayList<DevelopmentCard>();
		currentPlayer = null;
		playerNumber = 0;
		numPlayers = 0;
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
	
	public void beginNextTurn() {
		int i, number;
		String action, cont;
		Boolean flag, endTurn;
		ArrayList<Edge> availableEdges = new ArrayList<Edge>();
		ArrayList<Node> availableForCity, availableForSettlement;
		Scanner scan = new Scanner(System.in);
		
		availableForCity = new ArrayList<Node>();
		availableForSettlement = new ArrayList<Node>();
		endTurn = false;
		
		if (playerNumber == numPlayers) {
			currentPlayer = players.get(0);
			playerNumber = 0;
		}
		else {
			currentPlayer = players.get(playerNumber);
			playerNumber = playerNumber + 1;
		}
		
		while (!endTurn) {
			flag = false;
			System.out.println("Enter one of the following to choose the action: R to build road, S to build settlement, C to build city: ");
			action = scan.next();
		
			if (action.equals("R")) {
				while(!flag) {
					availableEdges = board.getAvailableEdgesFor(currentPlayer);
					
					System.out.println("Enter number of preffered edge: ");
					number = scan.nextInt();
					
					for (i=0; i<board.getAvailableEdgesFor(currentPlayer).size();i++) {
						if (board.getEdges().get(number)==availableEdges.get(i)) {
							currentPlayer.buildRoad(board, number);
							flag = true;
							break;
						}
					}
					
					if (!flag) {
						System.out.println("That edge is not available, would you like to select another? Y/N:");
						cont = scan.next();
						
						if (cont.equals("N")) {
							flag = true;
						}
					}
					
				}
			}
			
			else if (action.equals("S")) {
				while(!flag) {
					availableForSettlement = board.getAvailableNodesForSettlementsFor(currentPlayer);
					
					System.out.println("Enter number of preffered node: ");
					number = scan.nextInt();
					
					for (i=0; i<board.getAvailableNodesForSettlementsFor(currentPlayer).size();i++) {
						if (board.getNodes().get(number)==availableForSettlement.get(i)) {
							currentPlayer.buildSettlement(board, number);
							flag = true;
							break;
						}
					}
					
					if (!flag) {
						System.out.println("That node is not available, would you like to select another? Y/N:");
						cont = scan.next();
						
						if (cont.equals("N")) {
							flag = true;
						}
					}
					
					
				}
			}
			
			else if (action.equals("C")) {
				while(!flag) {
					availableForCity = board.getAvailableNodesForCitiesFor(currentPlayer);
					
					System.out.println("Enter number of preffered node: ");
					number = scan.nextInt();
					
					for (i=0; i<board.getAvailableNodesForCitiesFor(currentPlayer).size();i++) {
						if (board.getNodes().get(number-1)==availableForCity.get(i)) {
							currentPlayer.buildCity(board, number);
							flag = true;
							break;
						}
					}
					if (!flag) {
						System.out.println("That node is not available, would you like to select another? Y/N:");
						cont = scan.next();
						
						if (cont.equals("N")) {
							flag = true;
						}
					}
					
				}
			}
			
			System.out.println("Would you like to end your turn? Y/N: ");
			cont = scan.next();
			if (cont.equals("Y")) {
				endTurn = true;
			}
		}
		
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
	
	public void setNumberPlayers(Integer numPlay) {
		numPlayers = numPlay;
	}
	
	public Integer getNumberPlayers() {
		return this.numPlayers;
	}
	
}
