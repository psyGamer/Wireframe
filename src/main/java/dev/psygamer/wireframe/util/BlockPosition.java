package dev.psygamer.wireframe.util;

import dev.psygamer.wireframe.util.math.vector.Vector3i;

public class BlockPosition extends Vector3i {
	
	public BlockPosition(final int x, final int y, final int z) {
		super(x, y, z);
	}
	
	public static BlockPosition get(final net.minecraft.util.math.BlockPos internalPosition) {
		return new BlockPosition(internalPosition.getX(), internalPosition.getY(), internalPosition.getZ());
	}
	
	public net.minecraft.util.math.BlockPos toInternal() {
		return new net.minecraft.util.math.BlockPos(this.getX(), this.getY(), this.getZ());
	}
}
