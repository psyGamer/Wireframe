package dev.psygamer.wireframe.gui.modifier

import dev.psygamer.wireframe.api.client.render.PoseStack

private class MarginModifier(private val size: Int) : Modifier() {

	override fun apply(poseStack: PoseStack, width: Int, height: Int, parentWidth: Int, parentHeight: Int): Pair<Int, Int> {
		poseStack.translate(size, size, 0)
		return width + size * 2 to height + size * 2
	}
}

fun Modifier.margin(size: Int) = this and MarginModifier(size)