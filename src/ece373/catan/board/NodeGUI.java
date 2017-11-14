package ece373.catan.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class NodeGUI {
	
	private Node n;
	
	private Ellipse2D circle;
	
	private Point center;
	private int radius = 10;
	
	public NodeGUI(Node n, Point center) {
		this.n = n;
		n.setGUI(this);
		this.center = center;
		
		circle = new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, radius, radius);
	}
	
	public Ellipse2D getCircle() {
		return circle;
	}
}
