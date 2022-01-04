package dev.psygamer.wireframe.internal.entity

import dev.psygamer.wireframe.entity.Entity
import dev.psygamer.wireframe.entity.ProjectileEntity
import dev.psygamer.wireframe.mcNative
import dev.psygamer.wireframe.wfWrapped

class NativeProjectileEntity(override val mcNative: net.minecraft.entity.projectile.ProjectileEntity) :
	NativeEntity(mcNative), ProjectileEntity {
	
	override var owner: Entity?
		get() = mcNative.owner?.wfWrapped
		set(value) {
			mcNative.owner = value?.mcNative
		}
}