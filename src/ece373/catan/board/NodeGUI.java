package ece373.catan.board;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class NodeGUI {
	
	private Node n;
	
	private Ellipse2D circle;
	private Rectangle square;
	
	private Point center;
	private int diameter = 25;
	
	private JButton button;
	
	public NodeGUI(Node n, Point center) {
		this.n = n;
		n.setGUI(this);
		this.center = center;
		this.button = null;
		
		circle = new Ellipse2D.Double(center.getX() - diameter/2, center.getY() - diameter/2, diameter, diameter);
		square = new Rectangle((int) (center.getX() - (diameter/2)), (int) (center.getY() - (diameter/2)), diameter, diameter);
	}
	
	public JButton getButton() {
		return button;
	}
	
	public void setButton(JButton button) {
		this.button = button;
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
