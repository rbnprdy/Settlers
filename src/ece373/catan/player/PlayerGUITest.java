package ece373.catan.player;

import javax.swing.*;
import java.awt.Color;
import ece373.catan.card.*;
import ece373.catan.game.*;

public class PlayerGUITest {
	public static void main(String[] args) {
		
		//GENERATING CARDS
		ResourceCard sheepcard1 = new ResourceCard(ResourceType.SHEEP);
		ResourceCard sheepcard2 = new ResourceCard(ResourceType.SHEEP);
		ResourceCard brickcard = new ResourceCard(ResourceType.BRICK);
		ResourceCard stonecard = new ResourceCard(ResourceType.STONE);
		ResourceCard wheatcard = new ResourceCard(ResourceType.WHEAT);
		ResourceCard  woodcard = new  ResourceCard(ResourceType.WOOD);
		VictoryPointCard vCard = new VictoryPointCard();
		YearOfPlentyCard yopCard = new YearOfPlentyCard();
		LargestArmyCard lac = new LargestArmyCard();
		
		Game g1 = new Game();
		JFrame frame = new JFrame();
		PlayerGUI newGUI;
		Player p1 = new Player("Ruben", new Color(0xFF0000));
		Player p2 = new Player("Tofu", new Color(0x00FF00));
		Player p3 = new Player("Kray", new Color(0x0000FF));
		
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		g1.addPlayer(p3);
		
		p1.addCard(sheepcard1);
		p1.addCard(sheepcard2);
		p1.addCard(brickcard);
		//p1.addCard(stonecard);
		p1.addCard(wheatcard);
		p1.addCard(woodcard);
		p1.addCard(yopCard);
		p1.addCard(vCard);
		p1.addCard(lac);
		
		newGUI = new PlayerGUI(g1, p1);
		frame.add(newGUI);
		frame.setSize(newGUI.getWidth(), newGUI.getHeight());
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
		
	}
}
