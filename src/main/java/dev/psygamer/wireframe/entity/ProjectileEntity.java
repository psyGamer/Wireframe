package dev.psygamer.wireframe.entity;

import dev.psygamer.wireframe.internal.entity.InternalProjectileEntity;

public interface ProjectileEntity extends Entity {
	
	static ProjectileEntity get(final net.minecraft.entity.projectile.ProjectileEntity internalEntity) {
		if (internalEntity == null)
			return null;
		
		return new InternalProjectileEntity(internalEntity);
	}
	
	/* Owner */
	
	Entity getOwner();
	
	void setOwner(Entity owner);
	
	/* Internal */
	
	@Override
	net.minecraft.entity.projectile.ProjectileEntity getInternal();
}
