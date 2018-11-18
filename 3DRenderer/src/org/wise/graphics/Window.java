package org.wise.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import org.wise.math.Point3D;
import org.wise.world.WorldSpace;

public class Window extends JFrame implements KeyListener, ComponentListener, GraphicObject { private static final long serialVersionUID = 5325798843479656003L;
	
	protected DrawComp dc;
	
	private Camera camera;
	
	private WindowDraufsicht windowDraufsicht;
	
	private ArrayList<Point2D> screenPoints = new ArrayList<Point2D>();
	
	public Window() {
		this(new Camera(new Point3D(0, 0, 0, false)));
	}
	
	public Window(Camera camera) {
		super();
		
		this.camera = camera;
		
		this.addKeyListener(this);
		this.setTitle("The wise 3D Renderer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addComponentListener(this);
		
		dc = new DrawComp(this);
		dc.setPreferredSize(new Dimension(500, 500));
		this.add(dc);
		
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public ArrayList<Point2D> getPoints2D() {
		return screenPoints;
	}
	
	public void draw(Camera camera, Window window, Graphics g) {
		WorldSpace.draw(camera, window, g);
	}
	
	public void registerPoint(Point2D p){
		screenPoints.add(p);
	}
	
	
	public void generateDraufsicht() {
		if(this.windowDraufsicht != null)
			return;
		
		this.windowDraufsicht = new WindowDraufsicht(this);
	}
	
	private void repaintEverything() {
		this.repaint();
		if(this.windowDraufsicht != null)
			this.windowDraufsicht.repaint();
	}
	
	public void switchCamera(Camera c) {
		this.camera = c;
		c.updatePixelPerUnit(this);
	}
	
	//////////////////////////////////
	//////////   GETTERS    //////////
	//////////////////////////////////

	public Window getWindowMain() {
		return this;
	}
	
	public Camera getCamera() {
		return this.camera;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			camera.getPosition().increment(camera.getRight().scalar(-1));
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			camera.getPosition().increment(camera.getRight());
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			camera.getPosition().increment(camera.getUp());
		}
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			camera.getPosition().increment(camera.getUp().scalar(-1));
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			camera.getPosition().increment(camera.getForward());
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			camera.getPosition().increment(camera.getForward().scalar(-1));
		}
		
		if(e.getKeyCode() == KeyEvent.VK_MINUS) {
			camera.setFOV(camera.getFOV()+(Math.PI/100), this);			
		}
		if(e.getKeyCode() == KeyEvent.VK_PLUS) {
			camera.setFOV(camera.getFOV()-(Math.PI/100), this);			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			camera.rotate(camera.getUp(), -(Math.PI/100));
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			camera.rotate(camera.getUp(), (Math.PI/100));
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			camera.rotate(camera.getRight(), (Math.PI/100));
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			camera.rotate(camera.getRight(), -(Math.PI/100));
		}
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			camera.rotate(camera.getForward(), (Math.PI/100));
		}
		if(e.getKeyCode() == KeyEvent.VK_E) {
			camera.rotate(camera.getForward(), -(Math.PI/100));
		}
		
		this.repaintEverything();
	}

	public void keyReleased(KeyEvent e) {
	}
	
	public DrawComp getDrawComp() {
		return this.dc;
	}
	
	
	public class WindowDraufsicht extends Window { private static final long serialVersionUID = -1009996815859457967L;
	
	
		private Window windowMain;
		
		public WindowDraufsicht(Window windowMain) {
			super(null);
			
			this.setTitle(this.getTitle() + " - Draufsicht");
			
			this.windowMain = windowMain;
			
			this.dc.setDrawFadenkreuz(true);
			
			this.pack();
			this.setLocationRelativeTo(windowMain);
		}
		
		public ArrayList<Point2D> getPoints2D() {
			ArrayList<Point3D> points3D = WorldSpace.getPoints();
			
			Point2D[] points2D = new Point2D[points3D.size()];
			for(int i = 0; i < points2D.length; i++) {
				points2D[i] = new Point2D.Double(points3D.get(i).getX() - this.windowMain.getCamera().getPosition().getX(), -points3D.get(i).getZ() + this.windowMain.getCamera().getPosition().getZ());
			}
			return new ArrayList<Point2D>(Arrays.asList(points2D));
		}
		
		public Window getWindowMain() {
			return this.windowMain;
		}
		
		public void keyPressed(KeyEvent e) {
			windowMain.keyPressed(e);
		}
		
	}


	public void componentResized(ComponentEvent e) {
		if(this.camera == null)
			return;
		this.camera.updatePixelPerUnit(this);		
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}
	
	
}
