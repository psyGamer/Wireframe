package dev.psygamer.wireframe.block.state

import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.block.state.property.BlockProperty
import dev.psygamer.wireframe.internal.block.InternalBlockStateDefinition

interface BlockStateDefinition {
	
	val block: Block
	val defaultState: BlockState
	val possibleStates: List<BlockState>
	
	val mcNative: net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState>
	
	fun getProperty(propertyName: String?): BlockProperty<*>
}

internal val net.minecraft.state.StateContainer<net.minecraft.block.Block, net.minecraft.block.BlockState>.wfWrapped: BlockStateDefinition
	get() = InternalBlockStateDefinition(this)