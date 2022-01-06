package dev.psygamer.wireframe.nativeapi.entity

import dev.psygamer.wireframe.api.entity.Entity
import dev.psygamer.wireframe.api.entity.ProjectileEntity
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.nativeapi.wfWrapped

class NativeProjectileEntity(override val mcNative: net.minecraft.entity.projectile.ProjectileEntity) :
	NativeEntity(mcNative), ProjectileEntity {
	
	override var owner: Entity?
		get() = mcNative.owner?.wfWrapped
		set(value) {
			mcNative.owner = value?.mcNative
		}
}