package ece373.catan.game;

import javax.swing.*;

import ece373.catan.player.*;
import ece373.catan.board.*;
import ece373.catan.card.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

public class GameGUI extends JFrame {
	
	private Game game;
	private BoardGUI board;
	private JSplitPane window;
	private ArrayList<PlayerGUI> players;
	
	public GameGUI(Game g) {
		int i;
		PlayerGUI temp;
		
		game = g;
		players = new ArrayList<PlayerGUI>();
		board = new BoardGUI(game.getBoard(),game);
		
		temp = new PlayerGUI(game, game.getCurrentPlayer());
		
		window = new JSplitPane();
		
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(window);
		
		window.setSize((new Dimension (1600, 1600)));
		window.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		window.setLeftComponent(temp);
		window.setRightComponent(board);
		window.setBackground(new Color(0x00ff00));
		window.setDividerLocation(0.5);
		
		pack();
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		Player p1, p2, p3, p4;
		
		p1 = new Player("Ruben", new Color(0xffffff));
		p2 = new Player("Elan", new Color(0x0000ff));
		p3 = new Player("Kray", new Color(0x00ff00));
		p4 = new Player("Eddie", new Color(0xff0000));
		
		p1.addResourceCardOfType(ResourceType.SHEEP);
		p1.addResourceCardOfType(ResourceType.SHEEP);
		p1.addResourceCardOfType(ResourceType.BRICK);
		p1.addResourceCardOfType(ResourceType.STONE);
		p1.addResourceCardOfType(ResourceType.WHEAT);
		p1.addResourceCardOfType(ResourceType.WOOD);
		
		p2.addResourceCardOfType(ResourceType.BRICK);
		p2.addResourceCardOfType(ResourceType.STONE);
		p2.addResourceCardOfType(ResourceType.STONE);
		p2.addResourceCardOfType(ResourceType.STONE);
		p2.addResourceCardOfType(ResourceType.WHEAT);
		p2.addResourceCardOfType(ResourceType.WHEAT);
		p2.addResourceCardOfType(ResourceType.WOOD);
		
		p3.addResourceCardOfType(ResourceType.WHEAT);
		p3.addResourceCardOfType(ResourceType.WHEAT);
		
		p4.addResourceCardOfType(ResourceType.BRICK);
		
		g.addPlayer(p1);
		g.addPlayer(p2);
		g.addPlayer(p3);
		g.addPlayer(p4);
		
		g.getPlayers().get(0).buildSettlement(g.getBoard(), 13);
		g.getPlayers().get(0).buildRoad(g.getBoard(), 13);
		g.getPlayers().get(0).buildRoad(g.getBoard(), 14);
		
		g.getPlayers().get(1).buildSettlement(g.getBoard(), 45);
		g.getPlayers().get(1).buildRoad(g.getBoard(), 58);
		g.getPlayers().get(1).buildRoad(g.getBoard(), 59);
		g.getPlayers().get(1).buildCity(g.getBoard(), 45);
		
		g.getPlayers().get(2).buildSettlement(g.getBoard(), 34);
		g.getPlayers().get(2).buildRoad(g.getBoard(), 41);
		g.getPlayers().get(2).buildRoad(g.getBoard(), 35);
		g.getPlayers().get(2).buildSettlement(g.getBoard(), 23);
		
		g.getPlayers().get(3).buildSettlement(g.getBoard(), 25);
		g.getPlayers().get(3).buildRoad(g.getBoard(), 30);
		g.getPlayers().get(3).buildRoad(g.getBoard(), 31);
		g.getPlayers().get(3).buildCity(g.getBoard(), 25);
		
		g.setCurrentPlayer(g.getPlayers().get(0));
		
		new GameGUI(g).setVisible(true);
		
	}
	
	
	
}
