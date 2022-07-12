package dev.psygamer.wireframe.util

data class Identifier @JvmOverloads constructor(
	val namespace: String = "minecraft",
	val path: String,
) {

	override fun toString(): String {
		return "$namespace:$path"
	}
}
