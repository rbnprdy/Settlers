package ece373.catan.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class NodeGUI {
	
	private Node n;
	
	private Ellipse2D circle;
	private Rectangle square;
	
	private Point center;
	private int diameter = 25;
	
	public NodeGUI(Node n, Point center) {
		this.n = n;
		n.setGUI(this);
		this.center = center;
		
		circle = new Ellipse2D.Double(center.getX() - diameter + 7, center.getY() - diameter + 5, diameter, diameter);
		square = new Rectangle((int) (center.getX() - (diameter/2+10)), (int) (center.getY() - (diameter/2+5)), diameter+5, diameter+5);
	}
	
	public Point getCenter() {
		return center;
	}
	
	public Node getNode() {
		return n;
	}
	
	public Ellipse2D getCircle() {
		return circle;
	}
	
	public Rectangle getSquare() {
		return square;
	}
}
