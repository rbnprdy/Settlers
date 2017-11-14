package ece373.catan.board;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ece373.catan.player.Player;

class BoardTest {
	
	private Board b;
	private Player p;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		b = new Board();
		p = new Player("Test");
	}

	@AfterEach
	void tearDown() throws Exception {
		b = null;
        p = null;
	}

	@Test
	public void testGetAvailableEdgesOne() {
		
		b.getNodes().get(18).setSettlement(new Settlement(p));
		
		ArrayList<Edge> availableEdges = b.getAvailableEdgesFor(p);
		
		assert(availableEdges.size() == 3);
		assert(availableEdges.get(0) == b.getEdges().get(20));
		assert(availableEdges.get(1) == b.getEdges().get(27));
		assert(availableEdges.get(2) == b.getEdges().get(28));
	}
	
	@Test
	public void testGetAvailableEdgesTwo() {
		
		b.getNodes().get(18).setSettlement(new Settlement(p));
		b.getNodes().get(47).setCity(new City(p));
		
		b.getEdges().get(62).setRoad(new Road(p));
		
		ArrayList<Edge> availableEdges = b.getAvailableEdgesFor(p);
		
		assert(availableEdges.size() == 6);
		assert(availableEdges.get(0) == b.getEdges().get(20));
		assert(availableEdges.get(1) == b.getEdges().get(27));
		assert(availableEdges.get(2) == b.getEdges().get(28));
		assert(availableEdges.get(3) == b.getEdges().get(54));
		assert(availableEdges.get(4) == b.getEdges().get(55));
		assert(availableEdges.get(5) == b.getEdges().get(66));
	}
	
	@Test
	public void testGetAvailableNodesForSettlements() {
		
		b.getNodes().get(18).setSettlement(new Settlement(p));
		b.getNodes().get(47).setCity(new City(p));
		
		b.getEdges().get(62).setRoad(new Road(p));
		b.getEdges().get(55).setRoad(new Road(p));
		
		ArrayList<Node> availableNodes = b.getAvailableNodesForSettlementsFor(p);
		
		assert(availableNodes.size() == 1);
		assert(availableNodes.get(0) == b.getNodes().get(39));
	}
	
	@Test
	public void testGetAvailableNodesForCities() {
		
		b.getNodes().get(18).setSettlement(new Settlement(p));
		b.getNodes().get(47).setCity(new City(p));
		
		b.getEdges().get(62).setRoad(new Road(p));
		b.getEdges().get(55).setRoad(new Road(p));
		
		ArrayList<Node> availableNodes = b.getAvailableNodesForCitiesFor(p);
		
		assert(availableNodes.size() == 1);
		assert(availableNodes.get(0) == b.getNodes().get(18));
	}

}
