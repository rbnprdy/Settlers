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
		this.setSize(new Dimension(300,460));
		this.setUndecorated(true);		//REMOVING TOOLBAR
		faceImageLabel = new JLabel();
		Image img; 
		Image scaled_img;
		faceImageLabel.setSize(new Dimension(300,460));
		if(card instanceof ResourceCard) {
			switch(((ResourceCard) card).getType()){
			case BRICK: img = new ImageIcon(this.getClass().getResource("/card/brickcard.png")).getImage();
				break;
			case SHEEP: img = new ImageIcon(this.getClass().getResource("/card/sheepcard.png")).getImage();
				break;
			case STONE: img = new ImageIcon(this.getClass().getResource("/card/rockcard.png")).getImage();
				break;
			case WHEAT: img = new ImageIcon(this.getClass().getResource("/card/wheatcard.png")).getImage();
				break;
			case WOOD:	img = new ImageIcon(this.getClass().getResource("/card/woodcard.png")).getImage();
				break;
			default:  img = new ImageIcon(this.getClass().getResource("/card/cardback.png")).getImage();
				break;
			}
		}
		else if(card instanceof DevelopmentCard) {
			if(card instanceof KnightCard) {
				img = new ImageIcon(this.getClass().getResource("/card/knightcard.png")).getImage();
			}
			else if(card instanceof MonopolyCard) {
				img = new ImageIcon(this.getClass().getResource("/card/monopolycard.png")).getImage();
			}
			else if(card instanceof RoadBuildingCard) {
				img = new ImageIcon(this.getClass().getResource("/card/roadbuildingcard.png")).getImage();
			}
			else if(card instanceof VictoryPointCard) {
				img = new ImageIcon(this.getClass().getResource("/card/victorypointcard.png")).getImage();
			}
			else if(card instanceof YearOfPlentyCard) {
				img = new ImageIcon(this.getClass().getResource("/card/yearofplentycard.png")).getImage();
			}
			else {
				img = new ImageIcon(this.getClass().getResource("/card/cardback.png")).getImage();
			}
		}
		else if(card instanceof LargestArmyCard) {
			img = new ImageIcon(this.getClass().getResource("/card/largestarmycard.png")).getImage();
		}
		else if(card instanceof LongestRoadCard) {
			img = new ImageIcon(this.getClass().getResource("/card/longestroadcard.png")).getImage();
		}
		else {
			img = new ImageIcon(this.getClass().getResource("/card/cardback.png")).getImage();
		}
		
		scaled_img = img.getScaledInstance(faceImageLabel.getWidth(), faceImageLabel.getHeight(), Image.SCALE_SMOOTH);
		faceImageLabel.setIcon(new ImageIcon(scaled_img));
		this.getContentPane().add(faceImageLabel);
		
		
	}
}
