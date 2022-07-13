package dev.psygamer.wireframe.api.registry.client

import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.client.render.blockentity.BlockEntityRenderer
import dev.psygamer.wireframe.api.registry.MapRegistry
import dev.psygamer.wireframe.util.collection.FreezableMap

object BlockEntityRendererRegistry : MapRegistry<Class<out BlockEntity>, BlockEntityRenderer<*>>() {

	@JvmStatic
	inline fun <reified T : BlockEntity> register(renderer: BlockEntityRenderer<T>) {
		val blockEntityClass = T::class.java
		(elements as FreezableMap)[blockEntityClass] = renderer
	}
}