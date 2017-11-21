package ece373.catan.board;

import javax.swing.*;

import ece373.catan.game.*;
import ece373.catan.player.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class BoardGUI extends JPanel {

	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	private final int edgeThickness = 8; 

	private final int tileRadius = 90;
	private final double circleRadiusScalingFactor = 1.5;
	
	private Game game;

	private ArrayList<NodeGUI> nodeGUIs;
	private ArrayList<EdgeGUI> edgeGUIs;

	private ArrayList<Tile> tiles;
	
	private ArrayList<JButton> buttons;

	private Board b;

	private ArrayList<Node> availableNodes;
	private boolean displayAvailableNodes;

	private double xIncrement;
	private double yIncrement;

	public BoardGUI(Game g) {

		this.game = g;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		tiles = new ArrayList<Tile>();
		nodeGUIs = new ArrayList<NodeGUI>();
		edgeGUIs = new ArrayList<EdgeGUI>();
		buttons = new ArrayList<JButton>();
		availableNodes = new ArrayList<Node>();
		displayAvailableNodes = false;

		b = new Board();

		xIncrement = Math.sin((60*Math.PI)/180)*tileRadius - 0.5;
		yIncrement = Math.cos((60*Math.PI)/180)*tileRadius;

		setupNodes();
		setupEdges();

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Game g = new Game();
		BoardGUI bGUI = new BoardGUI(g);

		frame.setContentPane(bGUI);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(null);

		Board b = bGUI.getBoard();

		Player p = new Player("test", Color.blue);
		g.setCurrentPlayer(p);
		b.getNodes().get(18).setSettlement(new Settlement(p));
		b.getNodes().get(47).setSettlement(new Settlement(p));
		b.getNodes().get(8).setCity(new City(p));
		b.getNodes().get(19).setSettlement(new Settlement(p));
		b.getNodes().get(20).setCity(new City(p));

		b.getEdges().get(62).setRoad(new Road(p));
		b.getEdges().get(55).setRoad(new Road(p));
		b.getEdges().get(50).setRoad(new Road(p));

		bGUI.showAvailableNodes(b.getAvailableNodesForCitiesFor(p));
	}

	public Board getBoard() {
		return b;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Point origin = new Point(WIDTH / 2, HEIGHT / 2);

		g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

		// Paint the tiles row by row
		origin.setLocation(WIDTH / 2 - 2*xIncrement, HEIGHT / 2 - 6*yIncrement);
		for (int i = 0; i < 3; i++) {
			// Draw the tile
			Tile t = new Tile(origin, tileRadius, 1);
			tiles.add(t);
			g2d.setColor(Color.green);
			g2d.fill(t);
			g2d.setColor(Color.black);
			g2d.draw(t);

			// Draw the circle
			Ellipse2D circle = new Ellipse2D.Double(origin.getX() - tileRadius / (circleRadiusScalingFactor*2), origin.getY() - tileRadius / (circleRadiusScalingFactor*2), tileRadius / circleRadiusScalingFactor, tileRadius / circleRadiusScalingFactor);
			g2d.setColor(new Color(0xead4b8));

			g2d.fill(circle);

			// Draw then number
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, (int) (tileRadius / (circleRadiusScalingFactor * 2))));
			g2d.setColor(Color.black);
			// Center the number
			FontMetrics metrics = g2d.getFontMetrics(g.getFont());
			int stringX = (int) (circle.getX() + (circle.getWidth() - metrics.stringWidth(t.getNumber().toString())) / 2);
			int stringY = (int) (circle.getY() + ((circle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

			g2d.drawString(t.getNumber().toString(), stringX, stringY);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement, HEIGHT / 2 - 3*yIncrement);
		for (int i = 0; i < 4; i++) {

			// Draw the tile
			Tile t = new Tile(origin, tileRadius, 1);
			tiles.add(t);
			g2d.setColor(Color.green);
			g2d.fill(t);
			g2d.setColor(Color.black);
			g2d.draw(t);

			// Draw the circle
			Ellipse2D circle = new Ellipse2D.Double(origin.getX() - tileRadius / (circleRadiusScalingFactor*2), origin.getY() - tileRadius / (circleRadiusScalingFactor*2), tileRadius / circleRadiusScalingFactor, tileRadius / circleRadiusScalingFactor);
			g2d.setColor(new Color(0xead4b8));

			g2d.fill(circle);

			// Draw then number
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, (int) (tileRadius / (circleRadiusScalingFactor * 2))));
			g2d.setColor(Color.black);
			// Center the number
			FontMetrics metrics = g2d.getFontMetrics(g.getFont());
			int stringX = (int) (circle.getX() + (circle.getWidth() - metrics.stringWidth(t.getNumber().toString())) / 2);
			int stringY = (int) (circle.getY() + ((circle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

			g2d.drawString(t.getNumber().toString(), stringX, stringY);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 4*xIncrement, HEIGHT / 2);
		for (int i = 0; i < 5; i++) {

			// Draw the tile
			Tile t = new Tile(origin, tileRadius, 1);
			tiles.add(t);
			g2d.setColor(Color.green);
			g2d.fill(t);
			g2d.setColor(Color.black);
			g2d.draw(t);

			// Draw the circle
			Ellipse2D circle = new Ellipse2D.Double(origin.getX() - tileRadius / (circleRadiusScalingFactor*2), origin.getY() - tileRadius / (circleRadiusScalingFactor*2), tileRadius / circleRadiusScalingFactor, tileRadius / circleRadiusScalingFactor);
			g2d.setColor(new Color(0xead4b8));

			g2d.fill(circle);

			// Draw then number
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, (int) (tileRadius / (circleRadiusScalingFactor * 2))));
			g2d.setColor(Color.black);
			// Center the number
			FontMetrics metrics = g2d.getFontMetrics(g.getFont());
			int stringX = (int) (circle.getX() + (circle.getWidth() - metrics.stringWidth(t.getNumber().toString())) / 2);
			int stringY = (int) (circle.getY() + ((circle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

			g2d.drawString(t.getNumber().toString(), stringX, stringY);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement, HEIGHT / 2 + 3*yIncrement);
		for (int i = 0; i < 4; i++) {
			// Draw the tile
			Tile t = new Tile(origin, tileRadius, 1);
			tiles.add(t);
			g2d.setColor(Color.green);
			g2d.fill(t);
			g2d.setColor(Color.black);
			g2d.draw(t);

			// Draw the circle
			Ellipse2D circle = new Ellipse2D.Double(origin.getX() - tileRadius / (circleRadiusScalingFactor*2), origin.getY() - tileRadius / (circleRadiusScalingFactor*2), tileRadius / circleRadiusScalingFactor, tileRadius / circleRadiusScalingFactor);
			g2d.setColor(new Color(0xead4b8));

			g2d.fill(circle);

			// Draw then number
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, (int) (tileRadius / (circleRadiusScalingFactor * 2))));
			g2d.setColor(Color.black);
			// Center the number
			FontMetrics metrics = g2d.getFontMetrics(g.getFont());
			int stringX = (int) (circle.getX() + (circle.getWidth() - metrics.stringWidth(t.getNumber().toString())) / 2);
			int stringY = (int) (circle.getY() + ((circle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

			g2d.drawString(t.getNumber().toString(), stringX, stringY);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY());
		}

		origin.setLocation(WIDTH / 2 - 2*xIncrement, HEIGHT / 2 + 6*yIncrement);
		for (int i = 0; i < 3; i++) {
			// Draw the tile
			Tile t = new Tile(origin, tileRadius, 1);
			tiles.add(t);
			g2d.setColor(Color.green);
			g2d.fill(t);
			g2d.setColor(Color.black);
			g2d.draw(t);

			// Draw the circle
			Ellipse2D circle = new Ellipse2D.Double(origin.getX() - tileRadius / (circleRadiusScalingFactor*2), origin.getY() - tileRadius / (circleRadiusScalingFactor*2), tileRadius / circleRadiusScalingFactor, tileRadius / circleRadiusScalingFactor);
			g2d.setColor(new Color(0xead4b8));

			g2d.fill(circle);

			// Draw then number
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, (int) (tileRadius / (circleRadiusScalingFactor * 2))));
			g2d.setColor(Color.black);
			// Center the number
			FontMetrics metrics = g2d.getFontMetrics(g.getFont());
			int stringX = (int) (circle.getX() + (circle.getWidth() - metrics.stringWidth(t.getNumber().toString())) / 2);
			int stringY = (int) (circle.getY() + ((circle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

			g2d.drawString(t.getNumber().toString(), stringX, stringY);

			origin.setLocation(2*xIncrement+ origin.getX(), origin.getY()); 
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

		//Display available nodes if applicable
		if (displayAvailableNodes) {
			for (Node n: availableNodes) {
				if (n.getGUI() != null) {
					JButton b1 = new JButton();
					b1.setLayout(null);
					b1.setBounds(n.getGUI().getCircle().getBounds());
					b1.addActionListener(new NodeButtonListener(n));
					add(b1);
					buttons.add(b1);
					n.getGUI().setButton(b1);
				}
			}
		}

		g2d.setStroke(new BasicStroke(edgeThickness));
		for (EdgeGUI e: edgeGUIs) {
			g2d.drawLine((int) e.getP1().getX(), (int) e.getP1().getY(), (int) e.getP2().getX(), (int) e.getP2().getY());
		}
	}

	public void showAvailableNodes(ArrayList<Node> nodes) {
		availableNodes = nodes;
		displayAvailableNodes = true;
		this.repaint();
	}

	// Gives the nodes the correct coordinates so that they can be drawn when necessary
	private void setupNodes() {
		Point origin = new Point(WIDTH / 2, HEIGHT / 2);

		origin.setLocation(WIDTH / 2 - 2*xIncrement + 5, HEIGHT / 2 - 8*yIncrement + 5);
		for (int i = 0; i < 3; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			
			origin.translate((int) (2*xIncrement), 0);
		
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement + 5, HEIGHT / 2 - 7*yIncrement + 5);
		for (int i = 3; i < 7; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement + 5, HEIGHT / 2 - 5*yIncrement + 5);
		for (int i = 7; i < 11; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 4*xIncrement + 5, HEIGHT / 2 - 4*yIncrement + 5);
		for (int i = 11; i < 16; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 4*xIncrement + 5, HEIGHT / 2 - 2*yIncrement + 5);
		for (int i = 16; i < 21; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 5*xIncrement + 5, HEIGHT / 2 - 1*yIncrement + 5);
		for (int i = 21; i < 27; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 5*xIncrement + 5, HEIGHT / 2 + 1*yIncrement + 5);
		for (int i = 27; i < 33; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 4*xIncrement + 5, HEIGHT / 2 + 2*yIncrement + 5);
		for (int i = 33; i < 38; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 4*xIncrement + 5, HEIGHT / 2 + 4*yIncrement + 5);
		for (int i = 38; i < 43; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement + 5, HEIGHT / 2 + 5*yIncrement + 5);
		for (int i = 43; i < 47; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 3*xIncrement + 5, HEIGHT / 2 + 7*yIncrement + 5);
		for (int i = 47; i < 51; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}

		origin.setLocation(WIDTH / 2 - 2*xIncrement + 5, HEIGHT / 2 + 8*yIncrement + 5);
		for (int i = 51; i < 54; i++) {
			Point p = new Point((int) origin.getX(), (int) origin.getY());
			NodeGUI n = new NodeGUI(b.getNodes().get(i), p);
			nodeGUIs.add(n);
			origin.translate((int) (2*xIncrement), 0);
		}
	}

	// Gives the edges the correct coordinates so that they can be drawn when necessary
	private void setupEdges() {
		NodeGUI n1 = nodeGUIs.get(0);
		NodeGUI n2 = nodeGUIs.get(4);
		Edge e = new Edge(n1.getNode(), n2.getNode());
		EdgeGUI eg = new EdgeGUI(e, n1.getCenter(), n2.getCenter());
		edgeGUIs.add(eg);
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
			
			// repaint
			BoardGUI.this.repaint();
			
		}
		
	}
}
