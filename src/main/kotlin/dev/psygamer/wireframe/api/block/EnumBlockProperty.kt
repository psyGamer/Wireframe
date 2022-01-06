package dev.psygamer.wireframe.api.block

import java.util.*

open class EnumBlockProperty<T : Enum<T>>(
	propertyName: String, vararg possibleValues: T
) : BlockProperty<T>(propertyName, possibleValues[0]) {
	
	private val possibleValuesByName: Map<String, T>
	
	init {
		if (possibleValues.isEmpty())
			throw ArrayIndexOutOfBoundsException("Possible values may not be empty")
		
		this.possibleValuesByName = buildMap {
			possibleValues.forEach {
				put(it.name.lowercase(), it)
			}
		}
	}
	
	override fun getValue(valueName: String): Optional<T> {
		return Optional.ofNullable(possibleValuesByName[valueName])
	}
	
	override fun getValueName(value: T): String {
		return value.name.lowercase(Locale.getDefault())
	}
	
	override val possibleValues: Set<T>
		get() = possibleValuesByName.values.toSet()
}