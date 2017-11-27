package ece373.catan.board;

import ece373.catan.game.*;
import ece373.catan.player.*;
import java.util.ArrayList;


public class Board {

	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	private BoardGUI gui;
	
	private Game game;
	
	public Board(Game g) {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		
		this.game = g;
		
		setupNodes();
		setupEdges();
		
		this.gui = new BoardGUI(this, g);
	}
	
	private void setupNodes() {
		for (int i = 0; i < 54; i++) {
			nodes.add(new Node());
		}
	}
	
	private void setupEdges() {
		// Row 1
		edges.add(new Edge(nodes.get(0), nodes.get(3)));
		edges.add(new Edge(nodes.get(0), nodes.get(4)));
		edges.add(new Edge(nodes.get(1), nodes.get(4)));
		edges.add(new Edge(nodes.get(1), nodes.get(5)));
		edges.add(new Edge(nodes.get(2), nodes.get(5)));
		edges.add(new Edge(nodes.get(2), nodes.get(6)));
		
		// Row 2
		edges.add(new Edge(nodes.get(3), nodes.get(7)));
		edges.add(new Edge(nodes.get(4), nodes.get(8)));
		edges.add(new Edge(nodes.get(5), nodes.get(9)));
		edges.add(new Edge(nodes.get(6), nodes.get(10)));
		
		// Row 3
		edges.add(new Edge(nodes.get(7), nodes.get(11)));
		edges.add(new Edge(nodes.get(7), nodes.get(12)));
		edges.add(new Edge(nodes.get(8), nodes.get(12)));
		edges.add(new Edge(nodes.get(8), nodes.get(13)));
		edges.add(new Edge(nodes.get(9), nodes.get(13)));
		edges.add(new Edge(nodes.get(9), nodes.get(14)));
		edges.add(new Edge(nodes.get(10), nodes.get(14)));
		edges.add(new Edge(nodes.get(10), nodes.get(15)));
		
		// Row 4
		edges.add(new Edge(nodes.get(11), nodes.get(16)));
		edges.add(new Edge(nodes.get(12), nodes.get(17)));
		edges.add(new Edge(nodes.get(13), nodes.get(18)));
		edges.add(new Edge(nodes.get(14), nodes.get(19)));
		edges.add(new Edge(nodes.get(15), nodes.get(20)));
		
		// Row 5
		edges.add(new Edge(nodes.get(16), nodes.get(21)));
		edges.add(new Edge(nodes.get(16), nodes.get(22)));
		edges.add(new Edge(nodes.get(17), nodes.get(22)));
		edges.add(new Edge(nodes.get(17), nodes.get(23)));
		edges.add(new Edge(nodes.get(18), nodes.get(23)));
		edges.add(new Edge(nodes.get(18), nodes.get(24)));
		edges.add(new Edge(nodes.get(19), nodes.get(24)));
		edges.add(new Edge(nodes.get(19), nodes.get(25)));
		edges.add(new Edge(nodes.get(20), nodes.get(25)));
		edges.add(new Edge(nodes.get(20), nodes.get(26)));
		
		// Row 6
		edges.add(new Edge(nodes.get(21), nodes.get(27)));
		edges.add(new Edge(nodes.get(22), nodes.get(28)));
		edges.add(new Edge(nodes.get(23), nodes.get(29)));
		edges.add(new Edge(nodes.get(24), nodes.get(30)));
		edges.add(new Edge(nodes.get(25), nodes.get(31)));
		edges.add(new Edge(nodes.get(26), nodes.get(32)));
		
		// Row 7
		edges.add(new Edge(nodes.get(27), nodes.get(33)));
		edges.add(new Edge(nodes.get(28), nodes.get(33)));
		edges.add(new Edge(nodes.get(28), nodes.get(34)));
		edges.add(new Edge(nodes.get(29), nodes.get(34)));
		edges.add(new Edge(nodes.get(29), nodes.get(35)));
		edges.add(new Edge(nodes.get(30), nodes.get(35)));
		edges.add(new Edge(nodes.get(30), nodes.get(36)));
		edges.add(new Edge(nodes.get(31), nodes.get(36)));
		edges.add(new Edge(nodes.get(31), nodes.get(37)));
		edges.add(new Edge(nodes.get(32), nodes.get(37)));
		
		// Row 8
		edges.add(new Edge(nodes.get(33), nodes.get(38)));
		edges.add(new Edge(nodes.get(34), nodes.get(39)));
		edges.add(new Edge(nodes.get(35), nodes.get(40)));
		edges.add(new Edge(nodes.get(36), nodes.get(41)));
		edges.add(new Edge(nodes.get(37), nodes.get(42)));
		
		// Row 9
		edges.add(new Edge(nodes.get(38), nodes.get(43)));
		edges.add(new Edge(nodes.get(39), nodes.get(43)));
		edges.add(new Edge(nodes.get(39), nodes.get(44)));
		edges.add(new Edge(nodes.get(40), nodes.get(44)));
		edges.add(new Edge(nodes.get(40), nodes.get(45)));
		edges.add(new Edge(nodes.get(41), nodes.get(45)));
		edges.add(new Edge(nodes.get(41), nodes.get(46)));
		edges.add(new Edge(nodes.get(42), nodes.get(46)));
		
		// Row 10
		edges.add(new Edge(nodes.get(43), nodes.get(47)));
		edges.add(new Edge(nodes.get(44), nodes.get(48)));
		edges.add(new Edge(nodes.get(45), nodes.get(49)));
		edges.add(new Edge(nodes.get(46), nodes.get(50)));
		
		// Row 11
		edges.add(new Edge(nodes.get(47), nodes.get(51)));
		edges.add(new Edge(nodes.get(48), nodes.get(51)));
		edges.add(new Edge(nodes.get(48), nodes.get(52)));
		edges.add(new Edge(nodes.get(49), nodes.get(52)));
		edges.add(new Edge(nodes.get(49), nodes.get(53)));
		edges.add(new Edge(nodes.get(50), nodes.get(53)));	
	}
	
