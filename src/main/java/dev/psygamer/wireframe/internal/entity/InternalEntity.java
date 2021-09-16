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
	
	protected final net.minecraft.entity.Entity internalEntity;
	
	public InternalEntity(final net.minecraft.entity.Entity internal) {
		this.internalEntity = internal;
	}
	
	@Override
	public long getTicks() {
		return this.internalEntity.tickCount;
	}
	
	@Override
	public UUID getUUID() {
		return this.internalEntity.getUUID();
	}
	
	@Override
	public World getWorld() {
		return World.get(this.internalEntity.level);
	}
	
	@Override
	public Vector3d getPosition() {
		return Vector3d.get(this.internalEntity.position());
	}
	
	@Override
	public Vector3d getPreviousPosition() {
		return new Vector3d(
				this.internalEntity.xOld,
				this.internalEntity.yOld,
				this.internalEntity.zOld
		);
	}
	
	@Override
	public Vector3d getEyePosition() {
		return new Vector3d(
				this.internalEntity.getX(),
				this.internalEntity.getY() + this.internalEntity.getEyeHeight(),
				this.internalEntity.getZ()
		);
	}
	
	@Override
	public BlockPosition getBlockPosition() {
		return BlockPosition.get(this.internalEntity.blockPosition());
	}
	
	@Override
	public Vector3d getVelocity() {
		return Vector3d.get(this.internalEntity.getDeltaMovement());
	}
	
	@Override
	public float getRotationYaw() {
		return this.internalEntity.yRot;
	}
	
	@Override
	public float getHeadRotationYaw() {
		return this.internalEntity.getYHeadRot();
	}
	
	@Override
	public float getPreviousRotationYaw() {
		return this.internalEntity.yRotO;
	}
	
	@Override
	public float getRotationPitch() {
		return this.internalEntity.xRot;
	}
	
	@Override
	public float getPreviousRotationPitch() {
		return this.internalEntity.xRotO;
	}
	
	@Override
	public int getPassengerCount() {
		return this.internalEntity.getPassengers().size();
	}
	
	@Override
	public List<Entity> getPassengers() {
		return this.internalEntity.getPassengers().stream()
				.map(InternalEntity::new)
				.collect(Collectors.toList());
	}
	
	@Override
	public boolean isPassenger(final Entity passenger) {
		return this.internalEntity.hasPassenger(passenger.getInternal());
	}
	
	@Override
	public void addPassenger(final Entity passenger) {
		passenger.getInternal().startRiding(this.internalEntity);
	}
	
	@Override
	public void removePassenger(final Entity passenger) {
		passenger.getInternal().stopRiding();
	}
	
	@Override
	public Entity getRiding() {
		return Entity.get(this.internalEntity.getVehicle());
	}
	
	@Override
	public void setRiding(final Entity riding) {
		this.internalEntity.startRiding(riding.getInternal());
	}
	
	@Override
	public boolean isDead() {
		return !this.internalEntity.isAlive();
	}
	
	@Override
	public void damage(final float damage, final String message) {
		this.internalEntity.hurt(new DamageSource(message), damage);
	}
	
	@Override
	public void directDamage(final float damage, final String message) {
		this.internalEntity.hurt(new DamageSource(message).bypassArmor(), damage);
	}
	
	@Override
	public void kill() {
		this.internalEntity.kill();
	}
	
	@Override
	public net.minecraft.entity.Entity getInternal() {
		return this.internalEntity;
	}
}
