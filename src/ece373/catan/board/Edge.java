package ece373.catan.board;

import ece373.catan.player.*;

/*
 * @author Ruben Purdy
 * @date November 4, 2017
 * 
 * This class represents edge in the game board. It contains two nodes (which should never be null) 
 * and a road object (which can be null if no road is currently placed on the edge)
 */
public class Edge {
	
	
	private Node node1, node2;
	
	private Road road;
	
	public Edge(Node n1, Node n2) {
		node1 = n1;
		node2 = n2;
		n1.addEdge(this);
		n2.addEdge(this);
	}
	
	public Node getNode1() {
		return node1;
	}
	
	public Node getNode2() {
		return node2;
	}
	
	public Road getRoad() {
		return road;
	}
	
	public void setRoad(Road r) {
		this.road = r;
	}
	
	public Player getPlayer() {
		if (road != null) {
			return road.getPlayer();
		} else {
			return null;
		}
	}
	
	public boolean canBeBuiltOnBy(Player player) {
		
		// If this is already built on
		if (road != null) {
			return false;
		}
		
		// If it is adjacent to a settlement
		if (node1.getPlayer() == player || node2.getPlayer() == player) {
			return true;
		}
		
		// If it is adjacent to roads
		if (node1.getPlayer() == null) {
			for (Edge e: node1.getEdges()) {
				if (e.getRoad() != null && e.getRoad().getPlayer() == player) {
					return true;
				}
			}
		} 
		
		if (node2.getPlayer() == null) {
			for (Edge e: node2.getEdges()) {
				if (e.getRoad() != null && e.getRoad().getPlayer() == player) {
					return true;
				}
			}
		}
		
		return false;
	}
}
