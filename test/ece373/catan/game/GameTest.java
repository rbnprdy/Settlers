package ece373.catan.game;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import ece373.catan.player.*;
import ece373.catan.board.*;
import ece373.catan.card.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	
	private Player p1, p2, p3, p4;
	private Game game;
	
	
	@BeforeEach
	void setUp() throws Exception {
		p1 = new Player("Kray");
		p2 = new Player("Elan");
		p3 = new Player("Ruben");
		p4 = new Player("Marefat");
		game = new Game();
		
		game.addPlayer(p1);
		game.addPlayer(p2);
		game.addPlayer(p3);
		game.addPlayer(p4);
		game.setCurrentPlayer(game.getPlayers().get(0));
		game.setNumberPlayers(4);
	}

	@AfterEach
	void tearDown() throws Exception {
		p1 = null;
		p2 = null;
		p3 = null;
		game = null;
	}

	@Test
	final void testNextTurn() {
		
		game.getBoard().getNodes().get(14).setSettlement(new Settlement(game.getPlayers().get(0)));
		game.beginNextTurn();
		
		assert(game.getPlayers().get(0).getSettlements().size() == 2);
		assert(game.getPlayers().get(0).getRoads().size() == 4);
		assert(game.getPlayers().get(0).getCities().size() == 1);
	}

}
