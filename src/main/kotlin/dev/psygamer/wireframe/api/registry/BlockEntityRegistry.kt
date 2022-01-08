package dev.psygamer.wireframe.api.registry

import dev.psygamer.wireframe.api.block.entity.BlockEntity.Definition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.collection.FreezableMap

object BlockEntityRegistry : MapRegistry<Identifier, Definition>() {
	
	@JvmStatic
	fun register(definition: Definition) {
		(elements as FreezableMap)[definition.identifier] = definition
	}
}