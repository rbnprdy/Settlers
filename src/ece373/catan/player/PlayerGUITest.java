package ece373.catan.player;

import javax.swing.*;
import java.awt.Color;
import ece373.catan.card.*;

public class PlayerGUITest {
	public static void main(String[] args) {
		
		//GENERATING CARDS
		ResourceCard sheepcard1 = new ResourceCard(ResourceType.SHEEP);
		ResourceCard sheepcard2 = new ResourceCard(ResourceType.SHEEP);
		ResourceCard brickcard = new ResourceCard(ResourceType.BRICK);
		ResourceCard stonecard = new ResourceCard(ResourceType.STONE);
		ResourceCard wheatcard = new ResourceCard(ResourceType.WHEAT);
		ResourceCard  woodcard = new  ResourceCard(ResourceType.WOOD);
		
		JFrame frame = new JFrame();
		PlayerGUI newGUI;
		Player p1 = new Player("Ruben", new Color(0xFF0000));
		
		p1.addCard(sheepcard1);
		p1.addCard(sheepcard2);
		p1.addCard(brickcard);
		//p1.addCard(stonecard);
		p1.addCard(wheatcard);
		p1.addCard(woodcard);
		
		newGUI = new PlayerGUI(p1);
		frame.add(newGUI);
		frame.setSize(newGUI.getWidth(), newGUI.getHeight());
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
		
	}
}
