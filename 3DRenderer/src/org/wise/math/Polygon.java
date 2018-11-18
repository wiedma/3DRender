package org.wise.math;

import java.util.ArrayList;

import org.wise.graphics.Camera;
import org.wise.graphics.GraphicObject;
import org.wise.graphics.Window;

public class Polygon implements GraphicObject {
	private ArrayList<Point3D> vertices = new ArrayList<Point3D>();
	
	public Polygon(){
		
	}
	
	public void draw(Camera camera, Window window) {
		for(Point3D vertex : vertices) {
			vertex.draw(camera, window);
		}
	}
	
	public void addVertex(Point3D vertex) {
		vertices.add(vertex);
	}
	
	public ArrayList<Point3D> getVertices() {
		return vertices;
	}
}
