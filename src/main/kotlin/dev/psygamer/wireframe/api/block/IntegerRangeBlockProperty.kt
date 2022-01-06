package dev.psygamer.wireframe.api.block

import java.util.*

class IntegerRangeBlockProperty @JvmOverloads constructor(
	propertyName: String, private val min: Int = 0, private val max: Int
) : BlockProperty<Int>(propertyName, min) {
	
	override fun getValue(valueName: String): Optional<Int> {
		return try {
			val value = valueName.toInt()
			
			if (!possibleValues.contains(value))
				Optional.empty()
			else
				Optional.of(value)
		} catch (ex: NumberFormatException) {
			Optional.empty()
		}
	}
	
	override fun getValueName(value: Int): String {
		return value.toString()
	}
	
	override val possibleValues: Set<Int>
		get() = buildSet { for (i in min..max) add(i) }
}