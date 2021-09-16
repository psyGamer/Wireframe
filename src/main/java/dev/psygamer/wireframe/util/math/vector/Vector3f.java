package dev.psygamer.wireframe.util.math.vector;

public class Vector3f {
	
	private final float x, y, z;
	
	public Vector3f(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3f get(final net.minecraft.util.math.vector.Vector3f internal) {
		return new Vector3f(internal.x(), internal.y(), internal.z());
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public double getZ() {
		return this.z;
	}
}
