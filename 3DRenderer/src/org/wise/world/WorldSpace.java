package org.wise.world;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.wise.graphics.Camera;
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
		for(int i = 0; i < points.length; i++) {			
			///TODO
			
			// 1. gerade durch camera und Punkt berechnen
			geradenAnker = cameraPos.toVector();
			geradenRichtung = WorldSpace.points.get(i).toVector().decrement(cameraPos);
			
			// 2. ebene der camera berechnen			
			ebenenAnker = camera.getPosition().toVector().increment(camera.getForward());
			ebenenNormale = camera.getForward();
			
			// 3. schnittpunkt gerade ebene
			
			// 4. auf bildschirm mappen
			
			///			
		}		
		
		return points;
	}
	
}
