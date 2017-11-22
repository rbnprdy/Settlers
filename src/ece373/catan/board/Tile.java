package ece373.catan.board;

import ece373.catan.card.ResourceType;

import java.awt.*;

@SuppressWarnings("serial")
public class Tile extends Polygon {
	
	private final int angle = 60;
	private final double circleRadiusScalingFactor = 1.5;
	
	private ResourceType resourceType;

	private Point center = new Point(0,0);
	private int radius;
	private Integer number;
	
	private Node[] nodes = new Node[6];
	
	public Tile(Point center, int radius, int number, ResourceType resourceType) {
		this.resourceType = resourceType;
		this.center = (Point) center.clone();
		this.radius = radius;
		this.number = number;
		
		setupPoints();
	}
	
	public void setNode(int num, Node node) {
		if (num > 5) {
			System.out.println("num is too large when adding node");
		} else {
			nodes[num] = node;
		}	
	}
	
	public Point[] getPoints() {
		Point[] points = new Point[6];
		for (int i = 0; i < 6; i++) {
			points[i] = new Point(xpoints[i], ypoints[i]);
		}
		
		return points;
	}
	
	public ResourceType getResourceType() {
		return resourceType;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public double getCircleRadiusScalingFactor() {
		return circleRadiusScalingFactor;
	}
	
	public void setupPoints() {
		
		npoints = 6;
		xpoints = new int[6];
		ypoints = new int[6];
		
		// Starting from top point, going clockwise
		xpoints[0] = (int) center.getX(); 
		ypoints[0] = (int) (center.getY() - radius);
		
		// Find xDist from center
		double xDist = Math.sin((angle*Math.PI)/180)*radius;
		// Find yDist from center
		double yDist = Math.cos((angle*Math.PI)/180)*radius;
		
		xpoints[1] = (int) (center.getX() + xDist);
		ypoints[1] = (int) (center.getY() - yDist);
		
		xpoints[2] = (int) (center.getX() + xDist);
		ypoints[2] = (int) (center.getY() + yDist);
		
		xpoints[3] = (int) center.getX();
		ypoints[3] = (int) (center.getY() + radius);
		
		xpoints[4] = (int) (center.getX() - xDist);
		ypoints[4] = (int) (center.getY() + yDist);

		xpoints[5] = (int) (center.getX() - xDist);
		ypoints[5] = (int) (center.getY() - yDist);
	}
}
