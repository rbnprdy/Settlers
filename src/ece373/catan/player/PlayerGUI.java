package ece373.catan.player;

import javax.swing.*;

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.GridLayout;
import ece373.catan.card.*;
import ece373.catan.game.*;


public class PlayerGUI extends JPanel {
	
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	public static Dimension size = toolkit.getScreenSize();
	private Game game;
	private Player player;	
	private JButton doneButton;
	private JButton buildButton;
	private JButton tradeButton;
	private JButton knightButton;
	private JButton roadBuildingButton;
	private JButton yearOfPlentyButton;
	private Image scaledKnightImage;
	private Image scaledRoadBuildingImage;
	private Image scaledYearOfPlentyImage;
	private Font font1 = new Font("SansSerif", Font.BOLD,40);
	private Font font2 = new Font("SansSerif", Font.PLAIN, 30);
	public static int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int playerGUIWidth = screenWidth/3;
	
	private JPanel topPanel;
	private JPanel cardPanel;
	private JPanel infoPanel;
	private JPanel devCardPanel;
	
	//BUILD MENU ITEMS
	private JFrame buildFrame;
	private JButton buildCancelButton;
	private JButton roadResourceLabel;
	private JButton settlementResourceLabel;
	private JButton cityResourceLabel;
	private JButton developmentCardResourceLabel;
	
	//TRADE MENU ITEMS
	private Player tradePlayer;
	private ResourceType p1ResourceType;
	private ResourceType p2ResourceType;
	private JFrame tradeFrame;


	
	private ArrayList<SlotButton> p1SlotButtons;
	private SlotButton p1SheepBox;
	private SlotButton p1BrickBox;
	private SlotButton p1StoneBox;
	private SlotButton p1WheatBox;
	private SlotButton p1WoodBox;

	private ArrayList<SlotButton> p2SlotButtons;
	private SlotButton p2SheepBox;
	private SlotButton p2BrickBox;
	private SlotButton p2StoneBox;
	private SlotButton p2WheatBox;
	private SlotButton p2WoodBox;

	private PlayerTradeButton tradeCancel;
	private PlayerTradeButton p1Button;
	private PlayerTradeButton p2Button;
	private PlayerTradeButton p3Button;
	
	//TRADE PROPOSITION PANEL ITEMS
	private JButton acceptButton;
	private JButton declineButton;
	private JFrame tradePropositionPanel;

	
	public PlayerGUI(Game g,Player p){
		
		//this.setOpaque(false);
		player = p;
		game = g;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buildGUI();
		setVisible(true);
	}
	
