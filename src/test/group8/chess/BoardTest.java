package test.group8.chess;

import static org.junit.Assert.*;

import com.group8.chess.piece.King;
import com.group8.chess.piece.Knight;
import com.group8.chess.piece.Piece;
import com.group8.chess.piece.Queen;
import com.group8.chess.piece.Rook;
import com.group8.chess.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.group8.chess.util.Board;

// Tests various situations for piece placement on the board.
// Note: Several invalid moves are allowed to set up different tests.
public class BoardTest {
	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void board() {
		assertTrue("Piece count failed.", board.getPieces().size() == 32);
		assertTrue("White count failed.", board.getPieces(PlayerColor.WHITE).size() == 16);
		assertTrue("Black count failed.", board.getPieces(PlayerColor.BLACK).size() == 16);
	}
	
	@Test
	public void move() {
		Piece piece = board.getPiece(1,1);
		piece.move(3,3);
		assertSame("Move to Blank Failed.",piece,board.getPiece(3,3));
	}

	@Test
	public void pawnUpgrade() {
		Piece piece = board.getPiece(1,1);
		piece.move(1,7);
		assertTrue("Queen not at coor: " + board.getPiece(1,7), board.getPiece(1,7) instanceof Queen);
	}

	@Test
	public void capture() {
		Piece piece = board.getPiece(1,1); // Pawn
		Piece enemy = board.getPiece(1,6); // Enemy Pawn
		piece.move(enemy.getPos());
		assertTrue("Attacking piece lost.", board.getPieces().contains(piece));
		assertFalse("Defending piece still on board.", board.getPieces().contains(enemy));
	}
	
	
	@Test
	public void Threat() {
		board.buildMoveList(PlayerColor.WHITE);
		Piece king;
		int posCount = 22;
		assertTrue("Pos threat count off: " + board.getThreat().getPos().size() + "/" + posCount, board.getThreat().getPos().size() == posCount);
			
		board.getPiece(4,6).move(4,4);
		board.getPiece(5,6).move(5,5);
		king = board.getPiece(4,0);
		king.move(0,2);
		board.buildMoveList(PlayerColor.WHITE);
		assertTrue("Check Test Failure." , board.getCheckState() == CheckState.CHECK);
			
		king.move(4,6);
		board.buildMoveList(PlayerColor.WHITE);
		assertTrue("Check-Mate Test Failure:" + board.getCheckState(), board.getCheckState() == CheckState.CHECK_MATE);
	}
	
	// Check for state mates due to check-mate being impossible by both sides.
	@Test 
	public void NoCheckPossible() {
		CheckState state;
		board.getPieces().clear();
		new King(PlayerColor.WHITE, board, new Coordinate(2,2));
		new King(PlayerColor.BLACK, board, new Coordinate(4,4));
		
		state = board.buildMoveList(PlayerColor.WHITE);
		assertTrue(state == CheckState.STALE_MATE);

		new Knight(PlayerColor.WHITE, board, new Coordinate(5,5));
		new Knight(PlayerColor.BLACK, board, new Coordinate(6,6));

		state = board.buildMoveList(PlayerColor.WHITE);
		assertTrue(state == CheckState.STALE_MATE);
		
		board.getPieces().clear();
		new King(PlayerColor.WHITE, board, new Coordinate(2,2));
		new King(PlayerColor.BLACK, board, new Coordinate(4,4));
		new Rook(PlayerColor.WHITE, board, new Coordinate(5,5));

		state = board.buildMoveList(PlayerColor.WHITE);
		assertTrue(state != CheckState.STALE_MATE);
	}
	
	@Test
	public void Enpassant() {
		Piece wPn = board.getPiece(0,1);
		Piece bPn = board.getPiece(1,6);
		bPn.move(1,3);
		wPn.move(0,3);

		showBoard();
		System.out.println("En-Passant:" + board.getPassant());
		
	}
	
	public void showBoard() {
		System.out.println("/---------------------------------\\");
		for (int y = board.height - 1; y > -1; y--) {
			System.out.print("|");
			for (int x = 0; x < board.width; x++) {
				Piece piece = board.getPiece(x,y);
				if (piece == null) System.out.print("    ");
				else System.out.print(" " + piece.getName());
			}
			System.out.println(" |");
		}
		System.out.println("\\---------------------------------/");
	}

}
