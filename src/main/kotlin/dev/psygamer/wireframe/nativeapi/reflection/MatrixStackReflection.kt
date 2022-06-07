package dev.psygamer.wireframe.nativeapi.reflection

import com.mojang.blaze3d.matrix.MatrixStack
import java.lang.reflect.Field
import java.util.*

@Suppress("UNCHECKED_CAST")
object MatrixStackReflection {

	private val cls = MatrixStack::class.java

	private val poseStack_field: Field = cls.getDeclaredField("poseStack")

	init {
		poseStack_field.isAccessible = true
	}

	fun getPoseStack(instance: MatrixStack): Deque<MatrixStack.Entry> =
		poseStack_field.get(instance) as Deque<MatrixStack.Entry>

	fun setPoseStack(instance: MatrixStack, value: Deque<MatrixStack.Entry>) =
		poseStack_field.set(instance, value)
}