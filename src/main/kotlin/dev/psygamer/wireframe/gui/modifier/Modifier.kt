package dev.psygamer.wireframe.gui.modifier

import dev.psygamer.wireframe.api.client.render.PoseStack

abstract class Modifier {

	companion object : Modifier() {

		override fun apply(poseStack: PoseStack, width: Int, height: Int, parentWidth: Int, parentHeight: Int): Pair<Int, Int> {
			var currentWidth = width
			var currentHeight = height

			modifierStack.forEach {
				val (newWidth, newHeight) = it.apply(poseStack, width, height, parentWidth, parentHeight)
				currentWidth = newWidth
				currentHeight = newHeight
			}

			return currentWidth to currentHeight
		}
	}

	internal val modifierStack = mutableListOf<Modifier>()

	abstract fun apply(poseStack: PoseStack, width: Int, height: Int, parentWidth: Int, parentHeight: Int): Pair<Int, Int>

	infix fun and(other: Modifier): Modifier {
		modifierStack.add(other)
		return this
	}
}