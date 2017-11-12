package ece373.catan.board;

import java.util.ArrayList;

public class Board {

	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	public Board() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
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
	
	public ArrayList<Edge> getEdges() {
		return edges;
	}
}

