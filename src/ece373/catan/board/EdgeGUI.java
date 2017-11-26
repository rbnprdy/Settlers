package ece373.catan.board;

import java.awt.*;

@SuppressWarnings("serial")
public class EdgeGUI extends Polygon {

	private final double numerator = 3;
	private final double denominator = 4;
	
	private Point p1;
	private Point p2;
	
	private Edge e;
	
	public EdgeGUI(Edge e, Point p1, Point p2) {
		
		this.e = e;
		e.setGUI(this);
		
		// get xCoord 1/4 and 3/4 down the line
		double p1X = numerator*p1.getX()/denominator + (denominator-numerator)*p2.getX()/denominator;
		double p2X = (denominator-numerator)*p1.getX()/denominator + numerator*p2.getX()/denominator;
		
		// get yCoord 1/4 and 3/4 down the line
		double p1Y = numerator*p1.getY()/denominator + (denominator-numerator)*p2.getY()/denominator;
		double p2Y = (denominator-numerator)*p1.getY()/denominator + numerator*p2.getY()/denominator;

		this.p1 = new Point((int) p1X, (int) p1Y);
		this.p2 = new Point((int) p2X, (int) p2Y);
	}
	
	public Point getP1() {
		return p1;
	}
	
	public Point getP2() {
		return p2;
	}
	
	public Point getCenter() {
		int x = (int) (p1.getX() + p2.getX()) / 2;
		int y = (int) (p1.getY() + p2.getY()) / 2;
		return new Point(x, y);
	}
	
	public Edge getEdge() {
		return e;
	}
}
