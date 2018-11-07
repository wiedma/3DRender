package org.wise.math;

import org.wise.world.WorldSpace;

public class Point3D {
	private double x, y, z;
	
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
		
		WorldSpace.addPoint(this);
	}
	
	public Point3D(double x, double y, double z, boolean visible) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.visible = visible;
		
		if(this.visible)
			WorldSpace.addPoint(this);
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
	
	public String toString() {
		return "[Point X: " + x + ", Y: " + y + ", Z: " + z + ", visible: " + visible + "]";
	}
}
