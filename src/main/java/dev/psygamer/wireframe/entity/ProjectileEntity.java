package dev.psygamer.wireframe.entity;

public interface ProjectileEntity extends Entity {
	
	Entity getOwner();
	
	void setOwner(Entity owner);
	
	@Override
	net.minecraft.entity.projectile.ProjectileEntity getInternal();
}
