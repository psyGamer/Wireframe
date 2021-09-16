package dev.psygamer.wireframe.util.math.vector;

public class Vector3i {
	
	private final int x, y, z;
	
	public Vector3i(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getZ() {
		return this.z;
	}
}
