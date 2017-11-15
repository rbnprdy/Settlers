package ece373.catan.card;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.*;

public class CardGUI extends JFrame {
	private Card card;
	private JLabel faceImageLabel;
	
	public CardGUI(Card new_card) {
		card = new_card;
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildGUI();	
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void buildGUI() {
		this.setSize(new Dimension(500,780));
		this.setUndecorated(true);		//REMOVING TOOLBAR
		faceImageLabel = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/brickcard.png")).getImage();
		faceImageLabel.setSize(new Dimension(500,780));
		if(card instanceof LargestArmyCard) {
			
		}
		faceImageLabel.setIcon(new ImageIcon(img));
		this.getContentPane().add(faceImageLabel);
		
		
	}
}
