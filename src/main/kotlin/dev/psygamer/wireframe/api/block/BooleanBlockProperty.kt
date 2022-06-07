package dev.psygamer.wireframe.api.block

import java.util.*

class BooleanBlockProperty(propertyName: String) : BlockProperty<Boolean>(propertyName, false) {

	override fun getValue(valueName: String): Optional<Boolean> {
		return when (valueName) {
			"true" -> Optional.of(true)
			"false" -> Optional.of(false)

			else -> Optional.empty()
		}
	}

	override fun getValueName(value: Boolean): String {
		return value.toString()
	}

	override val possibleValues: Set<Boolean>
		get() = setOf(true, false)
}