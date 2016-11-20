package com.group8.chess.client;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.group8.chess.piece.Piece;
import com.group8.chess.util.Board;
import com.group8.chess.util.Coordinate;

public class ClientUI {
	private Board board;
	private boolean highlight[][];
	
	private Coordinate selectedCoor = null;
	
	private int boardSize, pieceSize;
	private Map<String, BufferedImage> imageMap = new HashMap<>(); 
	private final File PATH = new File(System.getProperty("user.dir") + "\\img");

	private JFrame frame;
	private JLabel boardLbl;
	
	public ClientUI(Board board) {
		this.board = board;
		highlight = new boolean[board.width][board.height];
		
		try {
			buildImageMap();
			buildDisplay();
			updateDisplay();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Path=" + PATH);
		}
	}
		
		//board.getPiece(1,1).move(3,3);

	private void buildImageMap() throws IOException {

		// Build imageMap
		imageMap.put("board", ImageIO.read(new File(PATH, "board.png")));
		imageMap.put("highlight", ImageIO.read(new File(PATH, "highlight.png")));
		//white pieces
		imageMap.put("WPa", ImageIO.read(new File(PATH, "whitepawn.png")));
		imageMap.put("WRo", ImageIO.read(new File(PATH, "whiterook.png")));
		imageMap.put("WBi", ImageIO.read(new File(PATH, "whitebishop.png")));
		imageMap.put("WKn", ImageIO.read(new File(PATH, "whiteknight.png")));
		imageMap.put("WQu", ImageIO.read(new File(PATH, "whitequeen.png")));
		imageMap.put("WKi", ImageIO.read(new File(PATH, "whiteking.png")));
		//black pieces
		imageMap.put("BPa", ImageIO.read(new File(PATH, "blackpawn.png")));
		imageMap.put("BRo", ImageIO.read(new File(PATH, "blackrook.png")));
		imageMap.put("BBi", ImageIO.read(new File(PATH, "blackbishop.png")));
		imageMap.put("BKn", ImageIO.read(new File(PATH, "blackknight.png")));
		imageMap.put("BQu", ImageIO.read(new File(PATH, "blackqueen.png")));
		imageMap.put("BKi", ImageIO.read(new File(PATH, "blackking.png")));
		
		boardSize = imageMap.get("board").getWidth();
		pieceSize = imageMap.get("WPa").getWidth();
		
	}
	
	private void buildDisplay() {
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(520,520);
        boardLbl = new JLabel();
        boardLbl.addMouseListener(new MListener(this));

        frame.add(boardLbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void updateDisplay() {
		BufferedImage combined = new BufferedImage(boardSize, boardSize, BufferedImage.TYPE_INT_ARGB);
		Graphics g = combined.getGraphics();
		g.drawImage(imageMap.get("board"), 0, 0, null);

		// Draw Highlights
		for (int x = 0; x < board.width; x++) {
			for (int y = 0; y < board.height; y++) {
				if (highlight[x][y]) {
					g.drawImage(imageMap.get("highlight"), 
							x * pieceSize,
							(7 - y) * pieceSize,
							null
							);
				}
			}
		}
		
		// Draw Pieces
		for (Piece piece: board.getPieces()) {
			System.out.println();
			g.drawImage(imageMap.get(piece.getName()), 
					piece.getPos().getX() * pieceSize,
					(7 - piece.getPos().getY()) * pieceSize,
					null
					);
		}
	
		boardLbl.setIcon(new ImageIcon(combined));
		imageMap.put("display", combined);
		
		//Generates screenshot.png after each display update. 
		try {
			ImageIO.write(combined, "PNG", new File(PATH, "screenshot.png"));
		} catch (IOException e) {
			// Print error and ignore.
			e.printStackTrace();
		}
	}

	public void MouseUp(MouseEvent e) {
		Coordinate coor = new Coordinate(e.getX() / pieceSize, 7 - (e.getY() / pieceSize));
		if (!board.inBounds(coor)) return;
		
		Piece piece = board.getPiece(coor);
		
		if (e.getButton() == 3) {
			highlight[coor.getX()][coor.getY()] = !highlight[coor.getX()][coor.getY()]; 
			updateDisplay();
			return;
		}
		
		if (selectedCoor == null) {
			selectedCoor = piece.getPos();
			if (piece != null) {
				board.buildMoveList(piece.getPlayerColor());
				clearHighlights();
				for (Coordinate highCoor: piece.getMoveList()) {
					highlight[highCoor.getX()][highCoor.getY()] = true;
				}

			}
		} else {
			if (piece != null && coor.equals(selectedCoor)) {
				selectedCoor = null;
			} else {
				board.getPiece(selectedCoor).move(coor);
				selectedCoor = null;
				updateDisplay();
			}
			clearHighlights();
		}
		updateDisplay();
	}
	
	private void clearHighlights() {
		highlight = new boolean[board.width][board.height];
	}
	
}
