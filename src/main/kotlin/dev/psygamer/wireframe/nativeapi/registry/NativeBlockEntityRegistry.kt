package dev.psygamer.wireframe.nativeapi.registry

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.event.RegistryEvent.Register
import net.minecraftforge.eventbus.api.SubscribeEvent
import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.registry.BlockEntityRegistry
import dev.psygamer.wireframe.event.nativeapi.NativeModEventBusSubscriber
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.collection.*

@NativeModEventBusSubscriber
class NativeBlockEntityRegistry(private val modID: String) {

	companion object {

		private val tileEntityTypeCache: FreezableMap<Identifier, TileEntityType<*>> = FreezableHashMap()

		fun getTileEntityType(identifier: Identifier): TileEntityType<*> {
			return tileEntityTypeCache[identifier]!!
		}

		fun generateTileEntityType(definition: BlockEntity.Definition): TileEntityType<*> {
			@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") // It's fine
			return TileEntityType.Builder
				.of(
					{ definition.blockEntitySupplier().mcNative },
					*definition.blockEntityHolders.map { it.mcNative }.toTypedArray()
				)
				.build(null)
				.setRegistryName(definition.identifier.mcNative)
		}
	}

	@SubscribeEvent
	fun onBlockRegistry(event: Register<TileEntityType<*>>) {
		BlockEntityRegistry.freeze()
		BlockEntityRegistry.elements.values
			.filter { it.identifier.namespace == this.modID }
			.forEach { definition ->
				val tileEntityType = generateTileEntityType(definition)

				tileEntityTypeCache[definition.identifier] = tileEntityType
				event.registry.register(tileEntityType)

				Wireframe.LOGGER.info("Successfully registered tile entity ${definition.identifier}")
			}
	}
}