package dev.psygamer.wireframe.nativeapi.registry.client

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.registry.BlockEntityRegistry
import dev.psygamer.wireframe.api.registry.client.BlockEntityRendererRegistry
import dev.psygamer.wireframe.event.nativeapi.NativeModEventBusSubscriber
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.client.render.blockentity.NativeBlockEntityRenderer
import dev.psygamer.wireframe.nativeapi.registry.NativeBlockEntityRegistry

@NativeModEventBusSubscriber
class NativeBlockEntityRendererRegistry(private val modID: String) {

	@SubscribeEvent
	fun onClientSetup(event: FMLClientSetupEvent) {
		BlockEntityRendererRegistry.freeze()
		BlockEntityRendererRegistry.elements.forEach { (blockEntityClass, renderer) ->
			val identifier = findBlockEntityIdentifier(blockEntityClass) ?: return@forEach
			val tileEntityType = NativeBlockEntityRegistry.getTileEntityType(identifier)

			@Suppress("UNCHECKED_CAST") // NativeBlockEntity is the only possible option.
			ClientRegistry.bindTileEntityRenderer(tileEntityType as TileEntityType<NativeBlockEntity>) { NativeBlockEntityRenderer(renderer, it) }
		}
	}
}

private fun findBlockEntityIdentifier(blockEntityClass: Class<out BlockEntity>) =
	BlockEntityRegistry.elements.values
		.find { it.blockEntityClass == blockEntityClass }
		?.identifier