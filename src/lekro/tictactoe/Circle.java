package lekro.tictactoe;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

public class Circle implements Renderable {


	private int width;
	private Color color;
	private int centerX;
	private int centerY;
	private int halfWidth;
	

	
	public Circle(int width, Color color, int x, int y) {
		this.width = width;
		this.color = color;
		this.centerX = x;
		this.centerY = y;
		
		halfWidth = width / 2;
		
	}
	
	public Circle(double width, Color color, double x, double y) {
		this((int)width, color, (int) x, (int)y);
	}
	
	@Override
	public void renderGL() {
		for (double currWidth = halfWidth; currWidth > halfWidth - 5; currWidth -= 0.01) {
			GL11.glColor4d(((double)color.getRed()) / 255, ((double)color.getGreen()) / 255, ((double)color.getBlue()) / 255, ((double)color.getAlpha()) / 255);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			for(int i =0; i <= 300; i++){
				double angle = 2 * Math.PI * i / 300;
				double x = Math.cos(angle);
				double y = Math.sin(angle);
				GL11.glVertex2d(centerX + (x * currWidth),centerY + (y * currWidth));
			}
			GL11.glEnd();
		}
	}
	
}
