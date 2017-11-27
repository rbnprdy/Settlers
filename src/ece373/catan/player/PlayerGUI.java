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
import java.awt.GridLayout;
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

	//TRADE MENU ITEMS
	private JFrame tradeFrame;
	private JButton tradeOk;
	private JButton tradeCancel;

	
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
	
	private class TradeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			if(source.equals(tradeCancel)) {
				tradeFrame.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
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
		buildCancelButton.addActionListener(new BuildListener());
		
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
		roadResourceLabel.addActionListener(new BuildListener());
		settlementResourceLabel.addActionListener(new BuildListener());
		cityResourceLabel.addActionListener(new BuildListener());
		developmentCardResourceLabel.addActionListener(new BuildListener());
		
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
	
	public void buildTradeGUI() {
		tradeFrame = new JFrame();
		tradeFrame.setSize(new Dimension(500, 400));
		tradeFrame.setLayout(new BoxLayout(tradeFrame, BoxLayout.Y_AXIS));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setSize(new Dimension(500, 100));
		JPanel tradeBoxPanel = new JPanel();
		tradeBoxPanel.setSize(new Dimension(500, 300));
		//JPanel cardIconPanel = new JPanel();
		//JPanel thisPlayerPanel = new JPanel();
		//JPanel thatPlayerPanel = new JPanel();
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		//cardIconPanel.setLayout(new BoxLayout(cardIconPanel, BoxLayout.X_AXIS));
		//thisPlayerPanel.setLayout(new BoxLayout(thisPlayerPanel, BoxLayout.X_AXIS));
		//thatPlayerPanel.setLayout(new BoxLayout(thatPlayerPanel, BoxLayout.X_AXIS));
		tradeBoxPanel.setLayout(new GridLayout(3,5));
		
		JLabel tradeTitle = new JLabel("Trade     ");
		tradeTitle.setFont(font1);
		tradeCancel = new JButton("Cancel");
		tradeCancel.setFont(font1);
		infoPanel.add(tradeTitle);
		infoPanel.add(tradeCancel);
		
		
		JLabel sheepLabel = new JLabel();
		JLabel brickLabel = new JLabel();
		JLabel stoneLabel = new JLabel();
		JLabel wheatLabel = new JLabel();
		JLabel woodLabel = new JLabel();
		
		Image sheepIcon = new ImageIcon(this.getClass().getResource("/player/sheepicon.png")).getImage();
		Image brickIcon = new ImageIcon(this.getClass().getResource("/player/brickicon.png")).getImage();
		Image stoneIcon = new ImageIcon(this.getClass().getResource("/player/stoneicon.png")).getImage();
		Image wheatIcon = new ImageIcon(this.getClass().getResource("/player/wheaticon.png")).getImage();
		Image woodIcon = new ImageIcon(this.getClass().getResource("/player/woodicon.png")).getImage();
		
		Image scaledSheepIcon = sheepIcon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Image scaledBrickIcon = brickIcon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);		
		Image scaledStoneIcon = stoneIcon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Image scaledWheatIcon = wheatIcon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Image scaledWoodIcon = woodIcon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		sheepLabel.setIcon(new ImageIcon(scaledSheepIcon));
		brickLabel.setIcon(new ImageIcon(scaledBrickIcon));
		stoneLabel.setIcon(new ImageIcon(scaledStoneIcon));
		wheatLabel.setIcon(new ImageIcon(scaledWheatIcon));
		woodLabel.setIcon(new ImageIcon(scaledWoodIcon));
		
		tradeBoxPanel.add(sheepLabel);
		tradeBoxPanel.add(brickLabel);
		
		JButton p1SheepBox = new JButton();
		JButton p1BrickBox = new JButton();
		JButton p1StoneBox = new JButton();
		JButton p1wheatBox = new JButton();
		JButton p1woodBox = new JButton();
		

		JButton p2SheepBox = new JButton();
		JButton p2BrickBox = new JButton();
		JButton p2StoneBox = new JButton();
		JButton p2wheatBox = new JButton();
		JButton p2woodBox = new JButton();
		
		
		
	}
	
}
