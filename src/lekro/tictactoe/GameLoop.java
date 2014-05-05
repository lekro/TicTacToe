package lekro.tictactoe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class GameLoop {

	public List<Renderable> objects;
	Board board;
	
	public GameLoop() {
		objects = new ArrayList<Renderable>();
		board = new Board(3);
		objects.add(board);
	}
	
	public void loop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		if (!board.gameOver()) {

			pollInput();
		}
		for (Renderable q : objects) {
			q.renderGL();
		}
		Display.update();
	}
	
	public void pollInput() {
		board.pollMouse();
	}
	
	
}

