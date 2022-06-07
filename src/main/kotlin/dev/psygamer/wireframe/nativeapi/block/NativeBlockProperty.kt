package dev.psygamer.wireframe.nativeapi.block

import net.minecraft.state.Property
import java.util.*
import dev.psygamer.wireframe.api.block.BlockProperty

@Suppress("UNCHECKED_CAST")
class NativeBlockProperty<T : Comparable<T>>(private val blockProperty: BlockProperty<T>) :
	Property<T>(blockProperty.propertyName, blockProperty.defaultValue::class.java as Class<T>) {

	override fun getPossibleValues(): Collection<T> {
		return blockProperty.possibleValues
	}

	override fun getName(value: T): String {
		return blockProperty.getValueName(value).lowercase(Locale.getDefault())
	}

	override fun getValue(valueName: String): Optional<T> {
		return blockProperty.getValue(valueName)
	}
}