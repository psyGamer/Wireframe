package dev.psygamer.wireframe.internal.block.state

import dev.psygamer.wireframe.block.state.property.BlockProperty
import net.minecraft.state.Property
import java.util.*

class BlockPropertyWrapper<T : Comparable<T>>(val internal: Property<T>) :
	BlockProperty<T>(internal.name, internal.possibleValues.toList()[0]) {
	
	override fun getValue(valueName: String): Optional<T> {
		return internal.getValue(valueName)
	}
	
	override fun getValueName(value: T): String {
		return internal.getName(value)
	}
	
	override val possibleValues: Set<T>
		get() = internal.possibleValues.toSet()
}