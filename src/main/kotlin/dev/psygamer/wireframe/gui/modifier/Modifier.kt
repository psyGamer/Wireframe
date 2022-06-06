package dev.psygamer.wireframe.gui.modifier

import dev.psygamer.wireframe.api.client.render.PoseStack

interface Modifier {

	companion object

	fun apply(poseStack: PoseStack, width: Int, height: Int, parentWidth: Int, parentHeight: Int): Pair<Int, Int>
}