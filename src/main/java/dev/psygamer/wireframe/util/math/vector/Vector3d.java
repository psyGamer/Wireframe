package dev.psygamer.wireframe.util.math.vector;

public class Vector3d {
	
	private final double x, y, z;
	
	public Vector3d(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
}
