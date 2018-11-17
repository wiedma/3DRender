package org.wise.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public class DrawComp extends JComponent {	private static final long serialVersionUID = 5507894481934691627L;

	private Window window;

	private boolean drawFadenkreuz;
	
	public DrawComp(Window window) {
		this.window = window;
	}

	public void paintComponent(Graphics gOld) {
		Graphics2D g = (Graphics2D) gOld;
		
		for(Point2D point : this.window.getPoints2D()) {
			if(point == null)
				continue;
			g.fillRect((int) (point.getX()) + (this.getWidth()/2) - 1, (this.getHeight()/2) - (int) (point.getY()) - 1, 3, 3);
		}
		
		
//		Point2D point2, point3;
//		for(Point2D point : this.window.getPoints2D()) {
//			if(point == null)
//				continue;
//			
//			point2 = this.window.getPoints2D()[(int) (Math.random() * this.window.getPoints2D().length)];
//			if(point2 == null)
//				continue;
//			
//			point3 = this.window.getPoints2D()[(int) (Math.random() * this.window.getPoints2D().length)];
//			if(point3 == null)
//				continue;
//			
////			for(Point2D point2 : this.window.getPoints2D()) {
////				if(point2 == null)
////					continue;
//				
//			g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
//			
//			g.fillPolygon(
//					new int[]{(int) (point.getX()) + (this.getWidth()/2) - 1, (int) (point2.getX()) + (this.getWidth()/2) - 1, (int) (point3.getX()) + (this.getWidth()/2) - 1},
//					new int[]{(int) (point.getY()) + (this.getHeight()/2) - 1, (int) (point2.getY()) + (this.getHeight()/2) - 1, (int) (point3.getY()) + (this.getHeight()/2) - 1},
//					3);
////			g.draw(, (this.getHeight()/2) - (int) (point.getY()) - 1,
////					(int) (point2.getX()) + (this.getWidth()/2) - 1, (this.getHeight()/2) - (int) (point2.getY()) - 1
////				);
////			}
			
//		}
		
		if(this.drawFadenkreuz) {
			g.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
			g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
			
//			g.drawLine(this.getWidth()/2, this.getHeight()/2,
//					(int) ((this.getWidth()/2) + Math.tan(-window.getWindowMain().getCamera().getFOV()/2) * 50),
//					(int) ((this.getWidth()/2) + Math.tan(-window.getWindowMain().getCamera().getFOV()/2) * 50)
//				);
			
			
			
			AffineTransform defaultAT = g.getTransform();
			
			g.rotate(-window.getWindowMain().getCamera().getFOV()/2, this.getWidth()/2, this.getHeight()/2);			
			g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, -this.getHeight()-this.getWidth());
			
			g.rotate(window.getWindowMain().getCamera().getFOV(), this.getWidth()/2, this.getHeight()/2);			
			g.drawLine(this.getWidth()/2, this.getHeight()/2, this.getWidth()/2, -this.getHeight()-this.getWidth());
			
			g.setTransform(defaultAT);
//			
////					(int) ((this.getWidth()/2) + Math.tan(window.getWindowMain().getCamera().getFOV()/2) * this.getHeight()/2),
//					0
				
			
		}
	}
	
	public void setDrawFadenkreuz(boolean drawFadenkreuz) {
		this.drawFadenkreuz = true;		
	}
	
}
