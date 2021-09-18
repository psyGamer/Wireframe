package dev.psygamer.wireframe.util;

import dev.psygamer.wireframe.util.math.vector.Vector3i;

public class BlockPosition extends Vector3i {
	
	public BlockPosition(final int x, final int y, final int z) {
		super(x, y, z);
	}
	
	public static BlockPosition get(final net.minecraft.util.math.BlockPos internalPosition) {
		return new BlockPosition(internalPosition.getX(), internalPosition.getY(), internalPosition.getZ());
	}
	
	public BlockPosition offset(final Direction direction) {
		return offset(1, direction);
	}
	
	public BlockPosition offset(final int distance, final Direction direction) {
		return offset(
				direction.getUnitVector().getX() * distance,
				direction.getUnitVector().getY() * distance,
				direction.getUnitVector().getZ() * distance
		);
	}
	
	public BlockPosition offset(final int xOffset, final int yOffset, final int zOffset) {
		return new BlockPosition(
				getX() + xOffset,
				getY() + yOffset,
				getZ() + zOffset
		);
	}
	
	public net.minecraft.util.math.BlockPos toInternal() {
		return new net.minecraft.util.math.BlockPos(this.getX(), this.getY(), this.getZ());
	}
}
