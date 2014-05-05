package lekro.tictactoe;

import java.awt.Color;
import java.awt.geom.Point2D;

import org.lwjgl.opengl.GL11;

public class Quadrilateral implements Renderable {

	private Point2D corner1, corner2, corner3, corner4;
	private Color color;
	
	public Quadrilateral() {
		this(new Point2D.Double(-10.0, -10.0), new Point2D.Double(-10.0, 10.0), new Point2D.Double(10.0, 10.0), new Point2D.Double(10.0, -10.0), new Color(255, 255, 255, 255));
	}
	
	public Quadrilateral(Point2D[] points, Color color) {

		this(points[0], points[1], points[2], points[3], color);
	}
	
	public Quadrilateral(Point2D point1, Point2D point2, Point2D point3, Point2D point4, Color color) {
		this.corner1 = point1;
		this.corner2 = point2;
		this.corner3 = point3;
		this.corner4 = point4;
		this.color = color;
	}
	
	public Quadrilateral(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, Color color) {
		this(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2), new Point2D.Double(x3, y3), new Point2D.Double(x4, y4), color);
	}
	
	public void renderGL() {
		GL11.glColor4d(((double)color.getRed()) / 255, ((double)color.getGreen()) / 255, ((double)color.getBlue()) / 255, ((double)color.getAlpha()) / 255);
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, 1);
		GL11.glBegin(GL11.GL_QUADS);
			//GL11.glTexCoord2d(0.0, 0.0);
		    GL11.glVertex2d(corner1.getX(), corner1.getY());
		    //GL11.glTexCoord2d(1.0, 0.0);
		    GL11.glVertex2d(corner2.getX(), corner2.getY());
		    //GL11.glTexCoord2d(1.0, 1.0);
		    GL11.glVertex2d(corner3.getX(), corner3.getY());
		    //GL11.glTexCoord2d(0.0, 1.0);
		    GL11.glVertex2d(corner4.getX(), corner4.getY());
		GL11.glEnd();
	}
	
	public void setPoint1(Point2D point) {
		corner1 = point;
	}
	
	public void setPoint2(Point2D point) {
		corner2 = point;
	}
	
	public void setPoint3(Point2D point) {
		corner3 = point;
	}
	
	public void setPoint4(Point2D point) {
		corner4 = point;
	}
	
	public Point2D[] getPoints() {
		return new Point2D[]{corner1, corner2, corner3, corner4};
	}
	
	public void setPoints(Point2D[] points) {
		setPoint1(points[0]);
		setPoint2(points[1]);
		setPoint3(points[2]);
		setPoint4(points[3]);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
