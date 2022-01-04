package dev.psygamer.wireframe.registry

import dev.psygamer.wireframe.block.entity.BlockEntity.Definition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.collection.FreezableHashMap
import dev.psygamer.wireframe.util.collection.FreezableMap

object BlockEntityRegistry {
	
	@JvmStatic
	val definitions: Map<Identifier, Definition> = FreezableHashMap()
	
	@JvmStatic
	fun register(definition: Definition) {
		(this.definitions as FreezableMap)[definition.identifier] = definition
	}
	
	@JvmStatic
	fun getDefinition(identifier: Identifier): Definition? {
		return this.definitions[identifier]
	}
	
	@JvmStatic
	fun freeze() {
		(this.definitions as FreezableMap).freeze()
	}
}