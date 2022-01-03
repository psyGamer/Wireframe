package dev.psygamer.wireframe.util.math;

import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.BlockPositionKt;
import dev.psygamer.wireframe.util.Direction;
import dev.psygamer.wireframe.util.DirectionKt;
import dev.psygamer.wireframe.util.math.vector.Vector3d;
import dev.psygamer.wireframe.util.math.vector.Vector3dKt;

import net.minecraft.util.math.RayTraceResult;

public class BlockHitResult extends HitResult {
	
	private final Direction direction;
	private final BlockPosition position;
	
	private final boolean miss;
	private final boolean inside;
	
	public BlockHitResult(final Vector3d location, final Direction direction, final BlockPosition position, final boolean inside
	) {
		this(location, direction, position, false, inside);
	}
	
	public BlockHitResult(final Vector3d location, final Direction direction, final BlockPosition position, final boolean miss,
						  final boolean inside
	) {
		super(location);
		
		this.direction = direction;
		this.position = position;
		this.miss = miss;
		this.inside = inside;
	}
	
	public static BlockHitResult get(final net.minecraft.util.math.BlockRayTraceResult internal) {
		return new BlockHitResult(
				Vector3dKt.getWfWrapped(internal.getLocation()),
				DirectionKt.getWfWrapped(internal.getDirection()),
				BlockPositionKt.getWfWrapped(internal.getBlockPos()),
				
				internal.getType() == RayTraceResult.Type.MISS,
				internal.isInside()
		);
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public BlockPosition getBlockPosition() {
		return this.position;
	}
	
	public HitResult.Type getType() {
		return this.miss ? HitResult.Type.MISS : HitResult.Type.BLOCK;
	}
	
	public boolean isInside() {
		return this.inside;
	}
}
