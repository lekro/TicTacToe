package lekro.tictactoe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

public class Board implements Renderable {

	Piece[][] board;
	List<Renderable> bars;
	List<Renderable> pieces;
	boolean turn;
	double pieceSize;
	boolean gameOver = false;
	String winner;
	Quadrilateral winningQuad;
	
	public Board() {
		this(3);
	}
	
	public Board(int width) {
		if (width < 2) {
			throw new IllegalArgumentException("board must be at least 2 wide");
		}
		
		bars = new ArrayList<Renderable>();
		pieces = new ArrayList<Renderable>();
		board = new Piece[width][width];
		
		int numOfVBars = width - 1;
		pieceSize = 600.0 / width;
		for (int i = 1; i <= numOfVBars; i++) {
			bars.add(new Quadrilateral(0, pieceSize * i - 5, 600, pieceSize * i - 5, 600, pieceSize * i + 5, 0, pieceSize * i + 5, new Color(100, 100, 100, 255)));
		}
		for (int i = 1; i <= numOfVBars; i++) {
			bars.add(new Quadrilateral(pieceSize * i - 5, 0, pieceSize * i - 5, 600, pieceSize * i + 5, 600, pieceSize * i + 5, 0, new Color(100, 100, 100, 255)));
		}
		
		turn = true;
	}
	
	@Override
	public void renderGL() {
		//updateWinningQuad();
		if (winningQuad != null) {
			winningQuad.renderGL();
		}
		for (Renderable r : bars) {
			r.renderGL();
		}
		for (Renderable r : pieces) {
			r.renderGL();
		}
		if (winningQuad != null) {
			endGame();
		}
	}
	
	public void pollMouse() {

		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = Mouse.getY();
			int xArr = (int) (x / pieceSize);
			int yArr = (int) (y / pieceSize);
			if (board[yArr][xArr] == null) {
				if (turn) {
					board[yArr][xArr] = Piece.X;
					pieces.add(new Cross(pieceSize * 19/20, new Color(255, 0, 0, 255), xArr * pieceSize + pieceSize / 2, yArr * pieceSize + pieceSize / 2));
				} else {
					board[yArr][xArr] = Piece.O;
					pieces.add(new Circle(pieceSize * 19/20, new Color(0, 0, 255, 255), xArr * pieceSize + pieceSize / 2, yArr * pieceSize + pieceSize / 2));
					
				}
				turn = !turn;
			}
			

			winner = checkWin();
			if (winner != null) {
				System.out.println(winner);
				if (winner.equalsIgnoreCase("O")) {
					winningQuad = new Quadrilateral(0, 0, 0, 600, 600, 600, 600, 0, new Color(0, 0, 100, 0));
					
				}
				else if (winner.equalsIgnoreCase("x")) {
					winningQuad = new Quadrilateral(0, 0, 0, 600, 600, 600, 600, 0, new Color(100, 0, 0, 0));
					
				}
				else {
					winningQuad = new Quadrilateral(0, 0, 0, 600, 600, 600, 600, 0, new Color(50, 50, 50, 0));
					
				}
				
			}
		}
	}
	
	public String checkWin() {
		for (int i = 0; i < board.length; i++) {
			
			//Detect a horizontal win in row i
			Piece myPiece = board[i][0];
			if (myPiece == null) {
				continue;
			}
			boolean horizWin = true;
			for (int j = 1; j < board[i].length; j++) {

				if (board[i][j] == null) {
					horizWin = false;
					break;
				}
				if (!board[i][j].equals(myPiece)) {
					horizWin = false;
				}
			}
			if (horizWin) {
				return myPiece.toString();
			}
		}
		for (int i = 0; i < board.length; i++) {
			
			//Detect a vertical win in row i
			Piece myPiece = board[0][i];
			if (myPiece == null) {
				continue;
			}
			boolean vertWin = true;
			for (int j = 1; j < board[i].length; j++) {
				if (board[j][i] == null) {
					vertWin = false;
					break;
				}
				if (!board[j][i].equals(myPiece)) {
					vertWin = false;
				}
			}
			if (vertWin) {
				return myPiece.toString();
			}
		}
		
		//Detect a diagonal win
		Piece piece1 = board[0][0];
		
		boolean diagWin1 = true;
		for (int i = 1; i < board.length; i++) {
			if (piece1 == null) {
				diagWin1 = false;
				break;
			}
			if (board[i][i] == null) {
				diagWin1 = false;
				break;
			}
			if (!board[i][i].equals(piece1)) {
				diagWin1 = false;
			}
		}
		if (diagWin1) {
			return piece1.toString();
		}
		Piece piece2 = board[0][board.length - 1];
		boolean diagWin2 = true;
		for (int i = 1; i < board.length; i++) {
			if (piece1 == null) {
				diagWin2 = false;
				break;
			}
			if (board[i][board.length - i - 1] == null) {
				diagWin2 = false;
				break;
			}
			if (!board[i][board.length - i - 1].equals(piece2)) {
				diagWin2 = false;
			}
		}
		if (diagWin2) {
			return piece1.toString();
		}
		
		boolean full = true;
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if (board[i][j] == null) {
					full = false;
				}
			}
		}
		if (full) {
			return "Stalemate!";
		}
		
		
		return null;
	}
	
	public void endGame() {
		gameOver = true;
	}
	
	public boolean gameOver() {
		return gameOver;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public void updateWinningQuad() {
		
		if (winningQuad == null) {
			return;
		}
		Color color = winningQuad.getColor();
		Color newColor;
		if (color.getAlpha() < 255) {
			newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() + 1);
			
		} else {
			newColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
			
		}
		Quadrilateral newQuad = new Quadrilateral(winningQuad.getPoints(), newColor);
		winningQuad = newQuad;
	}
	
}
