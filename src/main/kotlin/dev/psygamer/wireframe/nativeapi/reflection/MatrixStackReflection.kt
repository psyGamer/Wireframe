package dev.psygamer.wireframe.nativeapi.reflection

import com.mojang.blaze3d.matrix.MatrixStack
import net.minecraft.util.math.vector.*
import java.util.*

@Suppress("UNCHECKED_CAST")
object MatrixStackReflection {

	private val poseStack_field = MatrixStack::class.java.getDeclaredField("poseStack")

	private val MatrixStackEntry_constructor =
		MatrixStack.Entry::class.java.getDeclaredConstructor(Matrix4f::class.java, Matrix3f::class.java)

	init {
		poseStack_field.isAccessible = true

		MatrixStackEntry_constructor.isAccessible = true
	}

	var MatrixStack.poseStack: Deque<MatrixStack.Entry>
		set(value) = poseStack_field.set(this, value)
		get() = poseStack_field.get(this) as Deque<MatrixStack.Entry>

	fun newEntry(pose: Matrix4f, normal: Matrix3f): MatrixStack.Entry =
		MatrixStackEntry_constructor.newInstance(pose, normal)
}