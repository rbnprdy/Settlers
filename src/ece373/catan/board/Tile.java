package ece373.catan.board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class Tile extends Polygon {
	
	private final int angle = 60;
	private final double circleRadiusScalingFactor = 1.5;

	private Point center = new Point(0,0);
	private int radius;
	private Integer number;
	private Image i;
	
	public Tile(Point center, int radius, int number) {
		
		this.center = center;
		this.radius = radius;
		this.number = number;
		
		// Draw the image background
		String source = "images/board/test.jpg";
		File imageFile = new File(source);
        i = null;
        try {
			i = ImageIO.read(imageFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}  
        
        i = i.getScaledInstance(radius*2, radius*2, Image.SCALE_DEFAULT);
		
		setupPoints();
	}
	
	public Point[] getPoints() {
		Point[] points = new Point[6];
		for (int i = 0; i < 6; i++) {
			points[i] = new Point(xpoints[i], ypoints[i]);
		}
		
		return points;
	}
	
	public Integer getNumber() {
		return number;
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
		ypoints[0] = (int) (center.getY() + radius);
		
		// Find xDist from center
		double xDist = Math.sin((angle*Math.PI)/180)*radius;
		// Find yDist from center
		double yDist = Math.cos((angle*Math.PI)/180)*radius;
		
		xpoints[1] = (int) (center.getX() + xDist);
		ypoints[1] = (int) (center.getY() + yDist);
		
		xpoints[2] = (int) (center.getX() + xDist);
		ypoints[2] = (int) (center.getY() - yDist);
		
		xpoints[3] = (int) center.getX();
		ypoints[3] = (int) (center.getY() - radius);
		
		xpoints[4] = (int) (center.getX() - xDist);
		ypoints[4] = (int) (center.getY() - yDist);

		xpoints[5] = (int) (center.getX() - xDist);
		ypoints[5] = (int) (center.getY() + yDist);
	}
	
	public void drawShape(Graphics2D g) {
		// Draw the polygon

		g.setColor(new Color(0x000000));		
		g.fillPolygon(xpoints, ypoints, npoints);
		
        g.setClip(this);
	
		g.drawImage(i,(int) center.getX() - radius,(int) center.getY() - radius,null);
		
		g.drawPolygon(xpoints, ypoints, npoints);
		
		// Draw the circle
		Ellipse2D circle = new Ellipse2D.Double(center.getX() - radius / (circleRadiusScalingFactor*2), center.getY() - radius / (circleRadiusScalingFactor*2), radius / circleRadiusScalingFactor, radius / circleRadiusScalingFactor);
		g.setColor(new Color(0xead4b8));
		
		g.fill(circle);
		
		// Draw then number
		g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (radius / (circleRadiusScalingFactor * 2))));
		// Center the number
	    FontMetrics metrics = g.getFontMetrics(g.getFont());
	    int stringX = (int) (circle.getX() + (circle.getWidth() - metrics.stringWidth(number.toString())) / 2);
	    int stringY = (int) (circle.getY() + ((circle.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());

		g.drawString(number.toString(), stringX, stringY);
	}
}
