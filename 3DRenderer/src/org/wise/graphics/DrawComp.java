package org.wise.graphics;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public class DrawComp extends JComponent {	private static final long serialVersionUID = 5507894481934691627L;

	private Window window;

	public DrawComp(Window window) {
		this.window = window;
	}

	public void paintComponent(Graphics g) {
		
		for(Point2D point : this.window.getPoints2D())
			try {
				g.fillRect((int) (point.getX()) + (this.getWidth()/2) - 1, (this.getHeight()/2) - (int) (point.getY()) - 1, 3, 3);
			}catch(NullPointerException e) {
				
			}
	}
	
}
