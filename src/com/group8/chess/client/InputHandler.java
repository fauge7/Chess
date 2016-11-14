package com.group8.chess.client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.group8.chess.piece.Piece;
import com.group8.chess.util.Coordinate;

public class InputHandler  implements MouseListener{

	boolean selected;
	public static Piece selectedPiece;
	public static Coordinate lastClickCoord;
	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		if(selected && selectedPiece != null){
			BoardFrame.b.movePiece(selectedPiece.getPos(), new Coordinate(me.getX()/128,me.getY()/128));
			selected = false;
			lastClickCoord = null;
			selectedPiece = null;
		}
		else{
			lastClickCoord = new Coordinate(me.getX()/128, me.getY()/128);
			selectedPiece = BoardFrame.b.getPiece(me.getX()/128, me.getY()/128);
			selected = true;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
