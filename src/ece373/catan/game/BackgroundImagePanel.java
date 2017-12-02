package ece373.catan.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundImagePanel extends JPanel {
	
	Image image;
	
	public BackgroundImagePanel(ImageIcon i) {
		this.image = i.getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
	}
}
