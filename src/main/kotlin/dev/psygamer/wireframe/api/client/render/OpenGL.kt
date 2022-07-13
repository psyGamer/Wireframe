package dev.psygamer.wireframe.api.client.render

import org.lwjgl.opengl.GL11.*
import java.io.Closeable
import dev.psygamer.wireframe.nativeapi.client.NativeOpenGL
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.vector.Vector3d

/**
 * A set of secure methods to use OpenGL
 *
 * "Inspired" by Christian Mesh / cam72cam: [Source](https://github.com/TeamOpenIndustry/UniversalModCore/blob/972a4289113315b4e56c6982d3681ee08237dade/src/main/java/cam72cam/mod/render/OpenGL.java)
 */
object OpenGL {

	/**
	 * Pushes a new matrix onto the current matrix stack.
	 *
	 * [Closeable.close] will pop the matrix from the stack again.
	 */
	fun matrix(): Closeable =
		NativeOpenGL.matrix()

	/**
	 * Translates the current position by the specified [offset].
	 *
	 * [Closeable.close] will undo this translation.
	 */
	fun translate(offset: Vector3d): Closeable =
		NativeOpenGL.translate(offset.x, offset.y, offset.z)

	/**
	 * Translates the current position by the specified [x], [y] and [z] components.
	 *
	 * [Closeable.close] will undo this translation.
	 */
	fun translate(x: Double, y: Double, z: Double): Closeable =
		NativeOpenGL.translate(x, y, z)

	/**
	 * Translates the current position by the specified [rotation].
	 *
	 * [Closeable.close] will undo this rotation.
	 */
	fun rotate(rotation: Vector3d): Closeable =
		NativeOpenGL.rotate(rotation.x, rotation.y, rotation.z)

	/**
	 * Translates the current position by the specified [x], [y] and [z] components.
	 *
	 * [Closeable.close] will undo this rotation.
	 */
	fun rotate(x: Double, y: Double, z: Double): Closeable =
		NativeOpenGL.rotate(x, y, z)

	/**
	 * Translates the current position by the specified [scale].
	 *
	 * [Closeable.close] will undo this scale.
	 */
	fun scale(scale: Vector3d): Closeable =
		NativeOpenGL.scale(scale.x, scale.y, scale.z)

	/**
	 * Translates the current position by the specified [x], [y] and [z] components.
	 *
	 * [Closeable.close] will undo this scale.
	 */
	fun scale(x: Double, y: Double, z: Double): Closeable =
		NativeOpenGL.scale(x, y, z)

	/**
	 * Enables the specified OpenGL [operation].
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun enable(operation: Int): Closeable =
		NativeOpenGL.bool(operation, true)

	/**
	 * Disabled the specified OpenGL [operation].
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun disable(operation: Int): Closeable =
		NativeOpenGL.bool(operation, false)

	/**
	 * Set the [state] of the specified OpenGL [operation].
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun bool(operation: Int, state: Boolean): Closeable =
		NativeOpenGL.bool(operation, state)

	/**
	 * * Changes the current [color]
	 * * Enables [GL_COLOR_MATERIAL]
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun color(color: Color): Closeable =
		NativeOpenGL.color(color.red, color.green, color.blue, color.alpha)

	/**
	 * * Changes the current color's [red], [green], [blue] and [alpha] channels
	 * * Enables [GL_COLOR_MATERIAL]
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun color(red: Float, green: Float, blue: Float, alpha: Float = 1.0f): Closeable =
		NativeOpenGL.color(red, green, blue, alpha)

	/**
	 * * Changes the current color's [red], [green], [blue] and [alpha] channels
	 * * Enables [GL_COLOR_MATERIAL]
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun color(red: Double, green: Double, blue: Double, alpha: Double = 1.0): Closeable =
		NativeOpenGL.color(red, green, blue, alpha)

	/**
	 * * Changes the OpenGL blending-function
	 * * Enables [GL_BLEND]
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun blend(source: Int, destination: Int): Closeable =
		NativeOpenGL.blend(source, destination)

	/**
	 * * Binds the texture located at the specified [identifier]
	 * * Enables [GL_TEXTURE_2D]
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun texture(identifier: Identifier): Closeable =
		NativeOpenGL.texture(identifier)

	/**
	 * * Binds the texture with the corresponding [id]
	 * * Enables [GL_TEXTURE_2D]
	 *
	 * [Closeable.close] will revert to the state before the change.
	 */
	fun texture(id: Int): Closeable =
		NativeOpenGL.texture(id)

	// move to native
	fun createTexture(): Closeable {
		val texture = glGenTextures()
		return Closeable { glDeleteTextures(texture) }
	}
}