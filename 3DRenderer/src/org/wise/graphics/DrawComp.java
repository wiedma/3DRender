package org.wise.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

import org.wise.math.Vector;

public class DrawComp extends JComponent {	private static final long serialVersionUID = 5507894481934691627L;

	private Window window;

	private boolean drawFadenkreuz;
	
	public DrawComp(Window window) {
		this.window = window;
	}

	public void paintComponent(Graphics gOld) {
		
		Graphics2D g = (Graphics2D) gOld;
		
		if(this.drawFadenkreuz) {
			
//			g.setColor(Color.RED);
//			
//			for(Point2D point : WorldSpace.schnittpunkte) {
//				if(point == null)
//					continue;
//				g.fillRect((int) (point.getX()) + (this.getWidth()/2) - 1, (this.getHeight()/2) + (int) (point.getY()) - 1, 3, 3);
//			}
//			g.setColor(Color.BLACK);
//			
//			WorldSpace.schnittpunkte = new ArrayList<Point2D>();
			
			g.drawLine(this.getWidth()/2, 0, this.getWidth()/2, this.getHeight());
			g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
			
			Vector forward = window.getWindowMain().getCamera().getForward();
			
			
			AffineTransform defaultAT = g.getTransform();
			
			g.rotate(-window.getWindowMain().getCamera().getFOV()/2, this.getWidth()/2, this.getHeight()/2);			
			g.drawLine(this.getWidth()/2, this.getHeight()/2,
					this.getWidth()/2 + (int)(forward.getX() * 2 * this.getWidth()), this.getHeight() + (int)(forward.getZ() * 2 * this.getHeight()));
			
			g.rotate(window.getWindowMain().getCamera().getFOV(), this.getWidth()/2, this.getHeight()/2);			
			g.drawLine(this.getWidth()/2, this.getHeight()/2,
					this.getWidth()/2 + (int)(forward.getX() * 2 * this.getWidth()), this.getHeight() + (int)(forward.getZ() * 2 * this.getHeight()));
			
			g.setTransform(defaultAT);
			
		}
		else {
			window.getAllPoints();
		}
		
		for(Point2D point : this.window.getPoints2D()) {
			if(point == null)
				continue;
			g.fillRect((int) (point.getX()) + (this.getWidth()/2) - 1, (this.getHeight()/2) - (int) (point.getY()) - 1, 3, 3);
		}
	}
	
	public void setDrawFadenkreuz(boolean drawFadenkreuz) {
		this.drawFadenkreuz = true;		
	}
	
}
