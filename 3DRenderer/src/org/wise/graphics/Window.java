package org.wise.graphics;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import org.wise.world.WorldSpace;

public class Window extends JFrame { private static final long serialVersionUID = 5325798843479656003L;
	
	private DrawComp dc;
	
	private Camera camera;
	
	public Window(Camera camera) {
		super();
		
		this.camera = camera;
		
		this.setTitle("The wise 3D Renderer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dc = new DrawComp(this);
		dc.setPreferredSize(new Dimension(500, 500));
		this.add(dc);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public Point2D[] getPoints2D() {
		return WorldSpace.getPoints2D(this.camera);
	}
	
	//////////////////////////////////
	//////////   GETTERS    //////////
	//////////////////////////////////

	
	public Camera getCamera() {
		return this.camera;
	}
	
}
