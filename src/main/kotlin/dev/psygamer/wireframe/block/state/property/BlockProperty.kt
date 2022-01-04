package dev.psygamer.wireframe.block.state.property

import dev.psygamer.wireframe.internal.block.NativeBlockProperty
import java.util.*

abstract class BlockProperty<T : Comparable<T>>(propertyName: String, defaultValue: T) {
	
	val propertyName: String
	val defaultValue: T
	
	internal var mcNative: net.minecraft.state.Property<T>
	
	init {
		Objects.requireNonNull(propertyName, "The property name may not be null")
		
		this.propertyName = propertyName
		this.defaultValue = defaultValue
		
		this.mcNative = NativeBlockProperty(this)
	}
	
	abstract fun getValue(valueName: String): Optional<T>
	abstract fun getValueName(value: T): String
	
	abstract val possibleValues: Set<T>
}
