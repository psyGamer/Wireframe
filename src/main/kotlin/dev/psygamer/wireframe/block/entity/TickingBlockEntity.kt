package dev.psygamer.wireframe.block.entity

import dev.psygamer.wireframe.util.Identifier

open class TickingBlockEntity protected constructor(identifier: Identifier) : BlockEntity(identifier) {
	
	open fun tick() {}
}