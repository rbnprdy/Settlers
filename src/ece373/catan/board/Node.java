package ece373.catan.board;

import java.util.ArrayList;

import ece373.catan.player.*;

public class Node {

	private ArrayList<Edge> edges;
	private Settlement settlement;
	private City city;
	private NodeGUI gui;
	
	public Node() {
		edges = new ArrayList<Edge>();
		settlement = null;
		city = null;
		gui = null;
	}
	
	public void setGUI(NodeGUI g) {
		gui = g;
	}
	
	public NodeGUI getGUI() {
		return gui;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public void setSettlement(Settlement s) {
		settlement = s;
	}
	
	public Settlement getSettlement() {
		return settlement;
	}
	
	public Player getPlayer() {
		if (city != null) {
			return city.getPlayer();
		} else if (settlement != null) {
			return settlement.getPlayer();
		} else {
			return null;
		}
	}
	
	public boolean canBeBuiltOnBy(Player player) {
		
		if (getPlayer() != null) {
			return false;
		}
		
		boolean connectedRoad = false;
		
		for (Edge e: edges) {
			if (e.getRoad() != null && e.getRoad().getPlayer() == player) {
				connectedRoad = true;
			}
			if (e.getNode1().getPlayer() != null || e.getNode2().getPlayer() != null) {
				return false;
			}
		}
		
		return connectedRoad;
	}
	
	public boolean canBeBuiltOnAtStart() {
		
		if (getPlayer() != null) {
			return false;
		}
		
		for (Edge e: edges) {
			if (e.getNode1().getPlayer() != null || e.getNode2().getPlayer() != null) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Sets this node's city to c, and returns the node's settlement to null.
	 * @param c	the city to object that belongs to the player building on this node.
	 */
	public void setCity(City c) {
		settlement = null;
		city = c;
	}
	
	public City getCity() {
		return city;
	}
}
