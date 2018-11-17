package org.wise.graphics;

import org.wise.math.Point3D;
import org.wise.math.Vector;

public class Camera {
	private Point3D position;
	
//	private final double FORWARD_ABSOLUTE = 1;
	private Vector forward = new Vector(0, 0, -1);
	private Vector up = new Vector(0, 1, 0);
	private Vector right = new Vector(1, 0, 0);
	
	public Camera() {
		this(new Point3D(false));
	}
	
	public Camera(Point3D position) {
		this.position = position;
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
}
