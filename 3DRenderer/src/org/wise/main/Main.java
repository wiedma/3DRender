package org.wise.main;

import org.wise.graphics.Camera;
import org.wise.graphics.Window;
import org.wise.math.Vector;

public class Main {
	
	public static Window window;
	
	public static void main(String[] args) {
		
		window = new Window(new Camera());
		
	}
	
	private static void test() {
		Vector v = new Vector(2, 0, 0);
		Vector v2 = new Vector(0, 2, 0);
		
		System.out.println("abs " + v.abs());
		
		System.out.println("add " + v.add(v2));
		System.out.println("sub " + v.subtract(v2));
		System.out.println("cross " + v.cross(v2));
		System.out.println("scalar " + v.scalar(v2));
		System.out.println("scalar (5) " + v.scalar(5));
		
		System.out.println("unify " + v.unify());
		System.out.println("unify " + v2.unify());
	}
	
}
