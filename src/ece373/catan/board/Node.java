package ece373.catan.board;

import java.util.ArrayList;

public class Node {

	private ArrayList<Edge> edges;
	private Settlement settlement;
	private City city;
	
	public Node() {
		edges = new ArrayList<Edge>();
		settlement = null;
		city = null;
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
