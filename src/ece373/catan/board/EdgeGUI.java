package ece373.catan.board;

import java.awt.*;

@SuppressWarnings("serial")
public class EdgeGUI extends Polygon {

	private final int width = 4;
	private final int padding = 25;
	
	private Point p1;
	private Point p2;
	
	private int tileRadius = 90;
	
	private Edge e;
	
	public EdgeGUI(Edge e, Point p1, Point p2) {
		
		double xIncrement = Math.sin((60*Math.PI)/180)*tileRadius;
		double yIncrement = Math.cos((60*Math.PI)/180)*tileRadius;
		
		this.e = e;
		
		double p1X = p1.getX();
		double p2X = p2.getX();
		double p1Y = p1.getY();
		double p2Y = p2.getY();
		
		double slope = Math.abs(p1Y-p2Y)/Math.abs(p1X-p2X);
		//double dx = Math.sqrt(25/(1+Math.pow(slope, 2)));
		//double dy = dx*slope;
		double dx = xIncrement / 2;
		double dy = yIncrement / 2;
		
//		if (p1X > p2X) {
//			p1X -= dx;
//			p2X += dx;
//		} else if (p1X < p2X) {
//			p1X += dx;
//			p2X -= dx;
//		}
//		
//		if (p1Y > p2Y) {
//			p1Y -= dy;
//			p2Y += dy;
//		} else if (p1Y < p2Y) {
//			p2Y += dy;
//			p2Y -= dy;
//		}

		this.p1 = new Point((int) p1X, (int) p1Y);
		this.p2 = new Point((int) p2X, (int) p2Y);
	}
	
	public Point getP1() {
		return p1;
	}
	
	public Point getP2() {
		return p2;
	}
}
