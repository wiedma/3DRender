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
			g.drawRect((int) point.getX() - 1, (int) point.getY() - 1, 3, 3);
			
		
	}
	
}
