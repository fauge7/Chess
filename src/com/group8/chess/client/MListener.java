package com.group8.chess.client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MListener implements MouseListener {
	private ClientUI clientUI;
	
	public MListener(ClientUI clientUI) {
		this.clientUI = clientUI;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clientUI.MouseUp(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
