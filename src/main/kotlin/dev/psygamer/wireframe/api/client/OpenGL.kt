package dev.psygamer.wireframe.api.client

import org.lwjgl.opengl.GL11.*
import java.io.Closeable
import dev.psygamer.wireframe.nativeapi.client.NativeOpenGL
import dev.psygamer.wireframe.util.*

/**
 * A set of secure methods to use OpenGL
 *
 * "Inspired" by Christian Mesh / cam72cam: [Source](https://github.com/TeamOpenIndustry/UniversalModCore/blob/972a4289113315b4e56c6982d3681ee08237dade/src/main/java/cam72cam/mod/render/OpenGL.java)
 */
object OpenGL {

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
}