package dev.psyGamer.anvil.util.block;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

import javax.vecmath.Vector3d;
import java.util.HashMap;
import java.util.Map;

/**
 * A immutable bounding box utility class
 * <p>
 *
 * @author psyGamer
 * @version 1.0
 * @see AxisAlignedBB
 * @since 1.0
 */
public class AdvancedBoundingBox {
	
	private final double minX;
	private final double minY;
	private final double minZ;
	private final double maxX;
	private final double maxY;
	private final double maxZ;
	
	private EnumFacing upwardRotationBase = EnumFacing.NORTH;
	private EnumFacing downwardRotationBase = EnumFacing.NORTH;
	
	/**
	 * Constructs a new {@link AdvancedBoundingBox} with a specified site.
	 * <p>
	 *
	 * @param sizeX X size of the bounding box
	 * @param sizeY Y size of the bounding box
	 * @param sizeZ Z size of the bounding box
	 * @apiNote The position is at 0,0,0
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox(final double sizeX, final double sizeY, final double sizeZ) {
		this(0, 0, 0, sizeX, sizeY, sizeZ);
	}
	
	/**
	 * Constructs a new {@link AdvancedBoundingBox}
	 * <p>
	 *
	 * @param posX  X position of the bounding box
	 * @param posY  Y position of the bounding box
	 * @param posZ  Z position of the bounding box
	 * @param sizeX X size of the bounding box
	 * @param sizeY Y size of the bounding box
	 * @param sizeZ Z size of the bounding box
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox(final double posX, final double posY, final double posZ,
							   final double sizeX, final double sizeY, final double sizeZ) {
		this.minX = posX;
		this.minY = posY;
		this.minZ = posZ;
		this.maxX = sizeX;
		this.maxY = sizeY;
		this.maxZ = sizeZ;
	}
	
	/**
	 * Constructs a new {@link AdvancedBoundingBox} with a specified site.
	 * <p>
	 *
	 * @param size size of the bounding box
	 * @apiNote The position is at 0,0,0
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox(final Vector3d size) {
		this(new Vector3d(0, 0, 0), size);
	}
	
	/**
	 * Constructs a new {@link AdvancedBoundingBox}
	 * <p>
	 *
	 * @param pos  Position of the bounding box
	 * @param size Size of the bounding box
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox(final Vector3d pos, final Vector3d size) {
		this.minX = pos.x;
		this.minY = pos.y;
		this.minZ = pos.z;
		this.maxX = size.x;
		this.maxY = size.y;
		this.maxZ = size.z;
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox flip(final EnumFacing.Axis axis) {
		return this.flip(axis == EnumFacing.Axis.X, axis == EnumFacing.Axis.Y, axis == EnumFacing.Axis.Z);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox rotate(final EnumFacing facing) {
		switch (facing) {
			default:
			case NORTH:
				return this;
			case SOUTH:
				return this.flip(true, false, true);
			case EAST:
				return this.rotatePositiveY();
			case WEST:
				return this.rotatePositiveY().flip(true, false, true);
			case UP:
				switch (this.upwardRotationBase) {
					default:
					case NORTH:
						return this.rotatePositiveX();
					case SOUTH:
						return this.rotatePositiveX().flip(true, false, true);
					case EAST:
						return this.rotatePositiveX().rotatePositiveY();
					case WEST:
						return this.rotatePositiveX().rotatePositiveY().flip(true, false, true);
				}
			case DOWN:
				switch (this.downwardRotationBase) {
					default:
					case NORTH:
						return this.rotateNegativeX();
					case SOUTH:
						return this.rotateNegativeX().flip(true, false, true);
					case EAST:
						return this.rotateNegativeX().rotatePositiveY();
					case WEST:
						return this.rotateNegativeX().rotatePositiveY().flip(true, false, true);
				}
		}
	}
	
	public AdvancedBoundingBox rotatePositiveX() {
		return new AdvancedBoundingBox(
				this.minX, -this.minZ + 16, this.minY,
				this.maxX, -this.maxZ + 16, this.maxY
		);
	}
	
	public AdvancedBoundingBox rotateNegativeX() {
		return new AdvancedBoundingBox(
				this.minX, this.minZ, -this.minY + 16,
				this.maxX, this.maxZ, -this.maxY + 16
		);
	}
	
	public AdvancedBoundingBox rotatePositiveY() {
		return new AdvancedBoundingBox(
				-this.minZ + 16, this.minY, this.minX,
				-this.maxZ + 16, this.maxY, this.maxX
		);
	}
	
	public AdvancedBoundingBox rotateNegativeY() {
		return new AdvancedBoundingBox(
				this.minZ, this.minY, -this.minX + 16,
				this.maxZ, this.maxY, -this.maxX + 16
		);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox flip(final boolean xAxis, final boolean yAxis, final boolean zAxis) {
		return new AdvancedBoundingBox(
				xAxis ? -this.minX + 16 : this.minX, yAxis ? -this.minY + 16 : this.minY, zAxis ? -this.minZ + 16 : this.minZ,
				xAxis ? -this.maxX + 16 : this.maxX, yAxis ? -this.maxY + 16 : this.maxY, zAxis ? -this.maxZ + 16 : this.maxZ
		);
	}
	
	public AdvancedBoundingBox center() {
		return this.center(true, true, true);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox center(final boolean x, final boolean y, final boolean z) {
		final double centerX = Math.abs(this.maxX - this.minX) / 2;
		final double centerY = Math.abs(this.maxY - this.minY) / 2;
		final double centerZ = Math.abs(this.maxZ - this.minZ) / 2;
		
		return this.offset(
				x ? 8 - centerX : 0,
				y ? 8 - centerY : 0,
				z ? 8 - centerZ : 0
		);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox offset(final double x, final double y, final double z) {
		return new AdvancedBoundingBox(
				this.minX + x, this.minY + y, this.minZ + z,
				this.maxX + x, this.maxY + y, this.maxZ + z
		);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox grow(final double x, final double y, final double z) {
		return new AdvancedBoundingBox(
				this.minX - x, this.minY - y, this.minZ - z,
				this.maxX + x, this.maxY + y, this.maxZ + z
		);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox grow(final double value) {
		return this.grow(value, value, value);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox shrink(final double x, final double y, final double z) {
		return this.grow(-x, -y, -z);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AdvancedBoundingBox shrink(final double value) {
		return this.shrink(value, value, value);
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public AxisAlignedBB getBoundingBox() {
		return new AxisAlignedBB(
				this.minX / 16, this.minY / 16, this.minZ / 16,
				this.maxX / 16, this.maxY / 16, this.maxZ / 16
		);
	}
	
	public Map<EnumFacing, AxisAlignedBB> generateRotationMap() {
		final Map<EnumFacing, AxisAlignedBB> rotationMap = new HashMap<>();
		
		for (final EnumFacing rotation : EnumFacing.values()) {
			rotationMap.put(rotation, this.rotate(rotation).getBoundingBox());
		}
		
		return rotationMap;
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public void setUpwardRotationBase(final EnumFacing upwardRotationBase) {
		if (upwardRotationBase == EnumFacing.DOWN || upwardRotationBase == EnumFacing.UP) {
			throw new IllegalArgumentException("Upward rotation base must be on the horizontal plane (NORTH, EAST, SOUTH, WEST)");
		}
		
		this.upwardRotationBase = upwardRotationBase;
	}
	
	/**
	 * @author psyGamer
	 * @version 1.0
	 * @since 1.0
	 */
	public void setDownwardRotationBase(final EnumFacing downwardRotationBase) {
		if (downwardRotationBase == EnumFacing.DOWN || downwardRotationBase == EnumFacing.UP) {
			throw new IllegalArgumentException("Downward rotation base must be on the horizontal plane (NORTH, EAST, SOUTH, WEST)");
		}
		
		this.downwardRotationBase = downwardRotationBase;
	}
	
	@Override
	public String toString() {
		return String.format("AdvancedBoundingBox[%s, %s, %s -> %s, %s, %s]", this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
	}
}