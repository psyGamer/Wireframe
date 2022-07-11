package dev.psygamer.wireframe.util

data class Identifier(
	val namespace: String = "minecraft",
	val path: String,
) {

	constructor(path: String) : this("minecraft", path)

	override fun toString(): String {
		return "$namespace:$path"
	}
}
