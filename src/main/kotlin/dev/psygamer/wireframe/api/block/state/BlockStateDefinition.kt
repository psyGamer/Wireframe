package dev.psygamer.wireframe.api.block.state

import dev.psygamer.wireframe.api.block.Block
import dev.psygamer.wireframe.api.block.state.property.BlockProperty

interface BlockStateDefinition {
	
	val block: Block
	val defaultState: BlockState
	val possibleStates: List<BlockState>
	
	val mcNative: net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState>
	
	fun getProperty(propertyName: String): BlockProperty<*>?
}
