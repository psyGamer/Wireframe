package dev.psygamer.wireframe.util;

public class BlockPosition {
	
	final int x, y, z;
	
	public BlockPosition(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static BlockPosition get(final net.minecraft.util.math.BlockPos internalPosition) {
		return new BlockPosition(internalPosition.getX(), internalPosition.getY(), internalPosition.getZ());
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
	
	public net.minecraft.util.math.BlockPos toInternal() {
		return new net.minecraft.util.math.BlockPos(this.x, this.y, this.z);
	}
}
