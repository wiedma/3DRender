package org.wise.graphics;

import org.wise.math.Point3D;
import org.wise.math.Vector;

public class Camera {
	private Point3D position;
	
//	private final double FORWARD_ABSOLUTE = 1;
	private Vector forward = new Vector(0, 0, -1);
	private Vector up = new Vector(0, 1, 0);
	private Vector right = new Vector(1, 0, 0);
	
	private double fov;	
	private double pixelPerUnit = 1;
	
	public Camera() {
		this(new Point3D(false));
	}
	
	public Camera(Point3D position) {
		this.position = position;
		
		this.setFOV(Math.PI/2, null);
	}
	
	public void updatePixelPerUnit(Window window) {
		if(window == null)
			return;
		this.pixelPerUnit = (window.getDrawComp().getWidth()/2) / Math.tan(this.fov/2);
	}
	
	//////////////////////////////////
	//////////   SETTER(S)  //////////
	//////////////////////////////////
	
	public void setFOV(double degreeRadians, Window window) {
		this.fov = degreeRadians;
		this.updatePixelPerUnit(window);
	}
	
	//////////////////////////////////
	//////////   GETTERS    //////////
	//////////////////////////////////

	
	public Vector getForward() {
		return this.forward;
	}
	
	public Vector getUp()
	{
		return this.up;
	}
	
	public Vector getRight() {
		return this.right;
	}
	
	
	public Point3D getPosition() {
		return this.position;
	}
	
	public double getPixelPerUnit() {
		return this.pixelPerUnit;
	}
	
	public double getFOV() {
		return this.fov;
	}
}
