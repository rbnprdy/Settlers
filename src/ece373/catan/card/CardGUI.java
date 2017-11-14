package ece373.catan.card;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class CardGUI extends JFrame {
	private Card card;
	
	public CardGUI(Card new_card) {
		card = new_card;
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void buildGUI() {
		this.setSize(new Dimension(400,600));
		//this.setUndecorated(true);		//REMOVING TOOLBAR
	}
}
