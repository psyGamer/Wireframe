package dev.psygamer.wireframe.api.block

import java.util.*

interface BlockState {
	
	val block: Block
	
	val mcNative: net.minecraft.block.BlockState
	
	fun <T : Comparable<T>> setValue(property: BlockProperty<T>, value: T): BlockState
	
	fun <T : Comparable<T>> getValue(property: BlockProperty<T>): T
	fun <T : Comparable<T>> getOptionalValue(property: BlockProperty<T>): Optional<T>
	
	fun `is`(block: Block): Boolean
}