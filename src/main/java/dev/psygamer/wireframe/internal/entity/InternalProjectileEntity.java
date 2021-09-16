package dev.psygamer.wireframe.internal.entity;

import dev.psygamer.wireframe.entity.Entity;
import dev.psygamer.wireframe.entity.ProjectileEntity;

public class InternalProjectileEntity extends InternalEntity implements ProjectileEntity {
	
	protected final net.minecraft.entity.projectile.ProjectileEntity internalProjectile;
	
	public InternalProjectileEntity(final net.minecraft.entity.projectile.ProjectileEntity internal) {
		super(internal);
		
		this.internalProjectile = internal;
	}
	
	@Override
	public Entity getOwner() {
		return Entity.get(this.internalProjectile.getOwner());
	}
	
	@Override
	public void setOwner(final Entity owner) {
		this.internalProjectile.setOwner(owner.getInternal());
	}
	
	@Override
	public net.minecraft.entity.projectile.ProjectileEntity getInternal() {
		return this.internalProjectile;
	}
}
