package dev.psygamer.wireframe.internal.entity;

import dev.psygamer.wireframe.entity.Entity;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.math.vector.Vector3d;
import dev.psygamer.wireframe.world.World;

import net.minecraft.util.DamageSource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InternalEntity implements Entity {
	
	private final net.minecraft.entity.Entity internal;
	
	public InternalEntity(final net.minecraft.entity.Entity internal) {
		this.internal = internal;
	}
	
	@Override
	public long getTicks() {
		return this.internal.tickCount;
	}
	
	@Override
	public UUID getUUID() {
		return this.internal.getUUID();
	}
	
	@Override
	public World getWorld() {
		return World.get(this.internal.level);
	}
	
	@Override
	public Vector3d getPosition() {
		return Vector3d.get(this.internal.position());
	}
	
	@Override
	public Vector3d getPreviousPosition() {
		return new Vector3d(
				this.internal.xOld,
				this.internal.yOld,
				this.internal.zOld
		);
	}
	
	@Override
	public Vector3d getEyePosition() {
		return new Vector3d(
				this.internal.getX(),
				this.internal.getY() + this.internal.getEyeHeight(),
				this.internal.getZ()
		);
	}
	
	@Override
	public BlockPosition getBlockPosition() {
		return BlockPosition.get(this.internal.blockPosition());
	}
	
	@Override
	public Vector3d getVelocity() {
		return Vector3d.get(this.internal.getDeltaMovement());
	}
	
	@Override
	public float getRotationYaw() {
		return this.internal.yRot;
	}
	
	@Override
	public float getHeadRotationYaw() {
		return this.internal.getYHeadRot();
	}
	
	@Override
	public float getPreviousRotationYaw() {
		return this.internal.yRotO;
	}
	
	@Override
	public float getRotationPitch() {
		return this.internal.xRot;
	}
	
	@Override
	public float getPreviousRotationPitch() {
		return this.internal.xRotO;
	}
	
	@Override
	public int getPassengerCount() {
		return this.internal.getPassengers().size();
	}
	
	@Override
	public List<Entity> getPassengers() {
		return this.internal.getPassengers().stream()
				.map(InternalEntity::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public boolean isPassenger(final Entity passenger) {
		return this.internal.hasPassenger(passenger.getInternal());
	}
	
	@Override
	public void addPassenger(final Entity passenger) {
		passenger.getInternal().startRiding(this.internal);
	}
	
	@Override
	public void removePassenger(final Entity passenger) {
		passenger.getInternal().stopRiding();
	}
	
	@Override
	public Entity getRiding() {
		return new InternalEntity(this.internal.getVehicle());
	}
	
	@Override
	public void setRiding(final Entity riding) {
		this.internal.startRiding(riding.getInternal());
	}
	
	@Override
	public boolean isDead() {
		return !this.internal.isAlive();
	}
	
	@Override
	public void damage(final float damage, final String message) {
		this.internal.hurt(new DamageSource(message), damage);
	}
	
	@Override
	public void directDamage(final float damage, final String message) {
		this.internal.hurt(new DamageSource(message).bypassArmor(), damage);
	}
	
	@Override
	public void kill() {
		this.internal.kill();
	}
	
	@Override
	public net.minecraft.entity.Entity getInternal() {
		return this.internal;
	}
}
