package dev.psygamer.wireframe.nativeapi.block

import dev.psygamer.wireframe.api.block.Block
import dev.psygamer.wireframe.api.block.state.BlockState
import dev.psygamer.wireframe.api.block.state.property.BlockProperty
import dev.psygamer.wireframe.nativeapi.wfWrapped
import java.util.*

class NativeBlockState(override val mcNative: net.minecraft.block.BlockState) : BlockState {
	
	override val block: Block
		get() = this.mcNative.block.wfWrapped
	
	override fun <T : Comparable<T>> setValue(property: BlockProperty<T>, value: T): BlockState {
		return this.mcNative.setValue(property.mcNative, value).wfWrapped
	}
	
	override fun <T : Comparable<T>> getValue(property: BlockProperty<T>): T {
		return this.mcNative.getValue(property.mcNative)
	}
	
	override fun <T : Comparable<T>> getOptionalValue(property: BlockProperty<T>): Optional<T> {
		return this.mcNative.getOptionalValue(property.mcNative)
	}
	
	override fun `is`(block: Block): Boolean {
		return this.block.mcNative == block.mcNative
	}
}