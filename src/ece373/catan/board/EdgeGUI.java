package ece373.catan.board;

import java.awt.*;

@SuppressWarnings("serial")
public class EdgeGUI extends Polygon {

	private final int WIDTH = 15;
	
	private Edge e;
	
	private Point p1;
	private Point p2;
	
	public EdgeGUI(Edge e, Point p1, Point p2) {
		
		this.e = e;
		this.p1 = p1;
		this.p2 = p2;
		
	}
	
	public void displayAvailable(Graphics2D g) {
		g.setStroke(new BasicStroke(WIDTH));
		g.setColor(new Color(0xffffff));
		g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
	}
}
