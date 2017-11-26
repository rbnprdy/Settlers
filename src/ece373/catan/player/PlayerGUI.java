package ece373.catan.player;

import javax.swing.*;

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import ece373.catan.card.*;

public class PlayerGUI extends JPanel {
	private Player player;	
	
	public PlayerGUI(Player p){
		player = p;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buildGUI();
		setVisible(true);
	}
	
	public void buildGUI() {
		this.setSize((new Dimension (1000, 1600)));
		int i = 0;
		
		JPanel topPanel = new JPanel();
		
		Font font1 = new Font("SansSerif", Font.BOLD,30);
		
		JPanel infoPanel = new JPanel();
		
		JPanel playerNameAndColor = new JPanel();
		playerNameAndColor.setLayout(new BoxLayout(playerNameAndColor, BoxLayout.X_AXIS));
		JLabel playerNameLabel = new JLabel(player.getName() + " ");
		JLabel playerColorLabel = new JLabel("     ");
		playerColorLabel.setBackground(player.getColor());
		playerColorLabel.setAlignmentX(CENTER_ALIGNMENT);
		playerColorLabel.setMinimumSize(new Dimension(200,20));
		playerColorLabel.setOpaque(true);
		playerColorLabel.setFont(font1);
		playerNameLabel.setFont(font1);
		playerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		playerNameAndColor.add(playerNameLabel);
		playerNameAndColor.add(playerColorLabel);
		
		JLabel victoryPointsLabel = new JLabel("Victory Points: " + player.getVictoryPoints());
		victoryPointsLabel.setFont(font1);
		victoryPointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton doneButton = new JButton("Done");
		doneButton.setFont(font1);
		doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setPreferredSize(new Dimension(1000, 200));
		infoPanel.add(playerNameAndColor);
		infoPanel.add(victoryPointsLabel);
		infoPanel.add(doneButton);
		
		JPanel devCardPanel = new JPanel();
		JLayeredPane devCards = new JLayeredPane();
		JPanel largestArmyPanel = new JPanel();
		devCardPanel.setPreferredSize(new Dimension(1000, 300));
		devCardPanel.setLayout(new BoxLayout(devCardPanel, BoxLayout.X_AXIS));
		
		JPanel cardPanel = new JPanel();
		JLayeredPane sheepPanel = new JLayeredPane();
		JLayeredPane brickPanel = new JLayeredPane();
		JLayeredPane stonePanel = new JLayeredPane();
		JLayeredPane wheatPanel =  new JLayeredPane();
		JLayeredPane woodPanel = new JLayeredPane();
		cardPanel.setPreferredSize(new Dimension(1000, 300));
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));
		
		Dimension buttonDim = new Dimension(200, 200);
		JButton buildButton = new JButton("Build");
		JButton tradeButton = new JButton("Trade");
		buildButton.setFont(font1);
		tradeButton.setFont(font1);
		JButton developmentCardButton = new JButton();
		ImageIcon devImg = new ImageIcon(this.getClass().getResource("/player/devicon.png"));
		developmentCardButton.setIcon(devImg);
		buildButton.setPreferredSize(buttonDim);
		tradeButton.setPreferredSize(buttonDim);
		developmentCardButton.setPreferredSize(buttonDim);
		
		topPanel.setPreferredSize(new Dimension(1000,400));
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		topPanel.add(buildButton);
		topPanel.add(tradeButton);
		topPanel.add(developmentCardButton);
		
		i = 0;
		for(DevelopmentCard dc: player.getDevelopmentCards()) {
			CardGUI cardGUI = new CardGUI(dc);
			devCards.add(cardGUI);
			
		}
		
		if(player.getLargestArmyCard() != null) {
			CardGUI cardGUI = new CardGUI(player.getLargestArmyCard());
			largestArmyPanel.add(cardGUI);
		}
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING SHEEP CARDS
			if(rc.getType() == ResourceType.SHEEP) {
				CardGUI cardGUI = new CardGUI(rc);
				sheepPanel.add(cardGUI);
				cardGUI.setLocation(25, 0 - 50*(i));
				i = i + 1;
			}
		}
		i = 0;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING BRICK CARDS
			if(rc.getType() == ResourceType.BRICK) {
				CardGUI cardGUI = new CardGUI(rc);
				brickPanel.add(cardGUI);
				cardGUI.setLocation(25, 0 - 50*(i));				
				i = i + 1;
			}
		}
		i = 0;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING STONE CARDS
			if(rc.getType() == ResourceType.STONE) {
				CardGUI cardGUI = new CardGUI(rc);
				stonePanel.add(cardGUI);
				cardGUI.setLocation(25, 0 - 50*(i));		
				i = i + 1;
			}
		}
		i = 0;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING WHEAT CARDS
			if(rc.getType() == ResourceType.WHEAT) {
				CardGUI cardGUI = new CardGUI(rc);
				wheatPanel.add(cardGUI);
				cardGUI.setLocation(25,  0 - 50*(i));		
				i = i + 1;
			}
		}
		i = 0;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING WOOD CARDS
			if(rc.getType() == ResourceType.WOOD) {
				CardGUI cardGUI = new CardGUI(rc);
				woodPanel.add(cardGUI);
				cardGUI.setLocation(25, 0 - 50*(i));		
				i = i + 1;
			}
		}	
		
		cardPanel.add(sheepPanel);
		cardPanel.add(brickPanel);
		cardPanel.add(stonePanel);
		cardPanel.add(wheatPanel);
		cardPanel.add(woodPanel);
		devCardPanel.add(devCards);
		devCardPanel.add(largestArmyPanel);
		
		this.add(topPanel);
		this.add(infoPanel);	
		this.add(devCardPanel);
		this.add(cardPanel);
	}
}
