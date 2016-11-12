package test.group8.chess;

import static org.junit.Assert.*;

import com.group8.chess.piece.Piece;
import com.group8.chess.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.group8.chess.util.Board;

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
	
	public void showBoard() {
		System.out.println("/---------------------------------\\");
		for (int y = board.height - 1; y > -1; y--) {
			System.out.print("|");
			for (int x = 0; x < board.width; x++) {
				Piece piece = board.getPiece(x,y);
				if (piece == null) System.out.print("    ");
				else System.out.print(" " + piece);
			}
			System.out.println(" |");
		}
		System.out.println("\\---------------------------------/");
	}
	
	public void showThreat() {
		Coordinate coor;
		Threat threat = board.getThreat();
		
		System.out.println("/---------------------------------\\");
		for (int y = board.height - 1; y > -1; y--) {
			System.out.print("|");
			for (int x = 0; x < board.width; x++) {
				coor = new Coordinate(x,y);
				Piece piece = board.getPiece(x,y);
				if (piece == null) {
					if (threat.getPos().contains(coor))
						System.out.print(" xxx");
					else
						System.out.print("    ");
				}
				else if (threat.getPos().contains(coor))
					System.out.print(" " + piece.toString().toUpperCase());
				else
					System.out.print(" " + piece);
			}
			System.out.println(" |");
		}
		System.out.println("\\---------------------------------/");
	}
	
}
