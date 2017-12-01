package ece373.catan.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;

import ece373.catan.board.BoardGUI;
import ece373.catan.board.City;
import ece373.catan.board.Node;
import ece373.catan.board.Settlement;
import ece373.catan.card.ResourceType;
import ece373.catan.player.Player;

public class TitleScreenGUI {

	private JFrame frame;
	private JPanel numPlayersPanel;

	private Integer[] numPlayers = {2, 3, 4};
	JComboBox<Integer> numPlayersBox;
	private Font titleFont = new Font("SansSerif", Font.PLAIN, 40);
	private Font smallFont = new Font("SansSerif", Font.PLAIN, 30);

	public TitleScreenGUI() {

		frame = new JFrame();
		frame.setTitle("Settlers of Catan");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); 
		
		BackgroundImagePanel p = new BackgroundImagePanel(new ImageIcon(this.getClass().getResource("/game/strategies.jpg")));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		frame.add(p);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		
		JLabel titleLabel = new JLabel("Settlers of Catan");
		titleLabel.setFont(titleFont);
		titleLabel.setOpaque(false);
		titleLabel.setFont(titleLabel.getFont().deriveFont(150.0f));

		titlePanel.add(titleLabel);

		titlePanel.setVisible(true);
		p.add(titlePanel);

		numPlayersPanel = new JPanel();	
		numPlayersPanel.setOpaque(false);

		JLabel numLabel = new JLabel("Enter Number of Players:");
		numLabel.setOpaque(false);
		numLabel.setFont(titleFont);
		numPlayersPanel.add(numLabel);

		numPlayersBox = new JComboBox<Integer>(numPlayers);
		numPlayersBox.setSelectedIndex(0);
		numPlayersBox.setOpaque(false);
		numPlayersPanel.add(Box.createRigidArea(new Dimension (20, 0)));
		numPlayersPanel.add(numPlayersBox);

		JButton beginButton = new JButton("Begin");
		beginButton.setFont(titleFont);
		beginButton.addActionListener(new ButtonListener(numPlayersBox));
		numPlayersPanel.add(Box.createRigidArea(new Dimension (20, 0)));
		numPlayersPanel.add(beginButton);

		numPlayersPanel.setVisible(true);
		p.add(numPlayersPanel);
		
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		TitleScreenGUI t = new TitleScreenGUI();
	}

	private class ButtonListener implements ActionListener {

		private JComboBox<Integer> comboBox;
		
		public ButtonListener(JComboBox<Integer> box) {
			this.comboBox = box;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			int numPlayers = (int) comboBox.getSelectedItem();
			
			JLabel[] message = new JLabel[numPlayers];
			
			Object[] messagesAndFields = new Object[numPlayers*2];
			
			JTextField[] textFields = new JTextField[numPlayers];
			
			int j = 0;
			for (int i = 0; i < numPlayers; i++) {
				message[i] = new JLabel("Player " + Integer.toString(i + 1) + ":");
				message[i].setFont(smallFont);
				messagesAndFields[j] =  message[i];
				j++;
				textFields[i] = new JTextField();
				textFields[i].setFont(smallFont);
				messagesAndFields[j] = textFields[i];
				j++;
			}
			
			int option = JOptionPane.showConfirmDialog(null, messagesAndFields, "Enter Player Names", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				
				ArrayList<Player> players = new ArrayList<Player>();
				
				Color[] colors = { Color.blue, Color.red, Color.white, Color.orange };
				
			    for (int i = 0; i < numPlayers; i++) {
			    		players.add(new Player(textFields[i].getText(), colors[i]));
			    }
			    
			    Game g = new Game();
			    
			    for (Player p: players) {
			    		g.addPlayer(p);
			    }
			    
			    g.setCurrentPlayer(players.get(0));
			    
			    GameGUI gui = new GameGUI(g);
			    gui.setVisible(true);
			    TitleScreenGUI.this.frame.setVisible(false);
			    
			}
		}
	}
}
