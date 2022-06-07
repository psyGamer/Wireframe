package dev.psygamer.wireframe.nativeapi.entity

import dev.psygamer.wireframe.api.entity.*
import dev.psygamer.wireframe.nativeapi.*

class NativeProjectileEntity(override val mcNative: net.minecraft.entity.projectile.ProjectileEntity) :
	NativeEntity(mcNative), ProjectileEntity {

	override var owner: Entity?
		get() = mcNative.owner?.wfWrapped
		set(value) {
			mcNative.owner = value?.mcNative
		}
}