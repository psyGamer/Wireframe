package dev.psygamer.wireframe.util.math;

import dev.psygamer.wireframe.util.math.vector.Vector3d;

public abstract class HitResult {
	
	public enum Type {
		MISS, BLOCK, ENTITY
	}
	
	protected final Vector3d location;
	
	protected HitResult(final Vector3d location) {
		this.location = location;
	}
	
	public double distanceTo(final Vector3d other) {
		return Math.sqrt(squaredDistanceTo(other));
	}
	
	public double squaredDistanceTo(final Vector3d other) {
		final double xDiff = this.location.getX() - other.getX();
		final double yDiff = this.location.getY() - other.getY();
		final double zDiff = this.location.getZ() - other.getZ();
		
		return xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
	}
	
	public Vector3d getLocation() {
		return this.location;
	}
}
