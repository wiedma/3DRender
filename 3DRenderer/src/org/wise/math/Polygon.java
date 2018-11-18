package org.wise.math;

import java.awt.Graphics;
import java.util.ArrayList;

import org.wise.graphics.Camera;
import org.wise.graphics.GraphicObject;
import org.wise.graphics.Window;
import org.wise.world.WorldSpace;

public class Polygon implements GraphicObject {
	private ArrayList<Point3D> vertices = new ArrayList<Point3D>();
	
	public Polygon(){
		WorldSpace.addObject(this);
	}
	
	public void draw(Camera camera, Window window, Graphics g) {
		int[] xPos = new int[vertices.size()];
		int[] yPos = new int[vertices.size()];
		boolean notNull = true;
		for(int i = 0; i < vertices.size(); i++) {
			Point3D vertex = vertices.get(i);
			vertex.draw(camera, window, g);
			if(vertex.getScreenPoint() == null) {
				notNull = false;
				break;
			}
			xPos[i] = (int) vertex.getScreenPoint().getX() + (window.getWidth()/2);
			yPos[i] = (int) ((window.getHeight()/2) - vertex.getScreenPoint().getY());
		}
		
		if(notNull) {
			g.drawPolygon(xPos, yPos, vertices.size());			
		}
		
	}
	
	public void addVertex(Point3D vertex) {
		vertices.add(vertex);
	}
	
	public ArrayList<Point3D> getVertices() {
		return vertices;
	}
}
