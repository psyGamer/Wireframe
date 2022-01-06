package dev.psygamer.wireframe.api.registry

import dev.psygamer.wireframe.api.block.entity.BlockEntity.Definition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.collection.FreezableHashMap
import dev.psygamer.wireframe.util.collection.FreezableMap

object BlockEntityRegistry {
	
	@JvmStatic
	val definitions: Map<Identifier, Definition> = FreezableHashMap()
	
	@JvmStatic
	fun register(definition: Definition) {
		(definitions as FreezableMap)[definition.identifier] = definition
	}
	
	@JvmStatic
	fun getDefinition(identifier: Identifier): Definition? {
		return definitions[identifier]
	}
	
	@JvmStatic
	fun freeze() {
		(definitions as FreezableMap).freeze()
	}
}