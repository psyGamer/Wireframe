package dev.psygamer.wireframe.util

data class Identifier(val namespace: String = "minecraft", val path: String) {
	
	override fun toString(): String {
		return "$namespace:$path"
	}
}

val net.minecraft.util.ResourceLocation.wfWrapped: Identifier
	get() {
		return Identifier(this.namespace, this.path)
	}
val Identifier.mcNative: net.minecraft.util.ResourceLocation
	get() {
		return net.minecraft.util.ResourceLocation(this.namespace, this.path)
	}