package cop701.common;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class GameStateTest {

	@Test
	public void testGameStateInit() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		Square[] sq = gameState.getBoard();
		int countStar = 0;
		for (int i=0; i<sq.length; i++) {
			if (sq[i].getIsStar()) countStar++;
		}
		assertEquals(32, countStar);
	}

	@Test
	public void testGameStateEndgame() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		for (int i=0; i<57; i++)
			gameState.updatePiece(0, new Move(0,1));
		assertEquals(157, (int)gameState.getPieces()[0][0]);
		
		for (int i=0; i<57; i++)
			gameState.updatePiece(1, new Move(0,1));
		assertEquals(131, (int)gameState.getPieces()[1][0]);
	}
	
	@Test
	public void testGameStateValidateTwoPieceInStart() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		
		gameState.updatePiece(0, new Move(0,6));
		assertTrue(gameState.validateMove(0, new Move(1,1)));
	}
	
	@Test
	public void testGameStateValidateNoTwoPieceInSameSquare() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		
		gameState.updatePiece(0, new Move(0,6));
		gameState.updatePiece(0, new Move(0,1));
		gameState.updatePiece(0, new Move(1,6));
		assertFalse(gameState.validateMove(0, new Move(1,1)));
	}
	
	@Test
	public void testGameStateValidateTwoPieceInStarSquare() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		
		gameState.updatePiece(0, new Move(0,6));
		gameState.updatePiece(0, new Move(1,6));
		gameState.updatePiece(0, new Move(0,6));
		gameState.updatePiece(0, new Move(0,2));
		gameState.updatePiece(0, new Move(1,5));
		assertTrue(gameState.validateMove(0, new Move(1,3)));
		
		gameState.updatePiece(0, new Move(2,6));
		gameState.updatePiece(1, new Move(0,1));
		gameState.updatePiece(1, new Move(0,6));
		gameState.updatePiece(1, new Move(0,2));
		for (int i=0; i<33; i++) {
			gameState.updatePiece(0, new Move(2,1));			
		}
		assertTrue(gameState.validateMove(0, new Move(2,1)));
	}
	
	
	@Test
	public void testGameStateValidateCaptureOppPiece() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		
		gameState.updatePiece(0, new Move(0,6));
		gameState.updatePiece(1, new Move(0,1));
		gameState.updatePiece(1, new Move(0,6));
		gameState.updatePiece(1, new Move(0,1));
		for (int i=0; i<32; i++) {
			gameState.updatePiece(0, new Move(0,1));			
		}
		assertTrue(gameState.validateMove(0, new Move(0,1)));
		
		gameState.updatePiece(0, new Move(0,1));
		assertEquals(34, (int)gameState.getPieces()[0][0]);
		assertEquals(-1, (int)gameState.getPieces()[1][0]);
		assertEquals(1, gameState.getBoard()[34].getNoOfPieces()[0]);
		assertEquals(0, gameState.getBoard()[34].getNoOfPieces()[1]);
	}
	
	@Test
	public void testGameStateValidateCaptureOwnPiece() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		
		gameState.updatePiece(1, new Move(0,6));
		gameState.updatePiece(0, new Move(0,1));
		gameState.updatePiece(0, new Move(0,6));
		gameState.updatePiece(0, new Move(0,1));
		for (int i=0; i<32; i++) {
			gameState.updatePiece(1, new Move(0,1));			
		}
		
		gameState.updatePiece(1, new Move(0,1));
		assertEquals(8, (int)gameState.getPieces()[1][0]);
		assertEquals(-1, (int)gameState.getPieces()[0][0]);
		assertEquals(1, gameState.getBoard()[8].getNoOfPieces()[1]);
		assertEquals(0, gameState.getBoard()[8].getNoOfPieces()[0]);
	}
	
	@Test
	public void testGameStateValidateEndgame() {
		GameState gameState = new GameState(new HashMap<Integer, Color>());
		for (int i=0; i<56; i++)
			gameState.updatePiece(0, new Move(0,1));
		assertTrue(gameState.validateMove(0, new Move(0,1)));
		assertFalse(gameState.validateMove(0, new Move(0,2)));
		
		for (int i=0; i<51; i++)
			gameState.updatePiece(0, new Move(1,1));
		assertTrue(gameState.validateMove(0, new Move(1,6)));
		gameState.updatePiece(0, new Move(1,6));
		assertFalse(gameState.validateMove(0, new Move(1,1)));
		
		assertTrue(gameState.validateMove(0, new Move(0,1)));		
	}
	
}
