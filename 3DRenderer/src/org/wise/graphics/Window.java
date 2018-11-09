package org.wise.graphics;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import org.wise.math.Vector;
import org.wise.world.WorldSpace;

public class Window extends JFrame implements KeyListener{ private static final long serialVersionUID = 5325798843479656003L;
	
	private DrawComp dc;
	
	private Camera camera;
	
	public Window(Camera camera) {
		super();
		
		this.camera = camera;
		
		this.addKeyListener(this);
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

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			camera.getPosition().increment(new Vector(-1, 0, 0));
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			camera.getPosition().increment(new Vector(1, 0, 0));
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			camera.getPosition().increment(new Vector(0, 1, 0));
		}
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			camera.getPosition().increment(new Vector(0, -1, 0));
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			camera.getPosition().increment(new Vector(0, 0, -1));
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			camera.getPosition().increment(new Vector(0, 0, 1));
		}
		
		repaint();
	}

	public void keyReleased(KeyEvent e) {
	}
	
}
