package dev.psygamer.wireframe.util

data class Identifier @JvmOverloads constructor(
	val namespace: String = "minecraft",
	val path: String
) {
	
	override fun toString(): String {
		return "$namespace:$path"
	}
}

internal val net.minecraft.util.ResourceLocation.wfWrapped: Identifier
	get() {
		return Identifier(this.namespace, this.path)
	}
internal val Identifier.mcNative: net.minecraft.util.ResourceLocation
	get() {
		return net.minecraft.util.ResourceLocation(this.namespace, this.path)
	}