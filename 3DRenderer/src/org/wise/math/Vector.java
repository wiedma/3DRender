package org.wise.math;

public class Vector {
	
	private double x, y, z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector add(Vector v) {
		return new Vector(
				this.x + v.x,
				this.y + v.y,
				this.z + v.z
			);
	}
	
	public Vector increment(Vector v) {
		this.x += v.getX();
		this.y += v.getY();
		this.z += v.getZ();
		return this;
	}
	
	public Vector subtract(Vector v) {
		return new Vector(
				this.x - v.x,
				this.y - v.y,
				this.z - v.z
			);
	}
	
	public Vector decrement(Vector v) {
		this.x -= v.getX();
		this.y -= v.getY();
		this.z -= v.getZ();
		return this;
	}
	
	public Vector decrement(Point3D p) {
		this.x -= p.getX();
		this.y -= p.getY();
		this.z -= p.getZ();
		return this;
	}
	
	public double scalar(Vector v) {
		return (this.x * v.x) + (this.y * v.y) + (this.z * v.z); 
	}
	
	public Vector scalar(double a) {
		return new Vector(
				this.x * a,
				this.y * a,
				this.z * a
			);
	}
	
	public Vector cross(Vector v) {
		return new Vector(
				(this.y * v.z) - (this.z * v.y),
				(this.z * v.x) - (this.x * v.z),
				(this.x * v.y) - (this.y * v.x)
			);
	}
	
	public double abs() {
		return Math.sqrt((x*x) + (y*y) + (z*z));
	}
	
	public Vector unify() {
		return this.scalar(1 / this.abs());
	}
	
	public double[] toArray() {
		return new double[] {x,y,z};
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
		return "[Vector X: " + x + ", Y: " + y + ", Z: " + z + "]";
	}
	
}
