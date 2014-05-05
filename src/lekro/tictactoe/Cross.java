package lekro.tictactoe;

import java.awt.Color;

public class Cross implements Renderable {

	private int width;
	private Color color;
	private int x;
	private int y;
	
	Quadrilateral one, two;
	
	public Cross(int width, Color color, int x, int y) {
		this.width = width;
		this.color = color;
		this.x = x;
		this.y = y;
		
		int halfWidth = width / 2;
		
		one = new Quadrilateral(x - halfWidth, y + halfWidth - 5, x - halfWidth + 5, y + halfWidth, x + halfWidth, y - halfWidth + 5, x + halfWidth - 5, y - halfWidth, color);
		two = new Quadrilateral(x - halfWidth + 5, y - halfWidth, x - halfWidth, y - halfWidth + 5, x + halfWidth - 5, y + halfWidth, x + halfWidth, y + halfWidth - 5, color);
	}
	
	public Cross(double width, Color color, double x, double y) {
		this((int)width, color, (int) x, (int)y);
	}

	@Override
	public void renderGL() {
		one.renderGL();
		two.renderGL();
		
	}
	
	
}
