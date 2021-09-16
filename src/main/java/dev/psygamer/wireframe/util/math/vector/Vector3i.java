package dev.psygamer.wireframe.util.math.vector;

public class Vector3i {
	
	private final int x, y, z;
	
	public Vector3i(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3i get(final net.minecraft.util.math.vector.Vector3i internal) {
		return new Vector3i(internal.getX(), internal.getY(), internal.getZ());
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