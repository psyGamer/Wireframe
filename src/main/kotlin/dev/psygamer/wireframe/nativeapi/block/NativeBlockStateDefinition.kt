package dev.psygamer.wireframe.nativeapi.block

import dev.psygamer.wireframe.api.block.state.BlockStateDefinition
import dev.psygamer.wireframe.api.block.state.property.BlockProperty
import dev.psygamer.wireframe.nativeapi.wfWrapped
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.state.StateContainer

class NativeBlockStateDefinition(override val mcNative: StateContainer<Block, BlockState>) : BlockStateDefinition {
	
	override val block: dev.psygamer.wireframe.api.block.Block
		get() = this.mcNative.owner.wfWrapped
	
	override val defaultState: dev.psygamer.wireframe.api.block.state.BlockState
		get() = this.possibleStates[0]
	
	override val possibleStates: List<dev.psygamer.wireframe.api.block.state.BlockState>
		get() = this.mcNative.possibleStates.map { it.wfWrapped }
	
	override fun getProperty(propertyName: String): BlockProperty<*>? {
		return this.mcNative.getProperty(propertyName)?.wfWrapped
	}
}