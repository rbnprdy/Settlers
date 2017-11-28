package ece373.catan.board;

import javax.swing.*;

import ece373.catan.card.*;
import ece373.catan.game.*;
import ece373.catan.player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class BoardGUI extends JPanel {

//	private final int WIDTH = 1200;
//	private final int HEIGHT = 800;
	private final int edgeThickness = 8; 

	private final int tileRadius = 90;
	private final double circleRadiusScalingFactor = 1.5;
	
	private Game game;

	private ArrayList<NodeGUI> nodeGUIs;
	private ArrayList<EdgeGUI> edgeGUIs;

	private ArrayList<Tile> tiles;
	
	private ArrayList<JButton> buttons;

	private Board board;

	private ArrayList<Node> availableNodes;
	private boolean displayAvailableNodes;
	
	private ArrayList<Edge> availableEdges;
	private boolean displayAvailableEdges;
	
	private boolean displayAvailableTilesForRobber;
	
	private boolean takeResources;

	private double xIncrement;
	private double yIncrement;
	
	private ArrayList<Integer> numberTokens;
	private ArrayList<ResourceType> resourceTokens;

	public BoardGUI(Board b, Game g) {
		
		this.setLayout(null);
		this.setBackground(null);
		
		this.game = g;
		this.board = b;
		
		numberTokens = new ArrayList<Integer>(Arrays.asList(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
		Collections.shuffle(numberTokens);
		
		resourceTokens = new ArrayList<ResourceType>(Arrays.asList(
				ResourceType.BRICK, ResourceType.BRICK, ResourceType.BRICK,
				ResourceType.STONE, ResourceType.STONE, ResourceType.STONE,
				ResourceType.WHEAT, ResourceType.WHEAT, ResourceType.WHEAT, ResourceType.WHEAT,
				ResourceType.SHEEP, ResourceType.SHEEP, ResourceType.SHEEP, ResourceType.SHEEP,
				ResourceType.WOOD, ResourceType.WOOD, ResourceType.WOOD, ResourceType.WOOD,
				null
				));
		Collections.shuffle(resourceTokens);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(new Dimension( (int)(screenSize.getWidth()*0.75), (int) screenSize.getHeight()));

		tiles = new ArrayList<Tile>();
		nodeGUIs = new ArrayList<NodeGUI>();
		edgeGUIs = new ArrayList<EdgeGUI>();
		buttons = new ArrayList<JButton>();
		
		availableNodes = new ArrayList<Node>();
		displayAvailableNodes = false;
		
		availableEdges = new ArrayList<Edge>();
		displayAvailableEdges = false;
		
		displayAvailableTilesForRobber = false;
		
		takeResources = false;

		xIncrement = Math.sin((60*Math.PI)/180)*tileRadius - 0.5;
		yIncrement = Math.cos((60*Math.PI)/180)*tileRadius;

		setupTiles();
		setupNodes();
		setupEdges();

	}

	public Board getBoard() {
		return board;
	}
	
	public void setTakeResources(boolean b) {
		takeResources = b;
	}
	
	public void showAvailableNodes(ArrayList<Node> nodes) {
		availableNodes = nodes;
		displayAvailableNodes = true;
		this.repaint();
	}
	
	public void showAvailableEdges(ArrayList<Edge> edges) {
		availableEdges = edges;
		displayAvailableEdges = true;
		this.repaint();
	}
	
	public void showAvailableTilesForRobber() {
		displayAvailableTilesForRobber = true;
		this.repaint();
	}
	
	public void dealResourceCardsForRoll(int roll) {
		for(Tile t: tiles) {
			// if this tile is matching the roll
			if (t.getNumber() == roll && t.hasRobber() == false) {
				// for each node surrounding the tile
				for (Node n: t.getNodes()) {
					// if a player has built on this node
					if (n.getPlayer() != null) {
						// deal card
						n.getPlayer().addResourceCardOfType(t.getResourceType());
						// if the building is a city, deal another card
						if (n.getCity() != null) {
							n.getPlayer().addResourceCardOfType(t.getResourceType());
						}
					}
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;

		g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

		// Paint the tiles
		for (Tile t: tiles) {
			if (t.getResourceType() == null) {
				g2d.setColor(new Color(0xeec867));
			} else {
				switch (t.getResourceType()) {
					case BRICK:
						g2d.setColor(new Color(0xb75e1f));
						break;
					case SHEEP:
						g2d.setColor(new Color(0xbbd666));
						break;
					case STONE:
						g2d.setColor(new Color(0x929a89));
						break;
					case WHEAT:
						g2d.setColor(new Color(0xeba237));
						break;
					case WOOD:
						g2d.setColor(new Color(0x117645));
						break;
					default:
						g2d.setColor(Color.black);
						break;
				}
			}

			g2d.fill(t);
			g2d.setColor(Color.black);
			g2d.draw(t);

			// Draw the circle
			Ellipse2D circle = new Ellipse2D.Double(t.getCenter().getX() - tileRadius / (circleRadiusScalingFactor*2), t.getCenter().getY() - tileRadius / (circleRadiusScalingFactor*2), tileRadius / circleRadiusScalingFactor, tileRadius / circleRadiusScalingFactor);
			g2d.setColor(new Color(0xead4b8));

			g2d.fill(circle);
			
			if (t.hasRobber()) {
				// Draw the robber
				Ellipse2D robber = new Ellipse2D.Double(t.getCenter().getX() - tileRadius / (circleRadiusScalingFactor*2) + 5, t.getCenter().getY() - tileRadius / (circleRadiusScalingFactor*2) + 5, tileRadius / circleRadiusScalingFactor - 10, tileRadius / circleRadiusScalingFactor - 10);
				g2d.setColor(Color.BLACK);

				g2d.fill(robber);
			}

			// Draw then number
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, (int) (tileRadius / (circleRadiusScalingFactor * 2))));
			g2d.setColor(Color.black);
			// Center the number
			FontMetrics metrics = g2d.getFontMetrics(g.getFont());
			int stringX = (int) (circle.getX() + (circle.getWidth() - metrics.stringWidth(t.getNumber().toString())) / 2);
			int stringY = (int) (circle.getY() + ((circle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

			g2d.drawString(t.getNumber().toString(), stringX, stringY);

		}

		// Paint the node GUIs if they are populated
		for (NodeGUI n: nodeGUIs) {
			if (n.getNode().getSettlement() != null) {
				g2d.setColor(n.getNode().getPlayer().getColor());
				g2d.fill(n.getCircle());
			} else if (n.getNode().getCity() != null) {
				g2d.setColor(n.getNode().getPlayer().getColor());
				g2d.fill(n.getSquare());
			}
		}
		
		// Paint the edge GUIs if they are populated
		g2d.setStroke(new BasicStroke(edgeThickness));
		for (EdgeGUI e: edgeGUIs) {
			if (e.getEdge().getRoad() != null) {
				g2d.setColor(e.getEdge().getPlayer().getColor());
				g2d.drawLine((int) e.getP1().getX(), (int) e.getP1().getY(), (int) e.getP2().getX(), (int) e.getP2().getY());
			}
		}

		// Display available nodes if applicable
		if (displayAvailableNodes) {
			for (Node n: availableNodes) {
				if (n.getGUI() != null) {
					JButton b1 = new JButton();
					b1.setLayout(null);
					b1.setBounds(n.getGUI().getCircle().getBounds());
					b1.addActionListener(new NodeButtonListener(n));
					add(b1);
					buttons.add(b1);
				}
			}
			displayAvailableNodes = false;
		}

		// Display available edges if applicable
		if (displayAvailableEdges) {
			for (Edge e: availableEdges) {
				if (e.getGUI() != null) {
					JButton b1 = new JButton();
					b1.setLayout(null);
					b1.setBounds((int) e.getGUI().getCenter().getX() - 10, (int) e.getGUI().getCenter().getY() - 10, 20, 20);
					b1.addActionListener(new EdgeButtonListener(e));
					add(b1);
					buttons.add(b1);
				}
			}
			displayAvailableEdges = false;
		}
		
		if (this.displayAvailableTilesForRobber) {
			for (Tile t: tiles) {
				t.setRobber(false);
				JButton b1 = new JButton();
				b1.setLayout(null);
				b1.setBounds((int) t.getCenter().getX() - 10, (int) t.getCenter().getY() - 10, 20, 20);
				b1.addActionListener(new TileButtonListener(t));
				add(b1);
				buttons.add(b1);
			}
			displayAvailableTilesForRobber = false;
		}
	}
	
	// Gives the tiles the correct coordinates so that they can be drawn when necessary
	private void setupTiles() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int WIDTH = (int) (0.75*screenSize.getWidth());
		int HEIGHT = (int) (screenSize.getHeight());
		int tokenNumber = 0;
		int resourceNumber = 0;
		
		tiles.clear();

		Point origin = new Point(this.getWidth() / 2, this.getHeight() / 2);

		// Set up the coordinates for the tiles row by row
		origin.setLocation(WIDTH / 2 - 2*xIncrement, HEIGHT / 2 - 6*yIncrement);
		for (int i = 0; i < 3; i++) {

			Tile t;
			// If this is the sand token, number is zero
			if (resourceTokens.get(resourceNumber) == null) {
				t = new Tile(origin, tileRadius, 0, resourceTokens.get(resourceNumber));
				t.setRobber(true);
				resourceNumber++;
			} else {
				t = new Tile(origin, tileRadius, numberTokens.get(tokenNumber), resourceTokens.get(resourceNumber));
				tokenNumber++;
				resourceNumber++;
			}
			
			tiles.add(t);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement, HEIGHT / 2 - 3*yIncrement);
		for (int i = 0; i < 4; i++) {

			Tile t;
			// If this is the sand token, number is zero
			if (resourceTokens.get(resourceNumber) == null) {
				t = new Tile(origin, tileRadius, 0, resourceTokens.get(resourceNumber));
				t.setRobber(true);
				resourceNumber++;
			} else {
				t = new Tile(origin, tileRadius, numberTokens.get(tokenNumber), resourceTokens.get(resourceNumber));
				tokenNumber++;
				resourceNumber++;
			}
			
			tiles.add(t);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 4*xIncrement, HEIGHT / 2);
		for (int i = 0; i < 5; i++) {

			Tile t;
			// If this is the sand token, number is zero
			if (resourceTokens.get(resourceNumber) == null) {
				t = new Tile(origin, tileRadius, 0, resourceTokens.get(resourceNumber));
				t.setRobber(true);
				resourceNumber++;
			} else {
				t = new Tile(origin, tileRadius, numberTokens.get(tokenNumber), resourceTokens.get(resourceNumber));
				tokenNumber++;
				resourceNumber++;
			}
			
			tiles.add(t);
			
			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement, HEIGHT / 2 + 3*yIncrement);
		for (int i = 0; i < 4; i++) {

			Tile t;
			// If this is the sand token, number is zero
			if (resourceTokens.get(resourceNumber) == null) {
				t = new Tile(origin, tileRadius, 0, resourceTokens.get(resourceNumber));
				t.setRobber(true);
				resourceNumber++;
			} else {
				t = new Tile(origin, tileRadius, numberTokens.get(tokenNumber), resourceTokens.get(resourceNumber));
				tokenNumber++;
				resourceNumber++;
			}
			
			tiles.add(t);
			
			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 2*xIncrement, HEIGHT / 2 + 6*yIncrement);
		for (int i = 0; i < 3; i++) {

			Tile t;
			// If this is the sand token, number is zero
			if (resourceTokens.get(resourceNumber) == null) {
				t = new Tile(origin, tileRadius, 0, resourceTokens.get(resourceNumber));
				t.setRobber(true);
				resourceNumber++;
			} else {
				t = new Tile(origin, tileRadius, numberTokens.get(tokenNumber), resourceTokens.get(resourceNumber));
				tokenNumber++;
				resourceNumber++;
			}
			
			tiles.add(t);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY()); 
		}

	}

	// Gives the nodes the correct coordinates so that they can be drawn when necessary
	private void setupNodes() {
		nodeGUIs.clear();
		
		// Row 1
		for (int i = 0; i < 3; i++) {
			Point p = tiles.get(i).getPoints()[0];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i).setNode(0, n.getNode());
		}

		// Row 2
		Point p3 = tiles.get(0).getPoints()[5];
		NodeGUI n3 = new NodeGUI(board.getNodes().get(3), p3);
		nodeGUIs.add(n3);
		tiles.get(0).setNode(5, n3.getNode());
		
		for (int i = 4; i < 6; i++) {
			Point p = tiles.get(i - 4).getPoints()[1];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 4).setNode(1, n.getNode());
			tiles.get(i - 3).setNode(5, n.getNode());
		}
		
		Point p6 = tiles.get(2).getPoints()[1];
		NodeGUI n6 = new NodeGUI(board.getNodes().get(6), p6);
		nodeGUIs.add(n6);
		tiles.get(2).setNode(1, n6.getNode());
		
		// Row 3
		Point p7 = tiles.get(0).getPoints()[4];
		NodeGUI n7 = new NodeGUI(board.getNodes().get(7), p7);
		nodeGUIs.add(n7);
		tiles.get(0).setNode(4, n7.getNode());
		tiles.get(3).setNode(0, n7.getNode());
		
		for (int i = 8; i < 10; i++) {
			Point p = tiles.get(i - 8).getPoints()[2];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 8).setNode(2, n.getNode());
			tiles.get(i - 7).setNode(4, n.getNode());
			tiles.get(i - 4).setNode(0, n.getNode());
		}
		
		Point p10 = tiles.get(2).getPoints()[2];
		NodeGUI n10 = new NodeGUI(board.getNodes().get(10), p10);
		nodeGUIs.add(n10);
		tiles.get(2).setNode(2, n10.getNode());
		tiles.get(6).setNode(0, n10.getNode());
		
		// Row 4
		Point p11 = tiles.get(3).getPoints()[5];
		NodeGUI n11 = new NodeGUI(board.getNodes().get(11), p11);
		nodeGUIs.add(n11);
		tiles.get(3).setNode(5, n11.getNode());
		
		for (int i = 12; i < 15; i++) {
			Point p = tiles.get(i - 9).getPoints()[1];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 12).setNode(3, n.getNode());
			tiles.get(i - 9).setNode(1, n.getNode());
			tiles.get(i - 8).setNode(5, n.getNode());
		}
		
		Point p15 = tiles.get(6).getPoints()[1];
		NodeGUI n15 = new NodeGUI(board.getNodes().get(15), p15);
		nodeGUIs.add(n15);
		tiles.get(6).setNode(1, n15.getNode());
		
		// Row 5
		Point p16 = tiles.get(3).getPoints()[4];
		NodeGUI n16 = new NodeGUI(board.getNodes().get(16), p16);
		nodeGUIs.add(n16);
		tiles.get(3).setNode(4, n16.getNode());
		tiles.get(7).setNode(0, n16.getNode());
		
		for (int i = 17; i < 20; i++) {
			Point p = tiles.get(i - 14).getPoints()[2];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 14).setNode(2, n.getNode());
			tiles.get(i - 13).setNode(4, n.getNode());
			tiles.get(i - 9).setNode(0, n.getNode());
		}
		
		Point p20 = tiles.get(6).getPoints()[2];
		NodeGUI n20 = new NodeGUI(board.getNodes().get(20), p20);
		nodeGUIs.add(n20);
		tiles.get(6).setNode(2, n20.getNode());
		tiles.get(11).setNode(0, n20.getNode());
		
		// Row 6
		Point p21 = tiles.get(7).getPoints()[5];
		NodeGUI n21 = new NodeGUI(board.getNodes().get(21), p21);
		nodeGUIs.add(n21);
		tiles.get(7).setNode(5, n21.getNode());
		
		for (int i = 22; i < 26; i++) {
			Point p = tiles.get(i - 15).getPoints()[1];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 15).setNode(1, n.getNode());
			tiles.get(i - 14).setNode(5, n.getNode());
			tiles.get(i - 19).setNode(3, n.getNode());
		}
		
		Point p26 = tiles.get(11).getPoints()[1];
		NodeGUI n26 = new NodeGUI(board.getNodes().get(26), p26);
		nodeGUIs.add(n26);
		tiles.get(11).setNode(1, n26.getNode());

		// Row 7
		Point p27 = tiles.get(7).getPoints()[4];
		NodeGUI n27 = new NodeGUI(board.getNodes().get(27), p27);
		nodeGUIs.add(n27);
		tiles.get(7).setNode(4, n27.getNode());
		
		for (int i = 28; i < 32; i++) {
			Point p = tiles.get(i - 21).getPoints()[2];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 21).setNode(2, n.getNode());
			tiles.get(i - 20).setNode(4, n.getNode());
			tiles.get(i - 16).setNode(0, n.getNode());
		}
		
		Point p32 = tiles.get(11).getPoints()[2];
		NodeGUI n32 = new NodeGUI(board.getNodes().get(32), p32);
		nodeGUIs.add(n32);
		tiles.get(11).setNode(2, n32.getNode());
		
		// Row 8
		Point p33 = tiles.get(12).getPoints()[5];
		NodeGUI n33 = new NodeGUI(board.getNodes().get(33), p33);
		nodeGUIs.add(n33);
		tiles.get(12).setNode(5, n33.getNode());
		tiles.get(7).setNode(3, n33.getNode());
		
		for (int i = 34; i < 37; i++) {
			Point p = tiles.get(i - 22).getPoints()[1];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 26).setNode(3, n.getNode());
			tiles.get(i - 22).setNode(1, n.getNode());
			tiles.get(i - 21).setNode(5, n.getNode());
		}
		
		Point p37 = tiles.get(11).getPoints()[3];
		NodeGUI n37 = new NodeGUI(board.getNodes().get(37), p37);
		nodeGUIs.add(n37);
		tiles.get(11).setNode(3, n37.getNode());
		tiles.get(15).setNode(1, n37.getNode());
		
		// row 9
		Point p38 = tiles.get(12).getPoints()[4];
		NodeGUI n38 = new NodeGUI(board.getNodes().get(38), p38);
		nodeGUIs.add(n38);
		tiles.get(12).setNode(4, n38.getNode());
		
		for (int i = 39; i < 42; i++) {
			Point p = tiles.get(i - 27).getPoints()[2];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 27).setNode(2, n.getNode());
			tiles.get(i - 26).setNode(4, n.getNode());
			tiles.get(i - 23).setNode(0, n.getNode());
		}

		Point p42 = tiles.get(15).getPoints()[2];
		NodeGUI n42 = new NodeGUI(board.getNodes().get(42), p42);
		nodeGUIs.add(n42);
		tiles.get(15).setNode(2, n42.getNode());
		
		// row 10
		Point p43 = tiles.get(12).getPoints()[3];
		NodeGUI n43 = new NodeGUI(board.getNodes().get(43), p43);
		nodeGUIs.add(n43);
		tiles.get(12).setNode(3, n43.getNode());
		tiles.get(16).setNode(5, n43.getNode());
		
		for (int i = 44; i < 46; i++) {
			Point p = tiles.get(i - 28).getPoints()[1];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 28).setNode(1, n.getNode());
			tiles.get(i - 31).setNode(3, n.getNode());
			tiles.get(i - 27).setNode(5, n.getNode());
		}
		
		Point p46 = tiles.get(15).getPoints()[3];
		NodeGUI n46 = new NodeGUI(board.getNodes().get(46), p46);
		nodeGUIs.add(n46);
		tiles.get(15).setNode(3, n46.getNode());
		tiles.get(18).setNode(1, n46.getNode());
		
		// row 11
		Point p47 = tiles.get(16).getPoints()[4];
		NodeGUI n47 = new NodeGUI(board.getNodes().get(47), p47);
		nodeGUIs.add(n47);
		tiles.get(16).setNode(4, n47.getNode());
		
		for (int i = 48; i < 50; i++) {
			Point p = tiles.get(i - 32).getPoints()[2];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 32).setNode(2, n.getNode());
			tiles.get(i - 31).setNode(4, n.getNode());
		}
		
		Point p50 = tiles.get(18).getPoints()[2];
		NodeGUI n50 = new NodeGUI(board.getNodes().get(50), p50);
		nodeGUIs.add(n50);
		tiles.get(18).setNode(2, n50.getNode());
		
		// row 12
		for (int i = 51; i < 54; i++) {
			Point p = tiles.get(i - 35).getPoints()[3];
			NodeGUI n = new NodeGUI(board.getNodes().get(i), p);
			nodeGUIs.add(n);
			tiles.get(i - 35).setNode(3, n.getNode());
		}
	}

	// Gives the edges the correct coordinates so that they can be drawn when necessary
	private void setupEdges() {
		edgeGUIs.clear();
		
		for (Edge e: board.getEdges()) {
			EdgeGUI eg = new EdgeGUI(e, e.getNode1().getGUI().getCenter(), e.getNode2().getGUI().getCenter());
			edgeGUIs.add(eg);
		}
	}
	
	private class NodeButtonListener implements ActionListener {
		
		private final Node n;
		
		public NodeButtonListener(Node n) {
			this.n = n;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			// if there is no settlement, build a settlement
			if (n.getSettlement() == null) {
				n.setSettlement(new Settlement(game.getCurrentPlayer()));
				
			// otherwise, build a city
			} else {
				n.setCity(new City(game.getCurrentPlayer()));
			}
			
			// clear out all the availableNodes and buttons
			availableNodes = null;
			displayAvailableNodes = false;
			for (JButton b: buttons) {
				BoardGUI.this.remove(b);
				b = null;
			}
			
			buttons.clear();
			
			if (takeResources) {
				//removeResourceCardsAfterBuilding();
				game.updatePlayerGUI();
			} else {
				JOptionPane.showOptionDialog(null, 
				        "Please Build a Road.",  
				        "Build House",
				        JOptionPane.OK_OPTION, 
				        JOptionPane.PLAIN_MESSAGE, 
				        null, 
				        new String[]{"Okay"},
				        "default");
				board.buildRoadAtStartWithPlayer(game.getCurrentPlayer());
			}
			
			// repaint
			BoardGUI.this.repaint();	
		}
		
		private void removeResourceCardsAfterBuilding() {
			Player p = BoardGUI.this.game.getCurrentPlayer();
			
			if (n.getCity() == null) {
				// Remove resources for settlement
				p.removeResourceCardOfType(ResourceType.BRICK);
				p.removeResourceCardOfType(ResourceType.WHEAT);
				p.removeResourceCardOfType(ResourceType.SHEEP);
				p.removeResourceCardOfType(ResourceType.WOOD);
			} else {
				// Remove resources for city
				p.removeResourceCardOfType(ResourceType.WHEAT);
				p.removeResourceCardOfType(ResourceType.WHEAT);
				p.removeResourceCardOfType(ResourceType.STONE);
				p.removeResourceCardOfType(ResourceType.STONE);
				p.removeResourceCardOfType(ResourceType.STONE);
			}
		}
	}
	
	private class EdgeButtonListener implements ActionListener {
		
		private final Edge edge;
		
		public EdgeButtonListener(Edge edge) {
			this.edge = edge;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// if there is no settlement, build a settlement
			if (edge.getRoad() == null) {
				edge.setRoad(new Road(game.getCurrentPlayer()));
			}
			
			// clear out all the availableNodes and buttons
			availableEdges = null;
			displayAvailableEdges = false;
			for (JButton b: buttons) {
				BoardGUI.this.remove(b);
				b = null;
			}
			
			buttons.clear();
			
			if (takeResources) {
				//removeResourceCardsAfterBuilding();	
				game.updatePlayerGUI();
			} else {
				BoardGUI.this.game.continueInitialBuilding();
			}
			
			// repaint
			BoardGUI.this.repaint();	
		}
		
		private void removeResourceCardsAfterBuilding() {
			Player p = BoardGUI.this.game.getCurrentPlayer();
			p.removeResourceCardOfType(ResourceType.BRICK);
			p.removeResourceCardOfType(ResourceType.WOOD);
		}
	}

	private class TileButtonListener implements ActionListener {
		
		private final Tile tile;
		
		public TileButtonListener(Tile tile) {
			this.tile = tile;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			tile.setRobber(true);
			
			displayAvailableTilesForRobber = false;
			for (JButton b: buttons) {
				BoardGUI.this.remove(b);
				b = null;
			}
			
			// Steal card
			ArrayList<Node> nodesWithPlayers = new ArrayList<Node>();
			for (Node n: tile.getNodes()) {
				if (n.getPlayer() != null && n.getPlayer() != game.getCurrentPlayer() && !n.getPlayer().getResourceCards().isEmpty()) {
					nodesWithPlayers.add(n);
				}
			}
			
			if (!nodesWithPlayers.isEmpty()) {
				Random rand = new Random();
				Player p = nodesWithPlayers.get(rand.nextInt(nodesWithPlayers.size())).getPlayer();
				ArrayList<ResourceCard> cards = p.getResourceCards();
				int cardNum = rand.nextInt(cards.size());
				ResourceCard card = cards.get(cardNum);
				cards.remove(cardNum);
				game.getCurrentPlayer().addCard(card);
				
				String resourceType = "";
				switch (card.getType()) {
					case BRICK: resourceType = "brick";
								break;
					case SHEEP: resourceType = "sheep";
								break;
					case STONE: resourceType = "stone";
								break;
					case WHEAT: resourceType = "wheat";
								break;
					case WOOD: resourceType = "wood";
								break;
					default:
								break;	
					
				}
				
				JOptionPane.showOptionDialog(null, 
				        "You stole a " + resourceType + " from " + p.getName() + ".",  
				        "Resource Stolen",
				        JOptionPane.OK_OPTION, 
				        JOptionPane.PLAIN_MESSAGE, 
				        null, 
				        new String[]{"Start turn"}, // this is the array
				        "default");
				
				game.updatePlayerGUI();
			}
			
			buttons.clear();
			
			// repaint
			BoardGUI.this.repaint();	
		}
	}
}
