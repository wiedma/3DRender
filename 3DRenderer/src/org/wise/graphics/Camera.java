package org.wise.graphics;

import org.wise.math.MatrixMaths;
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
	
	public static final int FORWARD_AXIS = 0;
	public static final int RIGHT_AXIS = 1;
	public static final int UP_AXIS = 2;
	
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
	
	public void rotate(int axis, double angle) {
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x,y,z;
		
		//Setze die richtige Achse
		switch(axis) {
			case FORWARD_AXIS:
				x = forward.getX();
				y = forward.getY();
				z = forward.getZ();
				break;
			case RIGHT_AXIS:
				x = right.getX();
				y = right.getY();
				z = right.getZ();
				break;
			case UP_AXIS:
				x = up.getX();
				y = up.getY();
				z = up.getZ();
				break;
			default: return;
		}
		
		//Berechne die Drehmatrix
		double[][] drehmatrix = {
				{	x * x * (1 - cos) + cos,
					x * y * (1 - cos) - (z * sin),
					x * z * (1 - cos) + (y * sin)
				},
				{
					y * x * (1 - cos) + (z * sin),
					y * y * (1 - cos) + cos,
					y * z * (1 - cos) - (x * sin)
				},
				{
					z * x * (1 - cos) - (y * sin),
					z * y * (1 - cos) + (x * sin),
					z * z * (1 - cos) + cos
				}
		};
		
		switch(axis) {
			case FORWARD_AXIS:
				up = MatrixMaths.vectorMatrixProduct(up, drehmatrix);
				right = forward.cross(up);
				break;
			case RIGHT_AXIS:
				forward = MatrixMaths.vectorMatrixProduct(forward, drehmatrix);
				up = right.cross(forward);
				break;
			case UP_AXIS:
				right = MatrixMaths.vectorMatrixProduct(right, drehmatrix);
				forward = up.cross(right);
				break;
			default: return;
		}
		
	}
	
	public void rotate(Vector axis, double angle) {
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double x = axis.getX();
		double y = axis.getY();
		double z = axis.getZ();
		
		//Berechne die Drehmatrix
		double[][] drehmatrix = {
				{	x * x * (1 - cos) + cos,
					x * y * (1 - cos) - (z * sin),
					x * z * (1 - cos) + (y * sin)
				},
				{
					y * x * (1 - cos) + (z * sin),
					y * y * (1 - cos) + cos,
					y * z * (1 - cos) - (x * sin)
				},
				{
					z * x * (1 - cos) - (y * sin),
					z * y * (1 - cos) + (x * sin),
					z * z * (1 - cos) + cos
				}
		};
		
		forward = MatrixMaths.vectorMatrixProduct(forward, drehmatrix).unify();
		up = MatrixMaths.vectorMatrixProduct(up, drehmatrix).unify();
		right = MatrixMaths.vectorMatrixProduct(right, drehmatrix).unify();
		
//		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("Forward: " + forward);
//		System.out.println("Right: " + right);
//		System.out.println("Up: " + up);
		
		
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
