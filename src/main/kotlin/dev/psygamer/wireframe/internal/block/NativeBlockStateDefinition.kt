package dev.psygamer.wireframe.internal.block

import dev.psygamer.wireframe.block.state.BlockStateDefinition
import dev.psygamer.wireframe.block.state.property.BlockProperty
import dev.psygamer.wireframe.wfWrapped
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.state.StateContainer

class NativeBlockStateDefinition(override val mcNative: StateContainer<Block, BlockState>) : BlockStateDefinition {
	
	override val block: dev.psygamer.wireframe.block.Block
		get() = this.mcNative.owner.wfWrapped
	
	override val defaultState: dev.psygamer.wireframe.block.state.BlockState
		get() = this.possibleStates[0]
	
	override val possibleStates: List<dev.psygamer.wireframe.block.state.BlockState>
		get() = this.mcNative.possibleStates.map { it.wfWrapped }
	
	override fun getProperty(propertyName: String): BlockProperty<*>? {
		return this.mcNative.getProperty(propertyName)?.wfWrapped
	}
}