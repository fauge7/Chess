package com.group8.chess.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.group8.chess.piece.Bishop;
import com.group8.chess.piece.King;
import com.group8.chess.piece.Knight;
import com.group8.chess.piece.Pawn;
import com.group8.chess.piece.Piece;
import com.group8.chess.piece.Queen;
import com.group8.chess.piece.Rook;
import com.group8.chess.util.Board;
import com.group8.chess.util.Coordinate;

public class BoardFrame extends JPanel {
	
	static HashMap<String,BufferedImage> peices;
	public static Board b;
	static BufferedImage background;
	private GridLayout layout;
	private JTextArea console;
	public BoardFrame() {
		// TODO Auto-generated constructor stub
		peices = new HashMap<>();
		b = new Board();
		File board = new File("Images\\board.png");
		
		File pawnw = new File("Images\\Pawn_W.png");
		File bishw = new File("Images\\Bishop_W.png");
		File kingw = new File("Images\\King_W.png");
		File queew = new File("Images\\Queen_W.png");
		File knigw = new File("Images\\Knight_W.png");
		File rookw = new File("Images\\Rook_W.png");
		
		File pawnb = new File("Images\\Pawn_B.png");
		File bishb = new File("Images\\Bishop_B.png");
		File kingb = new File("Images\\King_B.png");
		File queeb = new File("Images\\Queen_B.png");
		File knigb = new File("Images\\Knight_B.png");
		File rookb = new File("Images\\Rook_B.png");
		
		try {
			peices.put("WPa", ImageIO.read(pawnw));
			peices.put("WBi", ImageIO.read(bishw));
			peices.put("WKi", ImageIO.read(kingw));
			peices.put("WQu", ImageIO.read(queew));
			peices.put("WKn", ImageIO.read(knigw));
			peices.put("WRo", ImageIO.read(rookw));
			
			peices.put("BPa", ImageIO.read(pawnb));
			peices.put("BBi", ImageIO.read(bishb));
			peices.put("BKi", ImageIO.read(kingb));
			peices.put("BQu", ImageIO.read(queeb));
			peices.put("BKn", ImageIO.read(knigb));
			peices.put("BRo", ImageIO.read(rookb));
			
			
			background = ImageIO.read(board);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console = new JTextArea();
		layout = new GridLayout(2, 1);
		add(console);
//		setLayout(layout);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		for(int x = 0; x < b.height; x++){
			for(int y = 0; y < b.width; y++){
				if(b.getPiece(x, y) != null){
					g.drawImage(getImage(b.getPiece(x,y)),59*x,59*y,null);
//					System.out.println(b.getPiece(x,y).toString());
//					g.drawImage(getImage("wRo"),0,0,null);
				}
			}	
		}
		g.setColor(Color.BLUE);
		if(InputHandler.lastClickCoord!= null){
			g.drawRect(InputHandler.lastClickCoord.getX()*59, InputHandler.lastClickCoord.getY()*59, 59, 59);
		}
		List<Coordinate> movelist = new ArrayList<Coordinate>();
		if(InputHandler.selectedPiece != null){
			Piece p = InputHandler.selectedPiece;
			if(p.toString().contains("Bi")){
				Bishop b = (Bishop) p;
				b.buildMoveList(null, null);
				movelist = b.getMoveList();
			}else if(p.toString().contains("Pa")){
				Pawn b = (Pawn) p;
				b.buildMoveList(null, null);
//				b.setMoveList(movelist);
				movelist = b.getMoveList();
			}else if(p.toString().contains("Ki")){
				King b = (King) p;
				b.buildMoveList(null, null);
				movelist = b.getMoveList();
			}else if(p.toString().contains("Qu")){
				Queen b = (Queen) p;
				b.buildMoveList(null, null);
				movelist = b.getMoveList();
			}else if(p.toString().contains("Ro")){
				Rook b = (Rook) p;
				b.buildMoveList(null, null);
				movelist = b.getMoveList();
			}else if(p.toString().contains("Kn")){
				Knight b = (Knight) p;
				b.buildMoveList(null, null);
				movelist = b.getMoveList();
			}
			g.setColor(Color.GREEN);
			for(Coordinate c : movelist){
				g.drawRect(c.getX()*59, c.getY()*59, 59, 59);
			}
		}
	}
	
	public static BufferedImage getImage(Piece p){
		return peices.get(p.toString());
	}
	public static BufferedImage getImage(String s){
		return peices.get(s);
	}
}
