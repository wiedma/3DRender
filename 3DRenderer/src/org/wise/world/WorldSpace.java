package org.wise.world;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.wise.graphics.Camera;
import org.wise.math.MatrixMaths;
import org.wise.math.Point3D;
import org.wise.math.Vector;

public class WorldSpace {
	
	private static ArrayList<Point3D> points = new ArrayList<Point3D>();
	
	public static void addPoint(Point3D point) {
		WorldSpace.points.add(point);
		
		System.out.println("added: " + point);
	}
	
	public static Point2D[] getPoints2D(Camera camera) {
		Point2D[] points = new Point2D[WorldSpace.points.size()];
		
		
		Vector geradenAnker, geradenRichtung;
		Vector ebenenAnker, ebenenNormale;		
		Point3D cameraPos = camera.getPosition();
		ebenenAnker = camera.getPosition().toVector().increment(camera.getForward());
		ebenenNormale = camera.getForward();
		double nenner, zaehler, lambda;
		Vector schnittpunkt;
		for(int i = 0; i < points.length; i++) {			
			
			// 1. gerade durch camera und Punkt berechnen
			geradenAnker = cameraPos.toVector();
			geradenRichtung = WorldSpace.points.get(i).toVector().decrement(cameraPos);
						
			// 3. schnittpunkt gerade ebene
			nenner = (-ebenenNormale.getX() * geradenRichtung.getX()) +
					(ebenenNormale.getY() * geradenRichtung.getY()) +
					(ebenenNormale.getZ() * geradenRichtung.getZ());
			
			if(nenner == 0) continue;
			
			zaehler = + (ebenenNormale.getX() * geradenAnker.getX()) 
					- (ebenenNormale.getY() * geradenAnker.getY()) 
					- (ebenenNormale.getZ() * geradenAnker.getZ()) 
					- (ebenenNormale.getX() * ebenenAnker.getX())
					+ (ebenenNormale.getY() * ebenenAnker.getY())
					+ (ebenenNormale.getZ() * ebenenAnker.getZ());
			
			lambda = zaehler / nenner;
			
			schnittpunkt = geradenAnker.add(geradenRichtung.scalar(lambda));
					
			// 4. auf bildschirm mappen
			points[i] = mapToScreen(schnittpunkt, camera.getForward(), ebenenAnker);
			///			
		}		
		
		return points;
	}
	
	public static Point2D mapToScreen(Vector point, Vector forward, Vector pivot) {
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
	

	
}
