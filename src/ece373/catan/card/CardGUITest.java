package ece373.catan.card;

import javax.swing.*;

public class CardGUITest {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		CardGUI newGUI;

		ResourceCard rc1 = new ResourceCard(ResourceType.SHEEP);		
		
		newGUI = new CardGUI(rc1);
		frame.add(newGUI);
		frame.setSize(newGUI.getWidth(), newGUI.getHeight());
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setVisible(true);
		
	}
}
