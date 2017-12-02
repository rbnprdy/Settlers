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
		KnightCard kCard1 = new KnightCard();
		KnightCard kCard2 = new KnightCard();
		VictoryPointCard vCard1 = new VictoryPointCard();
		VictoryPointCard vCard2 = new VictoryPointCard();
		YearOfPlentyCard yopCard = new YearOfPlentyCard();
		LargestArmyCard lac = new LargestArmyCard();
		LongestRoadCard lrc = new LongestRoadCard();
		
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
		p1.addCard(vCard1);
		p1.addCard(vCard2);
		p1.addCard(lac);
		p1.addCard(kCard1);
		p1.addCard(kCard2);
		p1.addCard(lrc);
		
		p2.addCard(stonecard);
		
		newGUI = new PlayerGUI(g1, p1);
		frame.add(newGUI);
		frame.setSize(newGUI.getWidth(), newGUI.getHeight());
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
		
	}
}
