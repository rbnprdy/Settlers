package ece373.catan.player;

import javax.swing.*;

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.BoxLayout;
import ece373.catan.card.*;
import ece373.catan.game.*;

public class PlayerGUI extends JPanel {
	private Game game;
	private Player player;	
	private JButton doneButton;
	private JButton buildButton;
	private JButton tradeButton;
	private JButton developmentCardButton;
	private Font font1 = new Font("SansSerif", Font.BOLD,40);
	
	//BUILD MENU ITEMS
	private JFrame buildFrame;
	private JButton buildCancelButton;
	private JButton roadResourceLabel;
	private JButton settlementResourceLabel;
	private JButton cityResourceLabel;
	private JButton developmentCardResourceLabel;

	
	public PlayerGUI(Game g,Player p){
		player = p;
		game = g;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buildGUI();
		setVisible(true);
	}
	
	public void buildGUI() {
		this.setSize((new Dimension (1000, 1600)));
		int i = 0;
		
		JPanel topPanel = new JPanel();
		
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
		
		doneButton = new JButton("Done");
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
		buildButton = new JButton("Build");
		tradeButton = new JButton("Trade");
		buildButton.setFont(font1);
		tradeButton.setFont(font1);
		developmentCardButton = new JButton();
		ImageIcon devImg = new ImageIcon(this.getClass().getResource("/player/devicon.png"));
		developmentCardButton.setIcon(devImg);
		buildButton.setPreferredSize(buttonDim);
		tradeButton.setPreferredSize(buttonDim);
		developmentCardButton.setPreferredSize(buttonDim);
		
		buildButton.addActionListener(new ButtonListener());
		tradeButton.addActionListener(new ButtonListener());
		developmentCardButton.addActionListener(new ButtonListener());
		
		topPanel.setPreferredSize(new Dimension(1000,400));
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		topPanel.add(buildButton);
		topPanel.add(tradeButton);
		topPanel.add(developmentCardButton);
		
		
		i = 0;
		for(DevelopmentCard dc: player.getDevelopmentCards()) {
			CardGUI cardGUI = new CardGUI(dc);
			devCards.add(cardGUI);
			cardGUI.setLocation(0 + i*150, 0);
			i = i + 1;
			
		}
		
		if(player.getLargestArmyCard() != null) {
			CardGUI cardGUI = new CardGUI(player.getLargestArmyCard());
			largestArmyPanel.add(cardGUI);
		}
		i = 0;
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
		i = 0;;
		
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
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(doneButton)) {
				handleDone();
			}
			if(source.equals(buildButton)) {
				handleBuild();
			}
			if(source.equals(tradeButton)) {
				handleTrade();
			}
			if(source.equals(developmentCardButton)) {
				//handleDevelopmentCard;
			}
		}
		
		public void handleDone(){
			game.beginNextTurn();
		}
		
		public void handleBuild() {
			buildBuildGUI();
		}
		
		public void handleTrade() {
			//buildTradeGUI();
		}
		
		public void handleDevelopmentCard() {
			//buildDevelopmentCardGUI();
		}
	}
	
	private class BuildListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(buildCancelButton)) {
				buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
			}
			if(source.equals(roadResourceLabel)) {
				//game.getBoard().buildRoadWithPlayer(player);
				buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
			}
			if(source.equals(settlementResourceLabel)) {
				//game.getBoard().buildSettlementWithPlayer(player);
				buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
			}
			if(source.equals(cityResourceLabel)) {
				//game.getBoard().buildCityWithPlayer(player);
				buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
			}
			if(source.equals(developmentCardResourceLabel)) {
				//player.buildDevelopmentCard();
				buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
	
	public void buildBuildGUI() {
		buildFrame = new JFrame();
		buildFrame.setSize(new Dimension(800, 500));
		
		JPanel buildPanel = new JPanel();
		buildPanel.setSize(new Dimension(800, 500));
		buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		JLabel buildName = new JLabel("Building Costs     ");
		buildName.setFont(font1);
		buildCancelButton = new JButton("Cancel");
		buildCancelButton.setFont(font1);
		
		topPanel.add(buildName);
		topPanel.add(buildCancelButton);
		topPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		roadResourceLabel = new JButton();
		settlementResourceLabel = new JButton();
		cityResourceLabel = new JButton();
		developmentCardResourceLabel = new JButton();
		roadResourceLabel.setAlignmentX(CENTER_ALIGNMENT);
		settlementResourceLabel.setAlignmentX(CENTER_ALIGNMENT);
		cityResourceLabel.setAlignmentX(CENTER_ALIGNMENT);
		developmentCardResourceLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		Image roadCosts = new ImageIcon(this.getClass().getResource("/player/roadcosts.png")).getImage();
		Image settlementCosts = new ImageIcon(this.getClass().getResource("/player/settlementcosts.png")).getImage();
		Image cityCosts = new ImageIcon(this.getClass().getResource("/player/citycosts.png")).getImage();
		Image developmentCardCosts = new ImageIcon(this.getClass().getResource("/player/developmentcardcosts.png")).getImage();
		
		Image scaledRoadCosts = roadCosts.getScaledInstance(buildPanel.getWidth(), 100, Image.SCALE_SMOOTH);
		Image scaledSettlementCosts = settlementCosts.getScaledInstance(buildPanel.getWidth(), 100, Image.SCALE_SMOOTH);
		Image scaledCityCosts = cityCosts.getScaledInstance(buildPanel.getWidth(), 100, Image.SCALE_SMOOTH);
		Image scaledDevelopmentCardCosts = developmentCardCosts.getScaledInstance(buildPanel.getWidth(), 100, Image.SCALE_SMOOTH);
		
		roadResourceLabel.setIcon(new ImageIcon(scaledRoadCosts));
		settlementResourceLabel.setIcon(new ImageIcon(scaledSettlementCosts));
		cityResourceLabel.setIcon(new ImageIcon(scaledCityCosts));
		developmentCardResourceLabel.setIcon(new ImageIcon(scaledDevelopmentCardCosts));
		
		buildPanel.add(topPanel);
		buildPanel.add(roadResourceLabel);
		buildPanel.add(settlementResourceLabel);
		buildPanel.add(cityResourceLabel);
		buildPanel.add(developmentCardResourceLabel);
		buildPanel.setVisible(true);
		

		buildFrame.add(buildPanel);
		buildFrame.setLocationRelativeTo(null);
		buildFrame.setUndecorated(true);
		buildFrame.setVisible(true);		
	}
	
}
