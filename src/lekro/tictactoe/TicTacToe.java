package lekro.tictactoe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.Format;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class TicTacToe {

	public static void main(String[] args) throws LWJGLException, IOException {
		Display.setDisplayMode(new DisplayMode(600, 600));
		Display.create();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 600, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GameLoop loop = new GameLoop();
		
		while (!Display.isCloseRequested()) {
			loop.loop();
		}
		Display.destroy();
	}
	
}
