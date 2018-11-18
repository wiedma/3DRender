package org.wise.world;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.wise.graphics.Camera;
import org.wise.graphics.GraphicObject;
import org.wise.graphics.Window;
import org.wise.math.MatrixMaths;
import org.wise.math.Point3D;
import org.wise.math.Vector;

public class WorldSpace{
	
	private static ArrayList<GraphicObject> graphics = new ArrayList<GraphicObject>();
	
//	public static ArrayList<Point2D> schnittpunkte = new ArrayList<Point2D>();
	
	public static void addPoint(GraphicObject g) {
		WorldSpace.graphics.add(g);
	}
	
	public static void draw(Camera camera, Window window) {
		for(GraphicObject g : graphics) {			
			g.draw(camera, window);
		}		
	}
	
	public static Point2D mapToScreen(Vector schnittpunkt, Vector ebenenAnker, Camera camera, Point3D punkt) {
		Vector up = camera.getUp();
		Vector right = camera.getRight();
		Vector ankerZuSchnitt = schnittpunkt.subtract(ebenenAnker);
		
		double angleWithY = Math.acos((ankerZuSchnitt.scalar(up))/(ankerZuSchnitt.abs() * up.abs()));
		double angleWithX = Math.acos((ankerZuSchnitt.scalar(right))/(ankerZuSchnitt.abs() * right.abs()));
		double radius = ankerZuSchnitt.abs();
		
		Vector PunktZuSchnitt = schnittpunkt.subtract(punkt.toVector());
		Vector PunktZuCamera = camera.getPosition().toVector().subtract(punkt.toVector());
		
		if(PunktZuSchnitt.abs() > PunktZuCamera.abs()) {
			//Wenn der Winkel von Bildpunkt zu Schnittpunkt und Bildpunkt zu Kamera 180 ist, soll trotzdem gerendert werden (Bildpunkt zwischen Ebene und Kamera)
			if(!(Math.acos((PunktZuSchnitt.scalar(PunktZuCamera))/(PunktZuSchnitt.abs() * PunktZuCamera.abs())) > 0.1)) {
				return null;
			}
		}
		
		return new Point2D.Double(
								Math.cos(angleWithX) * radius * camera.getPixelPerUnit(),
								Math.cos(angleWithY) * radius * camera.getPixelPerUnit()
		);
	}
	
	@Deprecated
	public static Point2D mapToScreenOld(Vector point, Vector forward, Vector pivot) {
		Vector e = new Vector(1, 0, 0);
		Vector v = forward;
		Vector w = new Vector(0,0,1);
		
		double[][] matrix1 = new double[3][3];
		matrix1[0] = v.toArray();
		matrix1[1] = v.cross(e).unify().toArray();
		matrix1[2] = v.cross(v.cross(e)).toArray();
		
		double[][] matrix2 = new double[3][3];
		matrix2[0] = w.toArray();
		matrix2[1] = w.cross(e).unify().toArray();
		matrix2[2] = w.cross(w.cross(e)).toArray();
		
		double[][] matrixInverted = MatrixMaths.invert(matrix1);
		double[][] rotation = MatrixMaths.multiply(matrix2, matrixInverted);
		
		Vector screenPoint = MatrixMaths.vectorMatrixProduct(point, rotation);
		
		Vector translation = pivot;
		
		screenPoint.decrement(translation);
		screenPoint.increment(new Vector(250, 250, 0));
		
		System.out.println("Point on Screen: " + screenPoint);
		
		
		return new Point2D.Double(screenPoint.getX(), screenPoint.getY());
	}
	
	public static ArrayList<GraphicObject> getGraphicObjects() {
		return graphics;
	}
	
	public static ArrayList<Point3D> getPoints(){
		ArrayList<Point3D> points = new ArrayList<Point3D>();
		for(GraphicObject g : graphics) {
			if(g instanceof Point3D) {
				points.add((Point3D) g);
			}
		}
		return points;
	}
	
}
