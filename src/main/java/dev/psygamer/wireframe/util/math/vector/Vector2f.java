package dev.psygamer.wireframe.util.math.vector;

public class Vector2f {
	
	private final float x, y;
	
	public Vector2f(final float x, final float y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector2f get(final net.minecraft.util.math.vector.Vector2f internal) {
		return new Vector2f(internal.x, internal.y);
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
}