	public void buildGUI() {
		this.setSize((new Dimension (screenWidth/3, screenHeight)));
		
		player.calculateVictoryPoints();
		topPanel = new JPanel();
		topPanel.setOpaque(false);
		
		infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		
		JPanel playerNameAndColor = new JPanel();
		playerNameAndColor.setOpaque(false);
		playerNameAndColor.setLayout(new BoxLayout(playerNameAndColor, BoxLayout.X_AXIS));
		JLabel playerNameLabel = new JLabel(player.getName() + " ");
		playerNameLabel.setOpaque(false);
		JLabel playerColorLabel = new JLabel("     ");
		playerColorLabel.setOpaque(false);
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
		victoryPointsLabel.setOpaque(false);
		victoryPointsLabel.setFont(font1);
		victoryPointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		doneButton = new JButton("Done");
		doneButton.setFont(font1);
		doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		doneButton.addActionListener(new ButtonListener());
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setPreferredSize(new Dimension((int)this.getSize().getWidth(), (int)this.getSize().getHeight()/5));
		infoPanel.add(playerNameAndColor);
		infoPanel.add(victoryPointsLabel);
		infoPanel.add(doneButton);
				
		Dimension buttonDim = new Dimension((int)(this.getSize().getWidth()/4), (int)(this.getSize().getWidth()/4));
		buildButton = new JButton("Build");
		tradeButton = new JButton("Trade");
		buildButton.setFont(font1);
		tradeButton.setFont(font1);
		buildButton.setPreferredSize(buttonDim);
		tradeButton.setPreferredSize(buttonDim);
		
		buildButton.addActionListener(new ButtonListener());
		tradeButton.addActionListener(new ButtonListener());
		
		topPanel.setPreferredSize(new Dimension((int)this.getSize().getWidth(), (int)(this.getSize().getHeight()/4)));
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		topPanel.add(buildButton);
		topPanel.add(tradeButton);
		
		

		
		this.add(topPanel);
		this.add(infoPanel);	
		populateDevelopmentCards();
		populateResourceCards();
	}
	
	
	public void populateResourceCards() {
		int i;
		if(cardPanel != null) {
			cardPanel = null;
		}
		cardPanel = new JPanel();
		cardPanel.setOpaque(false);
		JLayeredPane sheepPanel = new JLayeredPane();
		JLayeredPane brickPanel = new JLayeredPane();
		JLayeredPane stonePanel = new JLayeredPane();
		JLayeredPane wheatPanel =  new JLayeredPane();
		JLayeredPane woodPanel = new JLayeredPane();
		cardPanel.setPreferredSize(new Dimension(1000, ((int)this.getHeight() - (int)topPanel.getSize().getHeight() - (int)devCardPanel.getSize().getHeight())));
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));
		
		i = 0;
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING SHEEP CARDS
			if(rc.getType() == ResourceType.SHEEP) {
				CardGUI cardGUI = new CardGUI(rc);
				sheepPanel.add(cardGUI);
				cardGUI.setLocation(25, (int)cardPanel.getSize().getHeight() + 50*(i));
				i = i + 1;
			}
		}
		i = 0;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING BRICK CARDS
			if(rc.getType() == ResourceType.BRICK) {
				CardGUI cardGUI = new CardGUI(rc);
				brickPanel.add(cardGUI);
				cardGUI.setLocation(25, (int)cardPanel.getSize().getHeight() + 50*(i));				
				i = i + 1;
			}
		}
		i = 0;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING STONE CARDS
			if(rc.getType() == ResourceType.STONE) {
				CardGUI cardGUI = new CardGUI(rc);
				stonePanel.add(cardGUI);
				cardGUI.setLocation(25, (int)cardPanel.getSize().getHeight() + 50*(i));		
				i = i + 1;
			}
		}
		i = 0;;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING WHEAT CARDS
			if(rc.getType() == ResourceType.WHEAT) {
				CardGUI cardGUI = new CardGUI(rc);
				wheatPanel.add(cardGUI);
				cardGUI.setLocation(25, (int)cardPanel.getSize().getHeight() + 50*(i));		
				i = i + 1;
			}
		}
		i = 0;
		
		for(ResourceCard rc: player.getResourceCards()) { // POPULATING WOOD CARDS
			if(rc.getType() == ResourceType.WOOD) {
				CardGUI cardGUI = new CardGUI(rc);
				woodPanel.add(cardGUI);
				cardGUI.setLocation(25, (int)cardPanel.getSize().getHeight() + 50*(i));		
				i = i + 1;
			}
		}	
		
		cardPanel.add(sheepPanel);
		cardPanel.add(brickPanel);
		cardPanel.add(stonePanel);
		cardPanel.add(wheatPanel);
		cardPanel.add(woodPanel);	
		this.add(cardPanel);
		return;
	}

	public void populateDevelopmentCards() {
		int i;
		devCardPanel = new JPanel();
		devCardPanel.setOpaque(false);
		JLayeredPane knightCards = new JLayeredPane();
		JLayeredPane roadBuildingCards = new JLayeredPane();
		JLayeredPane victoryPointCards = new JLayeredPane();
		JLayeredPane yearOfPlentyCards = new JLayeredPane();
		
		JLayeredPane largestArmyPanel = new JLayeredPane();
		JLayeredPane longestRoadPanel = new JLayeredPane();
		devCardPanel.setPreferredSize(new Dimension((int)this.getSize().getWidth(), (int)this.getSize().getHeight()/2));
		devCardPanel.setLayout(new BoxLayout(devCardPanel, BoxLayout.X_AXIS));
		
		i = 0;
		for(DevelopmentCard dc: player.getDevelopmentCards()) {
			if(dc instanceof KnightCard) {
				CardGUI cardGUI = new CardGUI(dc);
				if(i==0) {
					knightButton = new JButton();
					knightButton.setSize(new Dimension(playerGUIWidth/6, (int)((playerGUIWidth/6)*1.5)));
					scaledKnightImage = new ImageIcon(this.getClass().getResource("/card/knightcard.png")).getImage();
					scaledKnightImage = scaledKnightImage.getScaledInstance(playerGUIWidth/6, (int)((playerGUIWidth/6)*1.5), Image.SCALE_SMOOTH);
					knightButton.setIcon(new ImageIcon(scaledKnightImage));
					knightCards.add(knightButton);
					knightButton.addActionListener(new ButtonListener());
					knightButton.setLocation(0, (int)devCardPanel.getSize().getHeight() + 50*(i));
				}
				else {
					knightCards.add(cardGUI);
					cardGUI.setLocation(0, (int)devCardPanel.getSize().getHeight() + 50*(i));
				}
				i = i + 1;
			}
		}
		
		i=0;
		for(DevelopmentCard dc: player.getDevelopmentCards()) {
			if(dc instanceof RoadBuildingCard) {
				CardGUI cardGUI = new CardGUI(dc);
				roadBuildingCards.add(cardGUI);
				cardGUI.setLocation(0, (int)devCardPanel.getSize().getHeight() + 50*(i));
				i = i + 1;
			}
		}
		
		i=0;
		for(DevelopmentCard dc: player.getDevelopmentCards()) {
			if(dc instanceof VictoryPointCard) {
				CardGUI cardGUI = new CardGUI(dc);
				victoryPointCards.add(cardGUI);
				cardGUI.setLocation(0, (int)devCardPanel.getSize().getHeight() + 50*(i));
				i = i + 1;
			}
		}
		
		i=0;
		for(DevelopmentCard dc: player.getDevelopmentCards()) {
			if(dc instanceof YearOfPlentyCard) {
				CardGUI cardGUI = new CardGUI(dc);
				yearOfPlentyCards.add(cardGUI);
				cardGUI.setLocation(0, (int)devCardPanel.getSize().getHeight() + 50*(i));
				i = i + 1;
			}
		}
		
		
		if(player.getLargestArmyCard() != null) {
			CardGUI cardGUI = new CardGUI(player.getLargestArmyCard());
			largestArmyPanel.add(cardGUI);
		}
		
		if(player.getLongestRoadCard() != null) {
			CardGUI cardGUI = new CardGUI(player.getLongestRoadCard());
			longestRoadPanel.add(cardGUI);
		}
		
		knightCards.setSize((int)(devCardPanel.getSize().getWidth()/6),(int)(devCardPanel.getSize().getHeight()));
		victoryPointCards.setSize((int)(devCardPanel.getSize().getWidth()/6),(int)(devCardPanel.getSize().getHeight()));
		roadBuildingCards.setSize((int)(devCardPanel.getSize().getWidth()/6),(int)(devCardPanel.getSize().getHeight()));
		yearOfPlentyCards.setSize((int)(devCardPanel.getSize().getWidth()/6),(int)(devCardPanel.getSize().getHeight()));
		largestArmyPanel.setSize((int)(devCardPanel.getSize().getWidth()/6),(int)(devCardPanel.getSize().getHeight()));
		longestRoadPanel.setSize((int)(devCardPanel.getSize().getWidth()/6),(int)(devCardPanel.getSize().getHeight()));
		devCardPanel.add(knightCards);
		devCardPanel.add(victoryPointCards);
		devCardPanel.add(roadBuildingCards);
		devCardPanel.add(yearOfPlentyCards);
		devCardPanel.add(largestArmyPanel);
		devCardPanel.add(longestRoadPanel);
		this.add(devCardPanel);
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(player.areButtonsDisabled() == true) {
				return;
			}
			
			if(source.equals(doneButton)) {
				handleDone();
			}
			if(source.equals(buildButton)) {
				handleBuild();
			}
			if(source.equals(tradeButton)) {
				handleTrade();
			}
			if(source.equals(knightButton)) {
				handleKnight();
			}
		}
		
		public void handleDone(){
			game.beginNextTurn();
		}
		
		public void handleBuild() {
			buildBuildGUI();
		}
		
		public void handleTrade() {
			buildTradeGUI();
		}
		
		public void handleKnight() {
			int input = JOptionPane.showOptionDialog(null, "Press OK to move the robber. Each knight card can only be used once.", "Knight Card", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

			if(input == JOptionPane.OK_OPTION)
			{
				for(DevelopmentCard dc: player.getDevelopmentCards()) {
					if(dc instanceof KnightCard) {
						if(((KnightCard) dc).getBeenPlayed() == false){
							game.getBoard().moveRobber();
							((KnightCard) dc).setBeenPlayed(true);
							return;
						}
					}
				}
				JOptionPane.showMessageDialog(null, "You have no unused Knight cards.");
			}

		}
		
		public void handleRoadBuilding() {
			return;
		}
		public void handleYearOfPlenty() {
			return;
		}
		
	}
	
	private class BuildListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(buildCancelButton)) {
				buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
				return;
			}
			if(source.equals(roadResourceLabel)) {
				if(game.getBoard().canBuildRoads(player)) {
					if(player.hasCard(ResourceType.WOOD) && player.hasCard(ResourceType.BRICK)) {
						player.removeResourceCardOfType(ResourceType.WOOD);
						player.removeResourceCardOfType(ResourceType.BRICK);
						game.getBoard().buildRoadWithPlayer(player);
						game.updatePlayerGUI();
						buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
						return;
					}
					else {
						JOptionPane.showMessageDialog(null, "You do not have the proper materials.");
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You have no available spaces to build a road.");
					return;
				}

			}
			if(source.equals(settlementResourceLabel)) {
				if (game.getBoard().canBuildSettlements(player)){
					if(player.hasCard(ResourceType.WOOD) && player.hasCard(ResourceType.BRICK) && player.hasCard(ResourceType.WHEAT) && player.hasCard(ResourceType.SHEEP)) {
						player.removeResourceCardOfType(ResourceType.WOOD);
						player.removeResourceCardOfType(ResourceType.BRICK);
						player.removeResourceCardOfType(ResourceType.WHEAT);
						player.removeResourceCardOfType(ResourceType.SHEEP);
						game.getBoard().buildSettlementWithPlayer(player);
						game.updatePlayerGUI();
						buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
						return;
					}
					else {
						JOptionPane.showMessageDialog(null, "You do not have the proper materials.");
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You have no available spaces to build a settlement.");
					return;
				}

			}
			if(source.equals(cityResourceLabel)) {
				if(game.getBoard().canBuildCities(player)) {
					if(player.hasCard(ResourceType.WHEAT) && player.hasCard(ResourceType.STONE)) { //CHECK FIRST WHEAT AND STONE
						player.removeResourceCardOfType(ResourceType.WHEAT);
						player.removeResourceCardOfType(ResourceType.STONE);
						if(player.hasCard(ResourceType.WHEAT) && player.hasCard(ResourceType.STONE)) { // CHECK SECOND WHEAT AND STONE
							player.removeResourceCardOfType(ResourceType.WHEAT);
							player.removeResourceCardOfType(ResourceType.STONE);
							if(player.hasCard(ResourceType.STONE)) { 									//CHECKING FINAL STONE
								player.removeResourceCardOfType(ResourceType.STONE);
								game.getBoard().buildCityWithPlayer(player);
								game.updatePlayerGUI();
								buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
							}
							else {
								JOptionPane.showMessageDialog(null, "You do not have the proper materials.");
								player.addCard(new ResourceCard(ResourceType.WHEAT));
								player.addCard(new ResourceCard(ResourceType.WHEAT));
								player.addCard(new ResourceCard(ResourceType.STONE));
								player.addCard(new ResourceCard(ResourceType.STONE));
								return;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "You do not have the proper materials.");
							player.addCard(new ResourceCard(ResourceType.WHEAT));
							player.addCard(new ResourceCard(ResourceType.STONE));						
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "You do not have the proper materials.");
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You do not have a settlement to build on.");
					return;
				}
			}
			if(source.equals(developmentCardResourceLabel)) {
				if(player.hasCard(ResourceType.WHEAT) && player.hasCard(ResourceType.STONE) && player.hasCard(ResourceType.SHEEP)) {
					player.removeResourceCardOfType(ResourceType.WHEAT);
					player.removeResourceCardOfType(ResourceType.STONE);
					player.removeResourceCardOfType(ResourceType.SHEEP);
					game.drawDevelopmentCard(player);  //IMPLEMENTED IN GAME CLASS
					game.updatePlayerGUI();
					buildFrame.dispatchEvent(new WindowEvent(buildFrame, WindowEvent.WINDOW_CLOSING));
					return;
				}
				else {
					JOptionPane.showMessageDialog(null, "You do not have the proper materials.");
					return;
				}
			}
			return;
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
	
	private class PlayerTradeButton extends JButton{
		private Player buttonPlayer;
		
		public PlayerTradeButton(String title, Player p) {
			setText(title);
			buttonPlayer = p;
		}
		
		public Player getPlayer() {
			return buttonPlayer;
		}
		
	}
	
	private class SlotButton extends JButton{
		private ResourceType buttonType;
		
		public SlotButton(ResourceType rt){
			buttonType = rt;
		}
		
		public ResourceType getButtonType() {
			return buttonType;
		}
	}
	
	public void buildTradeGUI() {
		int numPlayers = game.getPlayers().size();
		int i;
		
		tradeFrame = new JFrame();
		Dimension slotButtonDim = new Dimension(100,100);

		tradeFrame.setSize(new Dimension(500, 500));
		tradeFrame.setLayout(new BoxLayout(tradeFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setSize(new Dimension(500, 100));
		JPanel tradeBoxPanel = new JPanel();
		tradeBoxPanel.setSize(new Dimension(500, 300));
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));

		tradeBoxPanel.setLayout(new GridLayout(3,5));
		
		JLabel tradeTitle = new JLabel("Trade     ");
		tradeTitle.setFont(font1);
		tradeCancel = new PlayerTradeButton("Cancel", null);
		tradeCancel.setFont(font1);
		tradeCancel.addActionListener(new TradeSendListener());
		infoPanel.add(tradeTitle);
		infoPanel.add(tradeCancel);
		
		
		JLabel sheepLabel = new JLabel();
		JLabel brickLabel = new JLabel();
		JLabel stoneLabel = new JLabel();
		JLabel wheatLabel = new JLabel();
		JLabel woodLabel = new JLabel();
		
		Image sheepIcon = new ImageIcon(this.getClass().getResource("/player/sheepicon.png")).getImage();
		Image brickIcon = new ImageIcon(this.getClass().getResource("/player/brickicon.png")).getImage();
		Image stoneIcon = new ImageIcon(this.getClass().getResource("/player/rockicon.png")).getImage();
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
		tradeBoxPanel.add(stoneLabel);
		tradeBoxPanel.add(wheatLabel);
		tradeBoxPanel.add(woodLabel);
		
		p1SlotButtons = new ArrayList<SlotButton>();
		p2SlotButtons = new ArrayList<SlotButton>();
		
		p1SheepBox = new SlotButton(ResourceType.SHEEP); 
		p1BrickBox = new SlotButton(ResourceType.BRICK);
		p1StoneBox = new SlotButton(ResourceType.STONE);
		p1WheatBox = new SlotButton(ResourceType.WHEAT);
		p1WoodBox = new SlotButton(ResourceType.WOOD);
		p2SheepBox = new SlotButton(ResourceType.SHEEP);
		p2BrickBox = new SlotButton(ResourceType.BRICK);
		p2StoneBox = new SlotButton(ResourceType.STONE);
		p2WheatBox = new SlotButton(ResourceType.WHEAT);
		p2WoodBox = new SlotButton(ResourceType.WOOD);
		
		p1SlotButtons.add(p1SheepBox);
		p1SlotButtons.add(p1BrickBox);
		p1SlotButtons.add(p1StoneBox);
		p1SlotButtons.add(p1WheatBox);
		p1SlotButtons.add(p1WoodBox);
		
		for(JButton slotButton: p1SlotButtons) {
			slotButton.addActionListener(new TradeSlotListener());
		}
		
		p2SlotButtons.add(p2SheepBox);
		p2SlotButtons.add(p2BrickBox);
		p2SlotButtons.add(p2StoneBox);
		p2SlotButtons.add(p2WheatBox);
		p2SlotButtons.add(p2WoodBox);
		
		for(JButton slotButton: p2SlotButtons) {
			slotButton.addActionListener(new TradeSlotListener());
		}
		
		tradeBoxPanel.add(p1SheepBox);
		tradeBoxPanel.add(p1BrickBox);
		tradeBoxPanel.add(p1StoneBox);
		tradeBoxPanel.add(p1WheatBox);
		tradeBoxPanel.add(p1WoodBox);
		tradeBoxPanel.add(p2SheepBox);
		tradeBoxPanel.add(p2BrickBox);
		tradeBoxPanel.add(p2StoneBox);
		tradeBoxPanel.add(p2WheatBox);
		tradeBoxPanel.add(p2WoodBox);
		
		
		
		p1SheepBox.setBackground(Color.WHITE);
		p1BrickBox.setBackground(Color.WHITE);
		p1StoneBox.setBackground(Color.WHITE);
		p1WheatBox.setBackground(Color.WHITE);
		p1WoodBox.setBackground(Color.WHITE);
		p2SheepBox.setBackground(Color.WHITE);
		p2BrickBox.setBackground(Color.WHITE);
		p2StoneBox.setBackground(Color.WHITE);
		p2WheatBox.setBackground(Color.WHITE);
		p2WoodBox.setBackground(Color.WHITE);
		
		
		p1SheepBox.setSize(slotButtonDim);
		
		JPanel playerSelectPanel = new JPanel();
		playerSelectPanel.setSize(new Dimension(500,100));
		playerSelectPanel.setLayout(new BoxLayout(playerSelectPanel, BoxLayout.X_AXIS));
		
		for(i = 0; i<numPlayers; ++i) {
			if (p1Button == null) {
				if(!game.getPlayers().get(i).equals(player)) {
					p1Button = new PlayerTradeButton(game.getPlayers().get(i).getName(), game.getPlayers().get(i));
				}
			}
			else if (p2Button == null) {
				if(!game.getPlayers().get(i).equals(player) && !game.getPlayers().get(i).equals(p1Button.getPlayer())) {
					p2Button = new PlayerTradeButton(game.getPlayers().get(i).getName(), game.getPlayers().get(i));
				}
			}
			else if (p3Button == null && !game.getPlayers().get(i).equals(p1Button.getPlayer()) && !game.getPlayers().get(i).equals(p2Button.getPlayer())) {
				if(!game.getPlayers().get(i).equals(player)) {
					p3Button = new PlayerTradeButton(game.getPlayers().get(i).getName(), game.getPlayers().get(i));
				}
			}
		}
				
		if(p1Button != null) {
			p1Button.setSize(slotButtonDim);
			p1Button.setFont(font1);
			p1Button.addActionListener(new TradeSendListener());
			playerSelectPanel.add(p1Button);
		}
		if(p2Button != null) {
			p2Button.setSize(slotButtonDim);
			p2Button.setFont(font1);
			p2Button.addActionListener(new TradeSendListener());
			playerSelectPanel.add(p2Button);
		}
		if(p3Button != null) {
			p3Button.setSize(slotButtonDim);
			p3Button.setFont(font1);
			p3Button.addActionListener(new TradeSendListener());
			playerSelectPanel.add(p3Button);
		}
		
		
		tradeFrame.add(infoPanel);
		tradeFrame.add(tradeBoxPanel);
		tradeFrame.add(playerSelectPanel);
		
		tradeFrame.setLocationRelativeTo(null);
		tradeFrame.setUndecorated(true);
		tradeFrame.setVisible(true);
		
	}
	
	private class TradeSlotListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(p1SlotButtons.contains(source)) {
				for(JButton slotButton: p1SlotButtons) {
						if(source.equals(slotButton)) {
							slotButton.setBackground(Color.BLACK);
						}
						else {
							slotButton.setBackground(Color.WHITE);
						}
				}
			}
			else if(p2SlotButtons.contains(source)) {
				for(JButton slotButton: p2SlotButtons) {
					if(source.equals(slotButton)) {
						slotButton.setBackground(Color.BLACK);
					}
					else {
						slotButton.setBackground(Color.WHITE);
					}
				}
			}
		}
	}
	
	private class TradeSendListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			PlayerTradeButton source = (PlayerTradeButton)(e.getSource());
			
			if(source.equals(tradeCancel)) {
				tradeFrame.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
				return;
			}
			
			for(SlotButton slotButton: p1SlotButtons) {
				if(slotButton.getBackground().equals(Color.BLACK)) {
					p1ResourceType = slotButton.getButtonType();
				}
			}
			
			if (p1ResourceType == null) {
				JOptionPane.showMessageDialog(null, "Please select a card to trade to the other player");
				return;
			}
			
			for(SlotButton slotButton: p2SlotButtons) {
				if(slotButton.getBackground().equals(Color.BLACK)) {
					p2ResourceType = slotButton.getButtonType();
				}
			}
			
			if (p2ResourceType == null) {
				JOptionPane.showMessageDialog(null, "Please select a card to receive from the other player");
				return;
			}
			
			if(!player.hasCard(p1ResourceType)){
				JOptionPane.showMessageDialog(null, "You do not have a card of this type");					
				return;
			}
			
			
			if(!source.getPlayer().hasCard(p2ResourceType)) {
				JOptionPane.showMessageDialog(null, "They do not have a card of this type");	
				return;
			}

			int input = JOptionPane.showOptionDialog(null, "Please pass to " + source.getPlayer().getName(), "Trade Proposed", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

			if(input == JOptionPane.OK_OPTION)
			{
				tradePlayer = source.getPlayer();
				handleTradeProposition(p1ResourceType, p2ResourceType);
				game.updatePlayerGUI();
			}

			return;
		}
	}
	
	private class TradeProposalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(acceptButton)) {
				player.makeTrade(tradePlayer, p1ResourceType, p2ResourceType);
				tradeFrame.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
				tradePropositionPanel.dispatchEvent(new WindowEvent(tradePropositionPanel, WindowEvent.WINDOW_CLOSING));
				game.updatePlayerGUI();
				JOptionPane.showMessageDialog(null, "Trade Completed! Please pass back to " + player.getName());
			}
			if(source.equals(declineButton)) {
				tradePropositionPanel.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
				JOptionPane.showMessageDialog(null, "Trade Declined. Please pass back to " + player.getName());		
			}
		}
	}
	
	
	public void handleTradeProposition(ResourceType p1Type, ResourceType p2Type) {
		
		if (tradePropositionPanel != null) {
			tradePropositionPanel.dispatchEvent(new WindowEvent(tradeFrame, WindowEvent.WINDOW_CLOSING));
		}
		tradePropositionPanel = new JFrame();
		tradePropositionPanel.setAlwaysOnTop(true);
		tradePropositionPanel.setLayout(new BoxLayout(tradePropositionPanel.getContentPane(), BoxLayout.Y_AXIS));
		tradePropositionPanel.setSize(new Dimension(800, 200));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setSize(new Dimension((int)(tradePropositionPanel.getSize().getWidth()/3), (int)(tradePropositionPanel.getSize().getWidth()/3)));
		JLabel tradePropositionLabel = new JLabel(player.getName() + " wants to trade their " + p1Type.toString().toLowerCase() + " card for your " +
				p2Type.toString().toLowerCase() + " card");
		tradePropositionLabel.setFont(font2);
		tradePropositionLabel.setAlignmentX(CENTER_ALIGNMENT);
		acceptButton = new JButton("Accept");
		declineButton = new JButton("Decline");
		
		acceptButton.setFont(font2);
		declineButton.setFont(font2);
		acceptButton.addActionListener(new TradeProposalListener());
		declineButton.addActionListener(new TradeProposalListener());
		
		tradePropositionPanel.add(tradePropositionLabel);
		buttonPanel.add(acceptButton);
		buttonPanel.add(declineButton);
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		tradePropositionPanel.add(buttonPanel);
		
		tradePropositionPanel.setLocationRelativeTo(null);
		tradePropositionPanel.setUndecorated(true);
		tradePropositionPanel.setVisible(true);
			
	}
	
}
