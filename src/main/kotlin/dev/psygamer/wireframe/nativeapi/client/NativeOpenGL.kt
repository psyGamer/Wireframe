package dev.psygamer.wireframe.nativeapi.client

import net.minecraft.client.Minecraft
import org.lwjgl.opengl.GL11.*
import java.io.Closeable
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.util.Identifier

/** [Source](https://github.com/TeamOpenIndustry/UniversalModCore/blob/972a4289113315b4e56c6982d3681ee08237dade/src/main/java/cam72cam/mod/render/OpenGL.java)*/
object NativeOpenGL {

	fun matrix(): Closeable {
		glPushMatrix()

		return Closeable {
			glPopMatrix()
		}
	}

	fun translate(x: Double, y: Double, z: Double): Closeable {
		glTranslated(x, y, z)

		return Closeable {
			glTranslated(-x, -y, -z)
		}
	}

	fun rotate(x: Double, y: Double, z: Double): Closeable {
		glRotated(x, 1.0, 0.0, 0.0)
		glRotated(y, 0.0, 1.0, 0.0)
		glRotated(z, 0.0, 0.0, 1.0)

		return Closeable {
			glRotated(-x, 1.0, 0.0, 0.0)
			glRotated(-y, 0.0, 1.0, 0.0)
			glRotated(-z, 0.0, 0.0, 1.0)
		}
	}

	fun scale(x: Double, y: Double, z: Double): Closeable {
		glScaled(x, y, z)

		return Closeable {
			glScaled(-x, -y, -z)
		}
	}

	fun bool(operation: Int, newState: Boolean): Closeable {
		val oldState = glIsEnabled(operation)

		if (oldState == newState)
			return Closeable { }

		if (newState)
			glEnable(operation)
		else
			glDisable(operation)

		return Closeable {
			if (oldState)
				glEnable(operation)
			else
				glDisable(operation)
		}
	}

	fun color(red: Float, green: Float, blue: Float, alpha: Float): Closeable {
		val oldColor = FloatArray(4)
		val c = bool(GL_COLOR_MATERIAL, true)

		glGetFloatv(GL_CURRENT_COLOR, oldColor)
		glColor4f(red, green, blue, alpha)

		return Closeable {
			glColor4f(oldColor[0], oldColor[1], oldColor[2], oldColor[3])
			c.close()
		}
	}

	fun color(red: Double, green: Double, blue: Double, alpha: Double): Closeable {
		val oldColor = DoubleArray(4)
		val c = bool(GL_COLOR_MATERIAL, true)

		glGetDoublev(GL_CURRENT_COLOR, oldColor)
		glColor4d(red, green, blue, alpha)

		return Closeable {
			glColor4d(oldColor[0], oldColor[1], oldColor[2], oldColor[3])
			c.close()
		}
	}

	fun blend(source: Int, destination: Int): Closeable {
		val oldSource = glGetInteger(GL_BLEND_SRC)
		val oldDestination = glGetInteger(GL_BLEND_DST)
		val b = bool(GL_BLEND, true)

		glBlendFunc(source, destination)

		return Closeable {
			glBlendFunc(oldSource, oldDestination)
			b.close()
		}
	}

	fun texture(identifier: Identifier): Closeable {
		val oldTexture = glGetInteger(GL_TEXTURE_BINDING_2D)
		val t = bool(GL_TEXTURE_2D, true)

		Minecraft.getInstance().textureManager.bind(identifier.mcNative)

		return Closeable {
			glBindTexture(GL_TEXTURE_2D, oldTexture)
			t.close()
		}
	}

	fun texture(id: Int): Closeable {
		val oldTexture = glGetInteger(GL_TEXTURE_BINDING_2D)
		val t = bool(GL_TEXTURE_2D, true)

		glBindTexture(GL_TEXTURE_2D, id)

		return Closeable {
			glBindTexture(GL_TEXTURE_2D, oldTexture)
			t.close()
		}
	}
}