	public void addNode(Node n) {
		nodes.add(n);
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public BoardGUI getGUI() {
		return gui;
	}
	
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public ArrayList<Edge> getAvailableEdgesFor(Player player) {
		ArrayList<Edge> availableEdges = new ArrayList<Edge>();
		
		for (Edge e: edges) {
			if (e.canBeBuiltOnBy(player)) {
				availableEdges.add(e);
			}
		}
		
		return availableEdges;
	}
	
	public ArrayList<Node> getAvailableNodesForSettlementsFor(Player player) {
		ArrayList<Node> availableNodes = new ArrayList<Node>();
		
		for (Node n: nodes) {
			if (n.canBeBuiltOnBy(player)) {
				availableNodes.add(n);
			}
		}
		
		return availableNodes;
	}
	
	public ArrayList<Node> getAvailableNodesForCitiesFor(Player player) {
		ArrayList<Node> availableNodes = new ArrayList<Node>();
		
		for (Node n: nodes) {
			if (n.getSettlement() != null && n.getSettlement().getPlayer() == player) {
				availableNodes.add(n);
			}
		}
		
		return availableNodes;
	}
	
	public ArrayList<Node> getAvailableNodesForSettlementsForStart() {
		
		ArrayList<Node> availableNodes = new ArrayList<Node>();
		
		for (Node n: nodes) {
			if (n.canBeBuiltOnAtStart()) {
				availableNodes.add(n);
			}
		}
		
		return availableNodes;
	}
	
	public void buildSettlementAtStart() {
		gui.setTakeResources(false);
		gui.showAvailableNodes(getAvailableNodesForSettlementsForStart());
	}
	
	public void buildRoadAtStartWithPlayer(Player p) {
		gui.setTakeResources(false);
		gui.showAvailableEdges(this.getAvailableEdgesFor(p));
	}
	
	public void buildRoadWithPlayer(Player p) {
		gui.showAvailableEdges(this.getAvailableEdgesFor(p));
	}
	
	public void buildSettlementWithPlayer(Player p) {
		gui.showAvailableNodes(this.getAvailableNodesForSettlementsFor(p));
	}
	
	public void buildCityWithPlayer(Player p) {
		gui.showAvailableNodes(this.getAvailableNodesForCitiesFor(p));
	}
	
	public void moveRobber() {
		gui.showAvailableTilesForRobber();
	}
	
	public void dealResourceCardsForRoll(int roll) {
		gui.dealResourceCardsForRoll(roll);
	}
}

