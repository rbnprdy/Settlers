package ece373.catan.player;

import static org.junit.Assert.*;
import ece373.catan.board.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testBuild() {
		Player p1 = new Player("My Dude");
		Board b1 = new Board();
		Node n1 = new Node();
		Node n2 = new Node();
		Edge e1 = new Edge(n1, n2);
		b1.addNode(n1);
		b1.addNode(n2);
		b1.addEdge(e1);

		
		p1.buildRoad(b1, 0);
		assertNotNull(b1.getEdges().get(0).getRoad()); //TESTING IF ROAD IS PLACED
		
		p1.buildSettlement(b1, 0);
		p1.buildSettlement(b1, 1);
		
		assertNotNull(b1.getNodes().get(0).getSettlement()); //TESTING IF SETTLEMENTS ARE PLACED
		assertEquals(b1.getNodes().get(0).getSettlement().getPlayer(), p1);
		assertNotNull(b1.getNodes().get(1).getSettlement());
		assertEquals(b1.getNodes().get(1).getSettlement().getPlayer(), p1);
		
		p1.buildCity(b1, 0);

		assertNotNull(b1.getNodes().get(0).getCity()); //TESTING IF CITY IS PLACED
		assertEquals(b1.getNodes().get(0).getCity().getPlayer(), p1);
		
		assertNull(b1.getNodes().get(0).getSettlement());
	}

}
