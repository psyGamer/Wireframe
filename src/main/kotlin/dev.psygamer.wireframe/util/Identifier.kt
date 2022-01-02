package dev.psygamer.wireframe.util

val net.minecraft.util.ResourceLocation.wfWrapped: Identifier
	get() {
		return Identifier(this.namespace, this.path)
	}

data class Identifier(val namespace: String = "minecraft", val path: String) {
	
	override fun toString(): String {
		return "$namespace:$path"
	}
}