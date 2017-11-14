package ece373.catan.board;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class BoardGUI extends JPanel {
	
	private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    
    private final int tileRadius = 90;
    private final double circleRadiusScalingFactor = 1.5;
    
    private ArrayList<NodeGUI> nodeGUIs;
    
    private ArrayList<Tile> tiles;
    
    private Board b;
    
    public BoardGUI() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tiles = new ArrayList<Tile>();
        nodeGUIs = new ArrayList<NodeGUI>();
        b = new Board();
    }

    public static void main(String[] args) {
    		JFrame frame = new JFrame();
        
        BoardGUI b = new BoardGUI();

        frame.setContentPane(b);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        Point origin = new Point(WIDTH / 2, HEIGHT / 2);
        		
        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        
        double xIncrement = Math.sin((60*Math.PI)/180)*tileRadius - 0.5;
        double yIncrement = Math.cos((60*Math.PI)/180)*tileRadius;
        
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
        
        
        setupNodes();

        g2d.setColor(Color.white);
        for (Node n: b.getNodes()) {
        		if (n.getGUI() != null) {
        			g2d.fill(n.getGUI().getCircle());
        		}
        }
        
    }
    
    private void setupNodes() {
    		Point origin = new Point(WIDTH / 2, HEIGHT / 2);
    		double xIncrement = Math.sin((60*Math.PI)/180)*tileRadius - 0.5;
        double yIncrement = Math.cos((60*Math.PI)/180)*tileRadius;
            
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
}
