package ece373.catan.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

	public TitleScreenGUI() {
		frame = new JFrame();
		frame.setTitle("Settlers of Catan");
		frame.setBounds(100, 100, 1200, 900);

		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Settlers of Catan");
		titleLabel.setFont(titleLabel.getFont().deriveFont(94.0f));

		titlePanel.add(titleLabel);

		titlePanel.setVisible(true);
		frame.add(titlePanel);

		numPlayersPanel = new JPanel();	

		JLabel numLabel = new JLabel("Enter Number of Players:");
		numPlayersPanel.add(numLabel);

		numPlayersBox = new JComboBox<Integer>(numPlayers);
		numPlayersBox.setSelectedIndex(0);

		numPlayersPanel.add(numPlayersBox);

		JButton beginButton = new JButton("Begin");
		beginButton.addActionListener(new ButtonListener(numPlayersBox));
		numPlayersPanel.add(beginButton);

		numPlayersPanel.setVisible(true);
		frame.add(numPlayersPanel);

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
			
			Object[] message = new Object[numPlayers];
			
			Object[] messagesAndFields = new Object[numPlayers*2];
			
			JTextField[] textFields = new JTextField[numPlayers];
			
			int j = 0;
			for (int i = 0; i < numPlayers; i++) {
				message[i] = "Player " + Integer.toString(i + 1) + ":";
				messagesAndFields[j] =  message[i];
				j++;
				textFields[i] = new JTextField();
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
