package ece373.catan.board;

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
}
