package org.wise.math;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import org.wise.graphics.Camera;
import org.wise.graphics.GraphicObject;
import org.wise.graphics.Window;
import org.wise.world.WorldSpace;

public class Point3D implements GraphicObject{
	private double x, y, z;
	private Point2D screenPoint;
	private boolean visible = true;
	
	public Point3D() {
		this(0, 0, 0);
	}
	
	public Point3D(boolean visible) {		
		this(0, 0, 0, visible);		
	}
	
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		WorldSpace.addObject(this);
	}
	
	public Point3D(double x, double y, double z, boolean visible) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.visible = visible;
		
		if(this.visible)
			WorldSpace.addObject(this);
	}
	
	public void increment(Vector v) {
		this.x += v.getX();
		this.y += v.getY();
		this.z += v.getZ();
	}
	
	public Vector toVector() {
		return new Vector(
				this.x,
				this.y,
				this.z
			);
	}
	
	public void draw(Camera camera, Window window, Graphics g) {
		
		Vector geradenAnker, geradenRichtung;
		Vector ebenenAnker, ebenenNormale;		
		Point3D cameraPos = camera.getPosition();
		ebenenAnker = camera.getPosition().toVector().increment(camera.getForward());
		ebenenNormale = camera.getForward();
		double nenner, zaehler, lambda;
		Vector schnittpunkt;		
			
		// 1. gerade durch camera und Punkt berechnen
		geradenAnker = cameraPos.toVector();
		geradenRichtung = this.toVector().decrement(cameraPos);
					
		// 3. schnittpunkt gerade ebene
		nenner = ebenenNormale.scalar(geradenRichtung);
		
		if(nenner == 0) return;
		
		zaehler = ebenenNormale.scalar(geradenAnker) - ebenenNormale.scalar(ebenenAnker);
		
		lambda = - (zaehler / nenner);
		
		schnittpunkt = geradenAnker.add(geradenRichtung.scalar(lambda));
//			schnittpunkte.add(new Point2D.Double(schnittpunkt.getX() - camera.getPosition().getX(), schnittpunkt.getZ() - camera.getPosition().getZ())); 
				
		// 4. auf bildschirm mappen
		screenPoint = WorldSpace.mapToScreen(schnittpunkt, ebenenAnker, camera, this);
		if(screenPoint != null)
		g.fillRect((int) (screenPoint.getX()) + (window.getWidth()/2), (window.getHeight()/2) - (int) (screenPoint.getY()), 1, 1);
			
	}
	
	//////////////////////////////////
	//////////   GETTERS    //////////
	//////////////////////////////////
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public Point2D getScreenPoint() {
		return screenPoint;
	}
	
	public String toString() {
		return "[Point X: " + x + ", Y: " + y + ", Z: " + z + ", visible: " + visible + "]";
	}
}
